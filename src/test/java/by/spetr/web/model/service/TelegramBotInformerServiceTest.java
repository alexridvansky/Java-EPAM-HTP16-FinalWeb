package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;
import org.testng.annotations.Test;

public class TelegramBotInformerServiceTest {

    String message = "Test message";

    @Test
    public void testSendMessage() {
        TelegramBotInformerService service = new TelegramBotInformerService();
        try {
            service.sendMessage(message);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}