package by.spetr.web.telegrambot;

import org.testng.annotations.Test;

public class TelegramBotServiceTest {
    private static final TelegramBotService telegramBotService = TelegramBotService.getInstance();

    @Test
    public void testSendMessage() {
        telegramBotService.sendPublicMessage("test public message");
    }
}