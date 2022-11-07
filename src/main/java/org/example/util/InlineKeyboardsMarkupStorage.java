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
        button.setText(ButtonsStorage.ButtonMyCardsInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.ButtonMyCardsInMenuMain.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonTransferMoneyInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.ButtonTransferMoneyInMenuMain.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonInstructionInMenuMain.getName());
        button.setCallbackData(ButtonsStorage.ButtonInstructionInMenuMain.getCallBackData());
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
        button.setText(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonBackInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonBackInMenuMyCard.getCallBackData());
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
        button.setText(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonAddNewCardInMenuMyCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonBackInMenuMyCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonBackInMenuMyCard.getCallBackData());
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
        button.setText(ButtonsStorage.ButtonAddMoneyToBalanceInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonAddMoneyToBalanceInMenuChooseSpecificCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonDeleteCardInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonDeleteCardInMenuChooseSpecificCard.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonBackInMenuChooseSpecificCard.getName());
        button.setCallbackData(ButtonsStorage.ButtonBackInMenuChooseSpecificCard.getCallBackData());
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
        button.setText(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardYes.getName());
        button.setCallbackData(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardYes.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardNo.getName());
        button.setCallbackData(ButtonsStorage.ButtonMenuApproveDeleteSpecificCardNo.getCallBackData());
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


}
