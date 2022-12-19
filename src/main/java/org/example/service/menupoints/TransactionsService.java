package org.example.service.menupoints;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.Constants;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;

public class TransactionsService {

    private DbManager dbManager;

    public TransactionsService(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public SendMessage processClickNumberCardFromForTransaction(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.startsWith(SystemStringsStorage.CallbackCardId)) {
            callBackData = callBackData.replace(SystemStringsStorage.CallbackCardId, "");
            int cardId = Integer.parseInt(callBackData);

            transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCardIdFrom, cardId);

            Card card = dbManager.getTableCards().getByCardId(cardId);
            PaymentSystem paymentSystem = dbManager.getTablePaymentSystems().getById(card.getPaymentSystemId());

            message.setText(DialogStringsStorage.createInputNumberCardToForTransaction(paymentSystem.getName(), card.getNumber(), card.getBalance()));

            transmittedData.setState(State.InputNumberCardToForTransaction);
            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }

    public SendMessage processInputNumberCardToForTransaction(String cardNumberAsString, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        long cardNumberTo = 0;
        try {
            cardNumberTo = Long.parseLong(cardNumberAsString);
        } catch (Exception e) {
            message.setText(DialogStringsStorage.ActionIncomeNumberCardToForTransactionFailInput);
            return message;
        }

        if (!dbManager.getTableCards().hasCardWithNumber(cardNumberTo)) {
            message.setText(DialogStringsStorage.ActionIncomeNumberCardToForTransactionNoCard);
            return message;
        }

        int cardId = dbManager.getTableCards().getByNumber(cardNumberTo).getId();

        transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCardIdTo, cardId);

        message.setText(DialogStringsStorage.ActionInputMoneyForTransaction);

        transmittedData.setState(State.InputMoneyForTransaction);
        return message;
    }

    public SendMessage processInputMoneyForTransaction(String moneyAsString, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        BigDecimal money = new BigDecimal(0);
        try {
            money = new BigDecimal(moneyAsString);
        } catch (Exception e) {
            message.setText(DialogStringsStorage.ActionIncomeMoneyForTransactionFailInput);
            return message;
        }

        int cardIdFrom = (int) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCardIdFrom);

        Card cardFrom = dbManager.getTableCards().getByCardId(cardIdFrom);

        if (money.compareTo(Constants.Zero) < 0 || money.compareTo(cardFrom.getBalance()) > 0) {
            message.setText(DialogStringsStorage.createIncomeMoneyForTransactionOutOfRange(cardFrom.getBalance()));
            return message;
        }

        int cardIdTo = (int) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCardIdTo);

        dbManager.getTableCards().takeOffMoneyFromBalanceByCardId(cardIdFrom, money);
        dbManager.getTableCards().depositMoneyToBalanceByCardId(cardIdTo, money);

        Card cardFromAfterTransaction = dbManager.getTableCards().getByCardId(cardIdFrom);
        PaymentSystem paymentSystem = dbManager.getTablePaymentSystems().getById(cardFromAfterTransaction.getPaymentSystemId());

        message.setText(DialogStringsStorage.createInputMoneyForTransactionOk(paymentSystem.getName(),cardFromAfterTransaction.getNumber(),cardFromAfterTransaction.getBalance()));

        message.setReplyMarkup(InlineKeyboardsMarkupStorage.getGoToMainMenuShared());

        transmittedData.setState(State.GoToMainMenuBySharedInlineButton);
        return message;
    }
}
