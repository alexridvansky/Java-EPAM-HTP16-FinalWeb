package by.spetr.web.telegrambot;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.UserService;
import by.spetr.web.util.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public final class TelegramInformerService extends TelegramLongPollingBot implements InformerService{
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserService.getInstance();
    // comment about placing tokens, sql-connection passwords and all that stuff into property and pushing to the git:
    // I know that it isn't the best way now to deal with it but it's the easiest way
    // how to share it with you. In a real situation i'd put it to the server's or vm's local variables
    private static final String BOT_TOKEN_PROPERTY = "telegram.bot_token";
    private static final String BOT_NAME_PROPERTY = "telegram.bot_name";
    private static final String BOT_CHAT_ID_PROPERTY = "telegram.chat_id";
    private static final String MAX_ATT_COUNT_PROPERTY = "telegram.confirm_count";
    private static final String ATT_EXPIRES_TIME_PROPERTY = "telegram.confirm_expires";
    private static final String BOT_TOKEN;
    private static final String BOT_NAME;
    private static final String BOT_CHAT_ID;
    private static final int MAX_ATT_COUNT;
    private static final int ATT_EXPIRES_TIME;
    private static TelegramInformerService instance;

    static {
        BOT_TOKEN = PropertyReader.getInstance().getBotProperty(BOT_TOKEN_PROPERTY);
        BOT_NAME = PropertyReader.getInstance().getBotProperty(BOT_NAME_PROPERTY);
        BOT_CHAT_ID = PropertyReader.getInstance().getBotProperty(BOT_CHAT_ID_PROPERTY);
        MAX_ATT_COUNT = Integer.parseInt(PropertyReader.getInstance().getBotProperty(MAX_ATT_COUNT_PROPERTY));
        ATT_EXPIRES_TIME = Integer.parseInt(PropertyReader.getInstance().getBotProperty(ATT_EXPIRES_TIME_PROPERTY));
    }

    public static TelegramInformerService getInstance() {
        if (instance == null) {
            instance = new TelegramInformerService();
        }
        return instance;
    }

    public void registerBot() {

    }

    public void setButton(SendMessage sendMessage) {

    }

    @Override
    public void sendMessage(String chatId, String text) {

    }

    @Override
    public void sendMessage(Message message, String text) {

    }

    @Override
    public void sendPublicMessage(String text) {
        sendMessage(BOT_CHAT_ID, text);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.debug("OnUpdateReceive");

    }

    private void confirm(Message message) {
        logger.debug("Confirm method");

    }

}
