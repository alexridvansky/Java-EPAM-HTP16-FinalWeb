package by.spetr.web.model.pool;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String URL_PROPERTY = "telegram.url";
    private static final String BOT_TOKEN_PROPERTY = "telegram.bot_token";
    private static final String CHAT_ID_PROPERTY = "telegram.chat_id";
    private static final String TELEGRAM_URL;
    private static final String BOT_TOKEN;
    private static final String CHAT_ID;

    static {
        TELEGRAM_URL = PropertyUtil.getInstance().getBotProperty(URL_PROPERTY);
        BOT_TOKEN = PropertyUtil.getInstance().getBotProperty(BOT_TOKEN_PROPERTY);
        CHAT_ID = PropertyUtil.getInstance().getBotProperty(CHAT_ID_PROPERTY);
    }

    private TelegramConnectionFactory() {
    }

    public static URLConnection createConnection(String urlText) throws ServiceException {
        String urlString = null;
        URLConnection connection;
        try {
            assert TELEGRAM_URL != null;
            assert BOT_TOKEN != null;
            assert CHAT_ID != null;
            urlString = TELEGRAM_URL;
            String text = URLEncoder.encode(urlText, StandardCharsets.UTF_8);
            urlString = String.format(urlString, BOT_TOKEN, CHAT_ID, text);
            URL url = new URL(urlString);
            connection = url.openConnection();
        } catch (IOException e) {
            logger.error("Exception in send telegram message with url: {}", urlString);
            throw new ServiceException("Exception in send telegram message", e);
        }
        return connection;
    }
}
