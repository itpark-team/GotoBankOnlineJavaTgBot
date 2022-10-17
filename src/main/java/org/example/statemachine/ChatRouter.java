package org.example.statemachine;

import org.example.service.ServiceManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ChatRouter {
    private Map<Long, TransmittedData> chats;
    private ServiceManager serviceManager;

    public ChatRouter() {
        chats = new HashMap<>();
        serviceManager = new ServiceManager();
    }

    public void route(long chatId, Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        if (!chats.containsKey(chatId)) {
            chats.put(chatId, new TransmittedData());
        }

        TransmittedData transmittedData = chats.get(chatId);

        serviceManager.processUpdate(chatId, transmittedData, update, bot);
    }

}
