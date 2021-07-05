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
    private static final long TIMER_COUNTER_DELAY_IN_MINUTES = 10; // Delay before the first check
    private static final long TIMER_COUNTER_REPEAT_IN_MINUTES = 10; // How often to repeat the check
    private static final int CONNECTION_VALIDITY_TIMEOUT = 0;
    private final Lock counterLock = new ReentrantLock();
    private final Condition condition = counterLock.newCondition();
    private final BlockingQueue<ProxyConnection> freeConnectionPool;
    private final BlockingQueue<ProxyConnection> busyConnectionPool;
    private static ConnectionPool instance;

    private ConnectionPool() {
        freeConnectionPool = new LinkedBlockingDeque<>(ConnectionCreator.POOL_SIZE);
        busyConnectionPool = new LinkedBlockingDeque<>();

        for (int i = 0; i < ConnectionCreator.POOL_SIZE; i++) {
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

        // Sets on the TimerTask for checking out (like every hour/a half or so) whether ConnectionPool is full or not
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

        logger.debug("checking out if connection returned is valid...");
        try {
            if (proxyConnection.isValid(CONNECTION_VALIDITY_TIMEOUT)) {
                // if connection is valid we move it from one queue to another
                isRemoved = busyConnectionPool.remove(proxyConnection);
                isAdded = freeConnectionPool.offer(proxyConnection);
                logger.debug("connection validity - ok, return to free connections list");
            } else {
                // if connection isn't valid we have to remove it and add another one instead
                isRemoved = busyConnectionPool.remove(proxyConnection);
                isAdded = addNewConnection();
                logger.debug("connection isn't valid, gonna remove and replace by new one");
            }
        } catch (SQLException throwables) {
            // Pretty much impossible scenario
            logger.error("Connection validity error");
        }

        logger.info(isRemoved && isAdded
                ? "Connection been successfully moved to freeConnectionPoll"
                : "Connection releasing error");

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

    boolean setServiceModeOn() {
        // whenever we need to count connections in both queues simultaneously we set isOnCalculation flag on
        boolean isSetOn = isOnCalculation.compareAndSet(false, true);
        logger.info(isSetOn ? "Connection pool is on service" : "Error: Connection poos isn't on service");

        return isSetOn;
    }

    boolean setServiceModeOff() {
        // sets isOnCalculation flag off, pool can keep on working
        boolean isSetOff = isOnCalculation.compareAndSet(true,false);
        logger.info(isSetOff ? "Service mode been switched off" : "Error: Service mode hasn't been switched off");

        return isSetOff;
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

        return isAdded;
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
