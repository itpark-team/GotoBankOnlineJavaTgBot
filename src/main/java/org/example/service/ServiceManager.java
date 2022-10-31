package org.example.service;

import org.example.service.handlers.MyCardsService;
import org.example.service.handlers.StaticService;
import org.example.service.handlers.MainMenuService;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private final Map<State, Service> methods;
    private final MainMenuService mainMenuService;
    private final MyCardsService myCardsService;
    private final StaticService staticService;


    public ServiceManager() {
        staticService = new StaticService();
        methods = new HashMap<>();
        mainMenuService = new MainMenuService();
        myCardsService = new MyCardsService();
        methods.put(State.WaitingCommandStart, mainMenuService::processCommandStart);
        methods.put(State.WaitingClickOnInlineButtonInMenuMain, mainMenuService::processClickOnInlineButtonInMenuMain);
        methods.put(State.WaitingClickOnInlineButtonInMenuMyCards, myCardsService::processClickOnInlineButtonInMenuMyCards);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseSpecificCard, myCardsService::processClickOnInlineButtonInMenuChooseSpecificCard);
        methods.put(State.WaitingInputIncomeMoneyForSpecificCard, myCardsService ::processInputIncomeMoneyForSpecificCard);
    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

    public StaticService getStaticService() {
        return staticService;
    }
}
