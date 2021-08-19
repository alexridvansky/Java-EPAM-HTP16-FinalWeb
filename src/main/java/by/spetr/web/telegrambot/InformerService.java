package by.spetr.web.telegrambot;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface InformerService {
    void sendMessage(String chatId, String text);

    void sendPublicMessage(String text);

    public void sendMessage(Message message, String text);

    static InformerService getInstance() {
        return new TelegramInformerService();
    }
}
