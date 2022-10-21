package org.example.statemachine;

import org.example.service.ServiceManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    public SendMessage route(long chatId, String textData) {
        if (!chats.containsKey(chatId)) {
            chats.put(chatId, new TransmittedData(chatId));
        }

        TransmittedData transmittedData = chats.get(chatId);

        return serviceManager.processUpdate(textData, transmittedData);
    }

}
