package by.spetr.web.model.pool;

import by.spetr.web.model.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final AtomicBoolean isOnCalculation = new AtomicBoolean(false);
    private static final long TIMER_COUNTER_DELAY_IN_MINUTES = 10;
    private static final long TIMER_COUNTER_REPEAT_IN_MINUTES = 10;
    private static final int CONNECTION_VALIDITY_TIMEOUT = 0;
    private final Lock counterLock = new ReentrantLock();
    private final Condition condition = counterLock.newCondition();
    private final BlockingQueue<ProxyConnection> freeConnectionPool;
    private final BlockingQueue<ProxyConnection> busyConnectionPool;
    private static ConnectionPool instance;

    private ConnectionPool() {
        int poolSize = ConnectionCreator.POOL_SIZE;
        freeConnectionPool = new LinkedBlockingDeque<>(poolSize);
        busyConnectionPool = new LinkedBlockingDeque<>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = ConnectionCreator.createConnection();
            } catch (SQLException throwables) {
                logger.error("Connection can't be created", throwables);
            }
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            boolean isAdded = freeConnectionPool.offer(proxyConnection);
            if (!isAdded) {
                logger.error("Connection hasn't been added to busy connections list");
                throw new RuntimeException("Connection hasn't been added to busy connections list");
            }
        }

        // TimerTask for checking out (every hour or so) whether ConnectionPool is full or not
        Timer timer = new Timer();
        timer.schedule(
                new ConnectionCheckerTimerTask(counterLock, condition),
                TimeUnit.MINUTES.toMillis(TIMER_COUNTER_DELAY_IN_MINUTES),
                TimeUnit.MINUTES.toMillis(TIMER_COUNTER_REPEAT_IN_MINUTES)
        );
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            if (isInitialized.compareAndSet(false,true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        logger.info("getConnection() method been called");
        // checking out whether we need to suspend this method
        if (isOnCalculation.get()) {
            onServicePause();
        }

        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnectionPool.take();
            busyConnectionPool.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.warn("Interrupted waiting for a queue", e);
        }

        if (proxyConnection == null) {
            throw new ConnectionPoolException("Error getting free connection");
        }

        logger.info("Connection been given");

        return proxyConnection;
    }

    public boolean releaseConnection(ProxyConnection proxyConnection) {
        logger.info("releaseConnection() method been called");
        // checking out whether it needs to suspend this method
        if (isOnCalculation.get()) {
            onServicePause();
        }

        if (proxyConnection.getClass() != ProxyConnection.class) {
            logger.error("Wild connection been returned");
            return false;
        }

        boolean isRemoved = false;
        boolean isAdded = false;

        try {
            if (proxyConnection.isValid(CONNECTION_VALIDITY_TIMEOUT)) {
                // if connection is valid we move it from one queue to another
                isRemoved = busyConnectionPool.remove(proxyConnection);
                isAdded = freeConnectionPool.offer(proxyConnection);
            } else {
                // if connection isn't valid we have to remove it and add another one instead
                isRemoved = busyConnectionPool.remove(proxyConnection);
                isAdded = addNewConnection();
            }
        } catch (SQLException throwables) {
            // Pretty much impossible scenario
            logger.error("Connection validity error");
        }

        logger.info(isRemoved && isAdded ?
                "Connection has been successfully released and moved to freeConnectionPoll" :
                "Connection releasing error");

        return isRemoved && isAdded;
    }

    private void onServicePause() {
        // while flag is on we put lock and wait to both getting and releasing connections methods
        // to make it possible to count connections within two queues
        while(isOnCalculation.get()) {
            try {
                logger.info("setting up the lock");
                counterLock.lock();
                condition.await();
            } catch (InterruptedException e) {
                logger.warn("Sleep been interrupted");
            } finally {
                counterLock.unlock();
            }
        }
    }

    int getFreeConnectionPoolSize() {
        return freeConnectionPool.size();
    }

    int getBusyConnectionPoolSize() {
        return busyConnectionPool.size();
    }

    void setServiceModeOn() {
        // whenever we need to count connections in both queues we set isOnCalculation flag on
        isOnCalculation.compareAndSet(false, true);
        logger.info("Connection pool is in service mode");
    }

    void setServiceModeOff() {
        // sets isOnCalculation flag off
        isOnCalculation.compareAndSet(true,false);
        logger.info("Service mode was switched off");
    }

    boolean addNewConnection() {
        logger.info("Adding new connection method been called");
        Connection connection = null;
        try {
            connection = ConnectionCreator.createConnection();
        } catch (SQLException throwables) {
            logger.error("Connection can't be created", throwables);
        }
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        boolean isAdded = freeConnectionPool.offer(proxyConnection);
        logger.info("Was connection added? {}", isAdded);

        return freeConnectionPool.offer(proxyConnection);
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < ConnectionCreator.POOL_SIZE; i++) {
                ProxyConnection proxyConnection = freeConnectionPool.take();
                proxyConnection.reallyClose();
            }
        } catch (InterruptedException | SQLException e) {
            logger.error("error destroying Connection Pool",e );
            throw new ConnectionPoolException("error destroying Connection Pool", e);
        } finally {
            deregisterDriver();
        }
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                logger.error("Drivers deregistration error", throwables);
                throw new RuntimeException("Drivers deregistration error", throwables);
            }
        });
    }
}
