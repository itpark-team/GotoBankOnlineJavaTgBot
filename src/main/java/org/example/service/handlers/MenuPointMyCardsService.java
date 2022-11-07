package org.example.service.handlers;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;
import java.util.List;

public class MenuPointMyCardsService {

    public SendMessage processClickOnInlineButtonInMenuMyCards(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonBackInMenuMyCard.getCallBackData())) {
            return SharedService.goToProcessClickOnInlineButtonInMenuMyCards(transmittedData);
        } else if (callBackData.equals(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getCallBackData())) {

            List<PaymentSystem> paymentSystemList = DbManager.getInstance().getTablePaymentSystems().getAll();

            message.setText(DialogStringsStorage.ActionMenuChoosePaySystemForNewCard);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.createMenuChoosePaySystemForNewCard(paymentSystemList));

            transmittedData.setState(State.WaitingClickOnInlineButtonInMenuChoosePaySystemForNewCard);

            return message;

        } else if (callBackData.startsWith(SystemStringsStorage.CallbackCardId)) {
            callBackData = callBackData.replace(SystemStringsStorage.CallbackCardId, "");
            int cardId = Integer.parseInt(callBackData);

            Card card = DbManager.getInstance().getTableCards().getByCardId(cardId);
            PaymentSystem paymentSystem = DbManager.getInstance().getTablePaymentSystems().getById(card.getPaymentSystemId());
            card.setPaymentSystem(paymentSystem);

            transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCurrentCard, card);

            message.setText(DialogStringsStorage.createMenuChooseSpecificCard(card.getPaymentSystem().getName(), card.getNumber(), card.getBalance()));
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuChooseSpecificCard());

            transmittedData.setState(State.WaitingClickOnInlineButtonInMenuChooseSpecificCard);
            return message;
        }
        throw new Exception("ввели хуйню");
    }

    public SendMessage processClickOnInlineButtonInMenuChooseSpecificCard(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonAddMoneyToBalanceInMenuChooseSpecificCard.getCallBackData())) {
            message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCard);

            transmittedData.setState(State.WaitingInputIncomeMoneyForSpecificCard);
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonDeleteCardInMenuChooseSpecificCard.getCallBackData())) {
            Card card = (Card) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCurrentCard);

            message.setText(DialogStringsStorage.createMenuApproveDeleteSpecificCard(card.getPaymentSystem().getName(), card.getNumber()));
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuApproveDeleteSpecificCard());

            transmittedData.setState(State.WaitingClickOnInlineButtonInMenuApproveDeleteSpecificCard);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonBackInMenuChooseSpecificCard.getCallBackData())) {
            message.setText("Кнопка назад");
            return message;
        }


        throw new Exception("ввели хуйню");
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
        DbManager.getInstance().getTableCards().depositMoneyToBalanceByCardId(card.getId(), money);

        message.setText(DialogStringsStorage.ActionIncomeMoneyForSpecificCardOk);

        transmittedData.setState(State.WaitingCommandStart);
        return message;
    }

    public SendMessage processClickOnInlineButtonInMenuApproveDeleteSpecificCard(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardYes.getCallBackData())) {

            Card card = (Card) transmittedData.getDataStorage().get(SystemStringsStorage.DataStorageCurrentCard);
            DbManager.getInstance().getTableCards().deleteByCardId(card.getId());

            message.setText(DialogStringsStorage.ActionApproveDeleteSpecificCardYes);

            transmittedData.setState(State.WaitingCommandStart);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardNo.getCallBackData())) {

            message.setText(DialogStringsStorage.ActionApproveDeleteSpecificCardNo);

            transmittedData.setState(State.WaitingCommandStart);

            return message;
        }

        throw new Exception("ввели хуйню");
    }

    public SendMessage processClickOnInlineButtonInMenuChoosePaySystemForNewCard(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.startsWith(SystemStringsStorage.CallbackPaymentSystemsId)) {
            callBackData = callBackData.replace(SystemStringsStorage.CallbackPaymentSystemsId, "");
            int paymentSystemId = Integer.parseInt(callBackData);
            PaymentSystem paymentSystem = DbManager.getInstance().getTablePaymentSystems().getById(paymentSystemId);

            long cardNumber = 0L;
            do {
                cardNumber = (long) (Math.random() * 10000000000000000L);
            } while (DbManager.getInstance().getTableCards().hasCardWithNumber(cardNumber));

            Card card = new Card(0, transmittedData.getChatId(), Constants.DefaultNewCardBalance, cardNumber, paymentSystemId);

            DbManager.getInstance().getTableCards().addNew(card);

            message.setText(DialogStringsStorage.createMenuChoosePaySystemForNewCard(paymentSystem.getName(), cardNumber));

            transmittedData.setState(State.WaitingCommandStart);
            return message;
        }

        throw new Exception("ввели хуйню");
    }
}



