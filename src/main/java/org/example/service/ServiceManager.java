package org.example.service;

import org.example.service.menupoints.MyCardsService;
import org.example.service.menupoints.MainMenuService;
import org.example.service.menupoints.TransactionsService;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private final Map<State, Service> methods;

    private final MainMenuService mainMenuService;
    private final MyCardsService myCardsService;
    private final TransactionsService transactionsService;


    public ServiceManager() throws Exception {
        methods = new HashMap<>();

        mainMenuService = new MainMenuService();
        myCardsService = new MyCardsService();
        transactionsService = new TransactionsService();

        methods.put(State.CommandStart, mainMenuService::processCommandStart);
        methods.put(State.ClickInMenuMain, mainMenuService::processClickInMenuMain);

        methods.put(State.ClickInMenuMyCards, myCardsService::processClickInMenuMyCards);
        methods.put(State.ClickInMenuChooseSpecificCard, myCardsService::processClickInMenuChooseSpecificCard);
        methods.put(State.InputIncomeMoneyForSpecificCard, myCardsService::processInputIncomeMoneyForSpecificCard);
        methods.put(State.ClickInMenuApproveDeleteSpecificCard, myCardsService::processClickInMenuApproveDeleteSpecificCard);
        methods.put(State.ClickInMenuChoosePaySystemForNewCard, myCardsService::processClickInMenuChoosePaySystemForNewCard);

        methods.put(State.ClickNumberCardFromForTransaction,transactionsService::processClickNumberCardFromForTransaction);
        methods.put(State.InputNumberCardToForTransaction,transactionsService::processInputNumberCardToForTransaction);
        methods.put(State.InputMoneyForTransaction,transactionsService::processInputMoneyForTransaction);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

}
