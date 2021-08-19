package by.spetr.web.telegrambot;

import by.spetr.web.model.exception.ServiceException;

public interface InformerService {
    void sendMessage(String message) throws ServiceException;

    static InformerService getInstance() {
        return new TelegramInformerService();
    }
}
