package by.spetr.web.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConnectionCheckerTimerTask extends TimerTask {
    private static final Logger logger = LogManager.getLogger();
    private final Lock lock;
    private final Condition condition;
    private final ConnectionPool pool = ConnectionPool.getInstance();

    ConnectionCheckerTimerTask(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            logger.info("ConnectionPool counter service been called");
            TimeUnit.MILLISECONDS.sleep(100);
            logger.info("Connection pool is gonna be put into service mode");

            // putting Pool in a service mode
            pool.setServiceModeOn();

            if (pool.getBusyConnectionPoolSize() + pool.getFreeConnectionPoolSize() != ConnectionCreator.POOL_SIZE) {
                logger.error("There are not enough connections, gonna add some...");
                pool.addNewConnection();
            }

            lock.lock();
            // notify all treads
            condition.signalAll();

        } catch (InterruptedException ignored) {
            logger.info("Sleep been interrupted");
        }
        finally {
            logger.info("releasing locks");
            lock.unlock();
            logger.info("removing service mode flag");
            pool.setServiceModeOff();
        }

    }
}