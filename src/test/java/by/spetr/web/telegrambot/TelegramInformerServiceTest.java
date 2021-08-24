package by.spetr.web.telegrambot;

import org.testng.annotations.Test;

public class TelegramInformerServiceTest {
    private static final TelegramInformerService TELEGRAM_INFORMER_SERVICE = TelegramInformerService.getInstance();

    @Test
    public void testSendMessage() {
        TELEGRAM_INFORMER_SERVICE.sendPublicMessage("test public message");
    }
}