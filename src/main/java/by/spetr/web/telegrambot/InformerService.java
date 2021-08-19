package by.spetr.web.telegrambot;

public interface InformerService {
    void sendMessage(String chatId, String message);

    void sendPublicMessage(String message);

    static InformerService getInstance() {
        return new TelegramInformerService();
    }
}
