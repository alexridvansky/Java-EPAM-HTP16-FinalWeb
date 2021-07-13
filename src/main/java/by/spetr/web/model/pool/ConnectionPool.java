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

import static by.spetr.web.model.pool.ConnectionCreator.POOL_SIZE;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final AtomicBoolean isOnCalculation = new AtomicBoolean(false);
    private static final long TIMER_COUNTER_DELAY_IN_MINUTES = 10; // Delay before the first check
    private static final long TIMER_COUNTER_REPEAT_IN_MINUTES = 10; // How often to repeat the check
    private static final int CONNECTION_VALIDITY_TIMEOUT = 0;
    private final Timer timer;
    private final Lock counterLock = new ReentrantLock();
    private final Condition condition = counterLock.newCondition();
    private final BlockingQueue<ProxyConnection> freeConnectionPool;
    private final BlockingQueue<ProxyConnection> busyConnectionPool;
    private static ConnectionPool instance;

    private ConnectionPool() {
        freeConnectionPool = new LinkedBlockingDeque<>(POOL_SIZE);
        busyConnectionPool = new LinkedBlockingDeque<>();

        for (int i = 0; i < POOL_SIZE; i++) {
            addNewConnection();
            logger.debug("pool size is {}/{}", getActualPoolSize(), POOL_SIZE);

        }

        // Sets on the TimerTask for checking out (like every hour/a half or so) whether ConnectionPool is full or not
        timer = new Timer();
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
            logger.warn("Interrupted waiting for a free connection", e);
            Thread.currentThread().interrupt();
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
                freeConnectionPool.put(proxyConnection);
                isAdded = true;
                logger.debug("connection is valid, return to free connections list");
            } else {
                // if connection isn't valid we have to remove it and add another one instead
                isRemoved = busyConnectionPool.remove(proxyConnection);
                isAdded = addNewConnection();
                logger.info("connection isn't valid, gonna remove and replace by new one");
            }
        } catch (SQLException e) {
            // Pretty much impossible scenario
            logger.error("Connection validity checking error");
        } catch (InterruptedException e) {
            logger.error("Awaiting been interrupted, error adding connection to free connections list", e);
            Thread.currentThread().interrupt();
        }

        logger.info(isRemoved && isAdded
                ? "Connection been successfully released and moved to freeConnectionPoll"
                : "Connection releasing error");
        logger.debug("pool size is {}/{}", getActualPoolSize(), POOL_SIZE);

        return isRemoved && isAdded;
    }

    boolean addNewConnection() {
        logger.info("Adding new connection method been called");

        Connection connection = null;
        try {
            connection = ConnectionCreator.createConnection();
        } catch (SQLException e) {
            logger.error("Connection can't be created", e);
            throw new RuntimeException("Connection can't be created", e);
        }

        ProxyConnection proxyConnection = new ProxyConnection(connection);

        try {
            freeConnectionPool.put(proxyConnection);
            logger.debug("connection been added to free connection list");
            return true;
        } catch (InterruptedException e) {
            logger.error("error adding connection to the free connection list, awaiting been interrupted", e);
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     *  while the flag is on we put lock and wait to both getting and releasing connections
     *  methods to make it possible to count connections within two queues
     */
    private void onServicePause() {
        while(isOnCalculation.get()) {
            try {
                logger.info("setting up the lock");
                counterLock.lock();
                condition.await();
            } catch (InterruptedException e) {
                logger.error("Sleep been interrupted");
                Thread.currentThread().interrupt();
            } finally {
                counterLock.unlock();
            }
        }
    }

    /**
     * the action should be performed under the service mode on
     *
     * @return the size of Pool in total as a sum of free and used connections
     */
    int getActualPoolSize() {
        return freeConnectionPool.size() + busyConnectionPool.size();
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

    public void destroyPool() throws ConnectionPoolException {
        logger.info("TimerTask is gonna to be cancelled");
        timer.cancel();
        int actualPoolSize = getActualPoolSize();
        try {
            for (int i = 1; i <= actualPoolSize; i++) {
                ProxyConnection proxyConnection = freeConnectionPool.take();
                proxyConnection.reallyClose();
                logger.debug("{}/{} connections closed", i, actualPoolSize);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            logger.error("error destroying Connection Pool",e );
            throw new ConnectionPoolException("error destroying Connection Pool", e);
        } finally {
            deregisterDriver();
        }
        //deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Drivers deregistered successfully ");
            } catch (SQLException e) {
                logger.error("Drivers deregistration error", e);
                throw new RuntimeException("Drivers deregistration error", e);
            }
        });
    }
}
