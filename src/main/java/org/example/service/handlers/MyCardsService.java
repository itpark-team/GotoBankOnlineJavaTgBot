package org.example.service.handlers;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MyCardsService {

    public SendMessage processClickOnInlineButtonInMenuMyCards(String callBackData, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonBackInMenuMyCard.getCallBackData())){
            message.setText(DialogStringsStorage.CommandStartOK);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getInlineKeyboardMarkupMenuMain());

            transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);

            return message;
        }else if(callBackData.equals(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getCallBackData())){



            throw new Exception("ввели хуйню");
        }else {
            long cardId = Long.parseLong(callBackData);
            Card card = DbManager.getInstance().getTableCards().getByCardId(cardId);
            PaymentSystem paymentSystem = DbManager.getInstance().getTablePaymentSystems().getById(card.getPaymentSystemId());

            message.setText(DialogStringsStorage.CreateMenuChooseSpecificCard(paymentSystem.getName(),card.getNumber(),card.getBalance()));
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getInlineKeyboardMarkupMenuChooseSpecificCard());

            return message;
        }


    }

}
