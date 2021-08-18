package by.spetr.web.model.pool;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.TelegramBotInformerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TelegramConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String RESOURCE = "bot.properties";
    private static final String URL_PROPERTY = "telegram.url";
    private static final String BOT_TOKEN_PROPERTY = "telegram.bot_token";
    private static final String CHAT_ID_PROPERTY = "telegram.chat_id";
    private static final String TELEGRAM_URL;
    private static final String BOT_TOKEN;
    private static final String CHAT_ID;

    static {
        try (InputStream inputStream = TelegramBotInformerService.class.getClassLoader()
                .getResourceAsStream(RESOURCE)) {
            if (inputStream == null) {
                logger.fatal("Can't find property file by name: " + RESOURCE);
                throw new RuntimeException("fatal: can't find property file by name: " + RESOURCE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.fatal("Can't load properties: ", e);
            throw new RuntimeException("Can't load properties: ", e);
        }
        TELEGRAM_URL = (String) properties.get(URL_PROPERTY);
        BOT_TOKEN = (String) properties.get(BOT_TOKEN_PROPERTY);
        CHAT_ID = (String) properties.get(CHAT_ID_PROPERTY);
    }

    private TelegramConnectionFactory() {
    }

    public static URLConnection createConnection(String urlText) throws ServiceException {
        String urlString = TELEGRAM_URL;
        String text = URLEncoder.encode(urlText, StandardCharsets.UTF_8);
        urlString = String.format(urlString, BOT_TOKEN, CHAT_ID, text);
        URLConnection connection;
        try {
            URL url = new URL(urlString);
            connection = url.openConnection();
        } catch (IOException e) {
            logger.error("Exception in send telegram message with url: {}", urlString);
            throw new ServiceException("Exception in send telegram message", e);
        }
        return connection;
    }
}
