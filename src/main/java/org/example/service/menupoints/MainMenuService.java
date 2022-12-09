package org.example.service.menupoints;

import org.example.api.CbrApiWorker;
import org.example.api.ExchangeRate;
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
    private DbManager dbManager;

    public MainMenuService(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        return SharedService.goToProcessClickOnInlineButtonInMenuMyCards(transmittedData);
    }

    public SendMessage processClickInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.MyCardsInMenuMain.getCallBackData())) {

            List<Card> cards = dbManager.getTableCards().getAllByChatId(transmittedData.getChatId());

            if (cards.size() == 0) {
                message.setText(DialogStringsStorage.MenuMyCardsText + "\n" + DialogStringsStorage.MenuMyCardsNoCardsText);
                message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuMyCardsNoCards());
            } else {
                List<PaymentSystem> paymentSystems = dbManager.getTablePaymentSystems().getAll();
                cards.stream().forEach(
                        card -> card.setPaymentSystem(
                                paymentSystems.stream()
                                        .filter(paymentSystem -> paymentSystem.getId() == card.getPaymentSystemId()).findFirst().get()
                        )
                );

                message.setText(DialogStringsStorage.MenuMyCardsText);
                message.setReplyMarkup(InlineKeyboardsMarkupStorage.createMenuMyCardsHasCards(cards));
            }

            transmittedData.setState(State.ClickInMenuMyCards);
            return message;
        } else if (callBackData.equals(ButtonsStorage.CurrentExchangeRateInMenuMain.getCallBackData())) {

            CbrApiWorker cbrApiWorker = new CbrApiWorker();
            String json = cbrApiWorker.getJsonCurrentExchangeRate();
            ExchangeRate exchangeRate = cbrApiWorker.parseCurrentExchangeRate(json);

            message.setText(DialogStringsStorage.createCurrentExchangeRate(exchangeRate.getDateAsString(), exchangeRate.getUsd(), exchangeRate.getEur()));
            transmittedData.setState(State.CommandStart);
            return message;
        } else if (callBackData.equals(ButtonsStorage.TransferMoneyInMenuMain.getCallBackData())) {

            List<Card> cards = dbManager.getTableCards().getAllByChatId(transmittedData.getChatId());

            if (cards.size() == 0) {
                message.setText(DialogStringsStorage.MenuTransactionsNoCardsText);
            } else {
                List<PaymentSystem> paymentSystems = dbManager.getTablePaymentSystems().getAll();
                cards.stream().forEach(
                        card -> card.setPaymentSystem(
                                paymentSystems.stream()
                                        .filter(paymentSystem -> paymentSystem.getId() == card.getPaymentSystemId()).findFirst().get()
                        )
                );

                message.setText(DialogStringsStorage.MenuTransactionsExistCardsText);
                message.setReplyMarkup(InlineKeyboardsMarkupStorage.createMenuTransactionsHasCards(cards));

                transmittedData.setState(State.ClickNumberCardFromForTransaction);
            }

            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }
}
