package org.example.service.handlers;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class MainMenuService {
    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.getInlineKeyboardMarkupMenuMain());

        transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);

        return message;
    }

    public SendMessage processClickOnInlineButtonInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonMyCardsInMenuMain.getCallBackData())) {

            List<Card> cards = DbManager.getInstance().getTableCards().getAllByChatId(transmittedData.getChatId());

            if (cards.size() == 0) {
                message.setText(DialogStringsStorage.MenuMyCardsText + "\n" + DialogStringsStorage.MenuMyCardsNoCardsText);
                message.setReplyMarkup(InlineKeyboardsMarkupStorage.getInlineKeyboardMarkupMenuMyCardsNoCards());
            } else {
                List<PaymentSystem> paymentSystems = DbManager.getInstance().getTablePaymentSystems().getAll();
                cards.stream().forEach(
                        card -> card.setPaymentSystem(
                                paymentSystems.stream()
                                        .filter(paymentSystem -> paymentSystem.getId() == card.getPaymentSystemId()).findFirst().get()
                        )
                );

                message.setText(DialogStringsStorage.MenuMyCardsText);
                message.setReplyMarkup(InlineKeyboardsMarkupStorage.createInlineKeyboardMarkupMenuMyCardsHasCards(cards));
            }

            transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMyCards);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonTransferMoneyInMenuMain.getCallBackData())) {
            message.setText("нажали на перевод денег");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonInstructionInMenuMain.getCallBackData())) {
            message.setText("нажали на инструкцию");
            return message;
        }

        throw new Exception("ввели хуйню");
    }
}
