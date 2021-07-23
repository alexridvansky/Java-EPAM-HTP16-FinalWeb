package by.spetr.web.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static by.spetr.web.model.pool.ConnectionCreator.POOL_SIZE;

public class ConnectionCheckerTimerTask extends TimerTask {
    private static final Logger logger = LogManager.getLogger();
    private final Lock lock;
    private final Condition condition;

    ConnectionCheckerTimerTask(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            logger.info("ConnectionPool counter service been called");
            TimeUnit.MILLISECONDS.sleep(100);
            logger.info("ConnectionPool pool is gonna be put into service mode");

            // putting Pool in a service mode
            pool.setServiceModeOn();

            logger.info("Actual pool size {}/{}", pool.getActualPoolSize(), POOL_SIZE);

            if (pool.getActualPoolSize() != POOL_SIZE) {
                for (int i = 0; i < POOL_SIZE - pool.getActualPoolSize(); i++) {
                    pool.addNewConnection();
                }
            }

            lock.lock();
            // notify all treads

        } catch (InterruptedException ignored) {
            logger.info("Sleep been interrupted");
        } finally {
            logger.info("releasing locks");
            condition.signalAll();
            lock.unlock();
            logger.info("removing service mode flag");
            pool.setServiceModeOff();
        }
    }
}