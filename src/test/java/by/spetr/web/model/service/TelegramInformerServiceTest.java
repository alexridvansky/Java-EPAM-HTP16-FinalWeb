package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;
import org.testng.annotations.Test;

public class TelegramInformerServiceTest {

    String message = "Test message";

    @Test
    public void testSendMessage() {
        TelegramInformerService service = new TelegramInformerService();
        try {
            service.sendMessage(message);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}