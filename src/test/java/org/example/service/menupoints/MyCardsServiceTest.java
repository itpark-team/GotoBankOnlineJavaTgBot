package org.example.service.menupoints;

import org.example.model.DbManager;
import org.example.model.entities.PaymentSystem;
import org.example.model.tables.TablePaymentSystems;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MyCardsServiceTest {
    @Test
    public void processClickInMenuMyCards_ClickToAddNewCardInMenuMyCard_ReturnMessageWithTextChoosePaySystemAndKeyboardChoosePaySystem_ForNewCard() throws Exception {
        //подготовка
        String callBackData = ButtonsStorage.AddNewCardInMenuMyCard.getCallBackData();
        TransmittedData transmittedData = new TransmittedData(0);

        TablePaymentSystems tablePaymentSystems = Mockito.mock(TablePaymentSystems.class);

        List<PaymentSystem> paymentSystems = new ArrayList<>();
        Collections.addAll(paymentSystems,
                new PaymentSystem(1,"AAA"),
                new PaymentSystem(2,"BBB"));

        when(tablePaymentSystems.getAll()).thenReturn(paymentSystems);

        DbManager dbManager = new DbManager(tablePaymentSystems, null);
        MyCardsService myCardsService = new MyCardsService(dbManager);

        //тест
        SendMessage message = myCardsService.processClickInMenuMyCards(callBackData, transmittedData);

        String expectedText = DialogStringsStorage.ActionMenuChoosePaySystemForNewCard;
        String actualText = message.getText();

        InlineKeyboardMarkup expectedKeyboard = InlineKeyboardsMarkupStorage.createMenuChoosePaySystemForNewCard(paymentSystems);
        InlineKeyboardMarkup actualKeyboard = (InlineKeyboardMarkup) message.getReplyMarkup();

        State expectedState = State.ClickInMenuChoosePaySystemForNewCard;
        State actualState = transmittedData.getState();

        //проверка
        assertThat(expectedText).isEqualTo(actualText);

        assertThat(expectedKeyboard).isEqualTo(actualKeyboard);

        assertThat(expectedState).isEqualTo(actualState);
    }
}