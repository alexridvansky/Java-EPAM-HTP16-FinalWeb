package by.spetr.web.telegrambot;

import by.spetr.web.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramInformerService extends TelegramLongPollingBot implements InformerService{
    private static final Logger logger = LogManager.getLogger();
    private static final String BOT_TOKEN_PROPERTY = "telegram.bot_token";
    private static final String BOT_NAME_PROPERTY = "telegram.bot_name";
    private static final String BOT_CHAT_ID_PROPERTY = "telegram.chat_id";
    private static final String BOT_TOKEN;
    private static final String BOT_NAME;
    private static final String BOT_CHAT_ID;
    private static TelegramInformerService instance;

    static {
        BOT_TOKEN = PropertyUtil.getInstance().getBotProperty(BOT_TOKEN_PROPERTY);
        BOT_NAME = PropertyUtil.getInstance().getBotProperty(BOT_NAME_PROPERTY);
        BOT_CHAT_ID = PropertyUtil.getInstance().getBotProperty(BOT_CHAT_ID_PROPERTY);
    }


    public static TelegramInformerService getInstance() {
        if (instance == null) {
            instance = new TelegramInformerService();
        }
        return instance;
    }

    public void registerBot() {
        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chatId, String message) {
        try {
            execute(new SendMessage(BOT_CHAT_ID, message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPublicMessage(String message) {
        sendMessage(BOT_CHAT_ID, message);
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
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            try {
                execute(new SendMessage(String.valueOf(chatId), "echo: " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
