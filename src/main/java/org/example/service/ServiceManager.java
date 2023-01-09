package org.example.service;

import org.example.model.DbManager;
import org.example.model.connection.HibernateSession;
import org.example.model.connection.JdbcConnection;
import org.example.model.tables.*;
import org.example.service.menupoints.MyCardsService;
import org.example.service.menupoints.MainMenuService;
import org.example.service.menupoints.SharedService;
import org.example.service.menupoints.TransactionsService;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.SystemStringsStorage;
import org.hibernate.SessionFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ServiceManager implements Service {
    private final Map<State, Service> methods;

    private final MainMenuService mainMenuService;
    private final MyCardsService myCardsService;
    private final TransactionsService transactionsService;

    private final DbManager dbManager;

    public ServiceManager() throws Exception {
//        Connection connection = new JdbcConnection(
//                SystemStringsStorage.DbUrl,
//                SystemStringsStorage.DbUser,
//                SystemStringsStorage.DbPassword).getConnection();
//
//        TableCards tableCards = new TableCardsJdbcImpl(connection);
//        TablePaymentSystems tablePaymentSystems = new TablePaymentSystemsJdbcImpl(connection);

        SessionFactory sessionFactory = HibernateSession.getInstance().getSessionFactory();

        TableCards tableCards = new TableCardsHiberImpl(sessionFactory);
        TablePaymentSystems tablePaymentSystems = new TablePaymentSystemsHiberImpl(sessionFactory);

        dbManager = new DbManager(tablePaymentSystems, tableCards);

        methods = new HashMap<>();

        mainMenuService = new MainMenuService(dbManager);
        myCardsService = new MyCardsService(dbManager);
        transactionsService = new TransactionsService(dbManager);

        methods.put(State.CommandStart, mainMenuService::processCommandStart);
        methods.put(State.ClickInMenuMain, mainMenuService::processClickInMenuMain);

        methods.put(State.ClickInMenuMyCards, myCardsService::processClickInMenuMyCards);
        methods.put(State.ClickInMenuChooseSpecificCard, myCardsService::processClickInMenuChooseSpecificCard);
        methods.put(State.InputIncomeMoneyForSpecificCard, myCardsService::processInputIncomeMoneyForSpecificCard);
        methods.put(State.ClickInMenuApproveDeleteSpecificCard, myCardsService::processClickInMenuApproveDeleteSpecificCard);
        methods.put(State.ClickInMenuChoosePaySystemForNewCard, myCardsService::processClickInMenuChoosePaySystemForNewCard);

        methods.put(State.ClickNumberCardFromForTransaction, transactionsService::processClickNumberCardFromForTransaction);
        methods.put(State.InputNumberCardToForTransaction, transactionsService::processInputNumberCardToForTransaction);
        methods.put(State.InputMoneyForTransaction, transactionsService::processInputMoneyForTransaction);

        methods.put(State.GoToMainMenuBySharedInlineButton, SharedService::processGoToMenuMainByInlineButton);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

}
