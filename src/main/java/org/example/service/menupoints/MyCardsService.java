package org.example.service.menupoints;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;
import java.util.List;

public class MyCardsService {
    
    private DbManager dbManager;

    public MyCardsService() throws Exception {
        dbManager = DbManager.getInstance();
    }

    public SendMessage processClickInMenuMyCards(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.BackInMenuMyCard.getCallBackData())) {
            return SharedService.goToProcessClickOnInlineButtonInMenuMyCards(transmittedData);
        } else if (callBackData.equals(ButtonsStorage.AddNewCardInMenuMyCard.getCallBackData())) {

            List<PaymentSystem> paymentSystemList = dbManager.getTablePaymentSystems().getAll();

            message.setText(DialogStringsStorage.ActionMenuChoosePaySystemForNewCard);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.createMenuChoosePaySystemForNewCard(paymentSystemList));

            transmittedData.setState(State.ClickInMenuChoosePaySystemForNewCard);
            return message;

        } else if (callBackData.startsWith(SystemStringsStorage.CallbackCardId)) {
            callBackData = callBackData.replace(SystemStringsStorage.CallbackCardId, "");
            int cardId = Integer.parseInt(callBackData);

            Card card = dbManager.getTableCards().getByCardId(cardId);
            PaymentSystem paymentSystem = dbManager.getTablePaymentSystems().getById(card.getPaymentSystemId());
            card.setPaymentSystem(paymentSystem);

            message.setText(DialogStringsStorage.createMenuChooseSpecificCard(card.getPaymentSystem().getName(), card.getNumber(), card.getBalance()));
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuChooseSpecificCard());

            transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCurrentCard, card);
            transmittedData.setState(State.ClickInMenuChooseSpecificCard);
            return message;
        }
        throw new Exception("Ошибка распознавания callBackData");
    }

    public SendMessage processClickInMenuChooseSpecificCard(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.AddMoneyToBalanceInMenuChooseSpecificCard.getCallBackData())) {
            message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCard);

            transmittedData.setState(State.InputIncomeMoneyForSpecificCard);
            return message;
        } else if (callBackData.equals(ButtonsStorage.DeleteCardInMenuChooseSpecificCard.getCallBackData())) {
            Card card = (Card) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCurrentCard);

            message.setText(DialogStringsStorage.createMenuApproveDeleteSpecificCard(card.getPaymentSystem().getName(), card.getNumber()));
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuApproveDeleteSpecificCard());

            transmittedData.setState(State.ClickInMenuApproveDeleteSpecificCard);
            return message;
        } else if (callBackData.equals(ButtonsStorage.BackInMenuChooseSpecificCard.getCallBackData())) {
            List<Card> cards = dbManager.getTableCards().getAllByChatId(transmittedData.getChatId());

            List<PaymentSystem> paymentSystems = dbManager.getTablePaymentSystems().getAll();
            cards.stream().forEach(
                    card -> card.setPaymentSystem(
                            paymentSystems.stream()
                                    .filter(paymentSystem -> paymentSystem.getId() == card.getPaymentSystemId()).findFirst().get()
                    )
            );

            message.setText(DialogStringsStorage.MenuMyCardsText);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.createMenuMyCardsHasCards(cards));

            transmittedData.setState(State.ClickInMenuMyCards);
            return message;
        }


        throw new Exception("Ошибка распознавания callBackData");
    }

    public SendMessage processInputIncomeMoneyForSpecificCard(String moneyAsString, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        BigDecimal money = new BigDecimal(0);
        try {
            money = new BigDecimal(moneyAsString);
        } catch (Exception e) {
            message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCardFailInput);
            return message;
        }

        if (money.compareTo(Constants.MinMoney) < 0 || money.compareTo(Constants.MaxMoney) > 0) {
            message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCardOutOfRange);
            return message;
        }

        Card card = (Card) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCurrentCard);
        dbManager.getTableCards().depositMoneyToBalanceByCardId(card.getId(), money);

        message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCardOk);

        transmittedData.setState(State.CommandStart);
        return message;
    }

    public SendMessage processClickInMenuApproveDeleteSpecificCard(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.MenuApproveDeleteSpecificCardYes.getCallBackData())) {

            Card card = (Card) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCurrentCard);
            dbManager.getTableCards().deleteByCardId(card.getId());

            message.setText(DialogStringsStorage.ActionApproveDeleteSpecificCardYes);

            transmittedData.setState(State.CommandStart);
            return message;
        } else if (callBackData.equals(ButtonsStorage.MenuApproveDeleteSpecificCardNo.getCallBackData())) {

            message.setText(DialogStringsStorage.ActionApproveDeleteSpecificCardNo);

            transmittedData.setState(State.CommandStart);
            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }

    public SendMessage processClickInMenuChoosePaySystemForNewCard(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.startsWith(SystemStringsStorage.CallbackPaymentSystemsId)) {
            callBackData = callBackData.replace(SystemStringsStorage.CallbackPaymentSystemsId, "");
            int paymentSystemId = Integer.parseInt(callBackData);
            PaymentSystem paymentSystem = dbManager.getTablePaymentSystems().getById(paymentSystemId);

            long cardNumber = 0L;
            do {
                cardNumber = (long) (Math.random() * 10000000000000000L);
            } while (dbManager.getTableCards().hasCardWithNumber(cardNumber));

            Card card = new Card(0, transmittedData.getChatId(), Constants.DefaultNewCardBalance, cardNumber, paymentSystemId);

            dbManager.getTableCards().addNew(card);

            message.setText(DialogStringsStorage.createMenuChoosePaySystemForNewCard(paymentSystem.getName(), cardNumber));

            transmittedData.setState(State.CommandStart);
            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }
}



