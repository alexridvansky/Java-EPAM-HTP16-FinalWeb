package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;

public interface BotInformerService {
    void sendMessage(String message) throws ServiceException;

    static BotInformerService getInstance() {
        return new TelegramBotInformerService();
    }
}
