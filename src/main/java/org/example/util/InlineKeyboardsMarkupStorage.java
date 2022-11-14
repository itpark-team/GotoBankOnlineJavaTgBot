package org.example.util;

import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardsMarkupStorage {
    public static InlineKeyboardMarkup getMenuMain() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.MyCardsInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.MyCardsInMenuMain.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.CurrentExchangeRateInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.CurrentExchangeRateInMenuMain.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.TransferMoneyInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.TransferMoneyInMenuMain.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.InstructionInMenuMain.getName());
        button.setUrl("https://docs.google.com/document/d/1FhUwceal_RMtM636MUQ08QFYppJj5VDz0pMdTZ65zRw/edit?usp=sharing");
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getMenuMyCardsNoCards() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.AddNewCardInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.AddNewCardInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.BackInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.BackInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;

    }

    public static InlineKeyboardMarkup createMenuMyCardsHasCards(List<Card> cards) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        for (int i = 0; i < cards.size(); i++) {
            row = new ArrayList<>();
            button = new InlineKeyboardButton();
            button.setText(cards.get(i).getPaymentSystem().getName() + " " + cards.get(i).getNumber());
            button.setCallbackData(SystemStringsStorage.CallbackCardId + cards.get(i).getId());
            row.add(button);
            keyboard.add(row);
        }

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.AddNewCardInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.AddNewCardInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.BackInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.BackInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;

    }

    public static InlineKeyboardMarkup getMenuChooseSpecificCard() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.AddMoneyToBalanceInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.AddMoneyToBalanceInMenuChooseSpecificCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.DeleteCardInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.DeleteCardInMenuChooseSpecificCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.BackInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.BackInMenuChooseSpecificCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getMenuApproveDeleteSpecificCard() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.MenuApproveDeleteSpecificCardYes.getName());
        button.setCallbackData(ButtonsStorage.MenuApproveDeleteSpecificCardYes.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.MenuApproveDeleteSpecificCardNo.getName());
        button.setCallbackData(ButtonsStorage.MenuApproveDeleteSpecificCardNo.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup createMenuChoosePaySystemForNewCard(List<PaymentSystem> paymentSystems) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        for (int i = 0; i < paymentSystems.size(); i++) {
            row = new ArrayList<>();
            button = new InlineKeyboardButton();
            button.setText(paymentSystems.get(i).getName());
            button.setCallbackData(SystemStringsStorage.CallbackPaymentSystemsId + paymentSystems.get(i).getId());
            row.add(button);
            keyboard.add(row);
        }

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;

    }

    public static InlineKeyboardMarkup createMenuTransactionsHasCards(List<Card> cards) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row;
        InlineKeyboardButton button;

        for (int i = 0; i < cards.size(); i++) {
            row = new ArrayList<>();
            button = new InlineKeyboardButton();
            button.setText(cards.get(i).getPaymentSystem().getName() + " " + cards.get(i).getNumber() + "\t₽" + cards.get(i).getBalance());
            button.setCallbackData(SystemStringsStorage.CallbackCardId + cards.get(i).getId());
            row.add(button);
            keyboard.add(row);
        }

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;

    }

}
