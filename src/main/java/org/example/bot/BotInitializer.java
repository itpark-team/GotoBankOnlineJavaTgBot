package org.example.bot;

import org.example.statemachine.ChatRouter;
import org.example.util.SystemStringsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotInitializer extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(BotInitializer.class);

    private ChatRouter chatRouter;

    public BotInitializer() throws Exception {
        chatRouter = new ChatRouter();
    }

    @Override
    public String getBotUsername() {
        return "GotoBankOnlineBot";
    }

    @Override
    public String getBotToken() {
        return "5634541269:AAG4mQjST-JbkOZJ5520AXdzmzTIFa6ZAQE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        int messageId = 0;
        String textData = SystemStringsStorage.Empty;
        String updateType = "";

        try {
            if (update.hasMessage()) {
                chatId = update.getMessage().getChatId();
                messageId = update.getMessage().getMessageId();
                textData = update.getMessage().getText();
                updateType = "Message";
            } else if (update.hasCallbackQuery()) {
                chatId = update.getCallbackQuery().getMessage().getChatId();
                messageId = update.getCallbackQuery().getMessage().getMessageId();
                textData = update.getCallbackQuery().getData();
                updateType = "Callback";
            }
            logger.info(String.format("INPUT: %s %d:%d:%s", updateType, chatId, messageId, textData));
            SendMessage message = chatRouter.route(chatId, textData);
            logger.info(String.format("OUTPUT: %d:%d:%s", chatId, messageId, message.getText()));
            execute(message);
        } catch (Exception e) {

            logger.info(String.format("ERROR: %s %d:%d:%s error=%s", updateType, chatId, messageId, textData, e.getMessage()));

            DeleteMessage message = new DeleteMessage();
            message.setChatId(chatId);
            message.setMessageId(messageId);

            try {
                execute(message);
            } catch (TelegramApiException telegramApiException) {
                telegramApiException.printStackTrace();
            }

        }
    }
}
