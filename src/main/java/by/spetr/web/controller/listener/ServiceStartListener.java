package by.spetr.web.controller.listener;

import by.spetr.web.model.pool.ConnectionPool;
import by.spetr.web.telegrambot.TelegramInformerService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceStartListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();
    TelegramInformerService telegramInformerService = TelegramInformerService.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Init listener");
        ConnectionPool.getInstance();
        logger.debug("Pool started");
        telegramInformerService.registerBot();
        logger.debug("Telegram bot started");
    }
}
