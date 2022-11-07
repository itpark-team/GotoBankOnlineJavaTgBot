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

        methods.put(State.WaitingCommandStart, mainMenuService::processCommandStart);
        methods.put(State.WaitingClickInMenuMain, mainMenuService::processClickInMenuMain);

        methods.put(State.WaitingClickInMenuMyCards, myCardsService::processClickInMenuMyCards);
        methods.put(State.WaitingClickInMenuChooseSpecificCard, myCardsService::processClickInMenuChooseSpecificCard);
        methods.put(State.WaitingInputIncomeMoneyForSpecificCard, myCardsService::processInputIncomeMoneyForSpecificCard);
        methods.put(State.WaitingClickInMenuApproveDeleteSpecificCard, myCardsService::processClickInMenuApproveDeleteSpecificCard);
        methods.put(State.WaitingClickInMenuChoosePaySystemForNewCard, myCardsService::processClickInMenuChoosePaySystemForNewCard);

        methods.put(State.WaitingClickNumberCardFromForTransaction,transactionsService::processClickNumberCardFromForTransaction);
        methods.put(State.WaitingInputNumberCardToForTransaction,transactionsService::processInputNumberCardToForTransaction);
        methods.put(State.WaitingInputMoneyForTransaction,transactionsService::processInputMoneyForTransaction);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

}
