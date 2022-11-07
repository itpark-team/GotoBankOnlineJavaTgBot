package org.example.service;

import org.example.service.handlers.MenuPointMyCardsService;
import org.example.service.handlers.SharedService;
import org.example.service.handlers.MainMenuService;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private final Map<State, Service> methods;

    private final MainMenuService mainMenuService;
    private final MenuPointMyCardsService menuPointMyCardsService;

    public ServiceManager() {
        methods = new HashMap<>();

        mainMenuService = new MainMenuService();
        menuPointMyCardsService = new MenuPointMyCardsService();

        methods.put(State.WaitingCommandStart, mainMenuService::processCommandStart);
        methods.put(State.WaitingClickOnInlineButtonInMenuMain, mainMenuService::processClickOnInlineButtonInMenuMain);
        methods.put(State.WaitingClickOnInlineButtonInMenuMyCards, menuPointMyCardsService::processClickOnInlineButtonInMenuMyCards);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseSpecificCard, menuPointMyCardsService::processClickOnInlineButtonInMenuChooseSpecificCard);
        methods.put(State.WaitingInputIncomeMoneyForSpecificCard, menuPointMyCardsService::processInputIncomeMoneyForSpecificCard);
        methods.put(State.WaitingClickOnInlineButtonInMenuApproveDeleteSpecificCard, menuPointMyCardsService::processClickOnInlineButtonInMenuApproveDeleteSpecificCard);
        methods.put(State.WaitingClickOnInlineButtonInMenuChoosePaySystemForNewCard,menuPointMyCardsService::processClickOnInlineButtonInMenuChoosePaySystemForNewCard);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

}
