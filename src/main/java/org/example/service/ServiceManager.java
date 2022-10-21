package org.example.service;

import org.example.service.handlers.MainMenuService;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<State, Service> methods;
    private MainMenuService mainMenuService;

    public ServiceManager() {
        methods = new HashMap<>();

        mainMenuService = new MainMenuService();

        methods.put(State.WaitingCommandStart, mainMenuService::processCommandStart);
    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }
}
