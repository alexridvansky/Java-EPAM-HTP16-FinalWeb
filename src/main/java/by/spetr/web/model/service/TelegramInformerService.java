package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.pool.TelegramConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URLConnection;


public class TelegramInformerService implements InformerService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sendMessage(String message) throws ServiceException {
        try {
            send(message);
        } catch (ServiceException e) {
            logger.error("Error while sending telegram message.");
        }
    }

    private void send(String message) throws ServiceException {
        try {
            URLConnection connection = TelegramConnectionFactory.createConnection(message);
            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            logger.debug("Notification sent. Answer is {}", response);
        } catch (IOException e) {
            logger.error("IOException, message can't be sent: {}", e.getMessage());
            throw new ServiceException("IOException, message can't be sent", e);
        }
    }
}
