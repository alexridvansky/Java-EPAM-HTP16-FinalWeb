package by.spetr.web.controller.listener;

import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.pool.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Listener is used to destroy connection pool while Servlet stops
 */
@WebListener
public class DestroyPoolListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Destroy Listener has been called. Destroying connection pool...");
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error("Error while destroying connection pool", e);
        }
    }
}
