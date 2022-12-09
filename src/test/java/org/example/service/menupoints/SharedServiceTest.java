package org.example.service.menupoints;

import org.example.model.tables.TablePaymentSystems;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SharedServiceTest {

    @Test
    void goToProcessClickOnInlineButtonInMenuMyCards_ClickOnInlineButtonInMenuMyCards_ReturnMessageWithTextCommandStartOKKeyboardMenuMain_ClickInMenuMain() {

        TransmittedData transmittedData = new TransmittedData(0);
        transmittedData.getDataStorage().add("aaa",1);
        SendMessage message = new SendMessage();
        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuMain());

        SendMessage message1 = SharedService.goToProcessClickOnInlineButtonInMenuMyCards(transmittedData);

        Object expectedDataStorageElement = null;
        Object actualDataStorageElement = transmittedData.getDataStorage().get("aaa");

        InlineKeyboardMarkup expectedKeyboard = InlineKeyboardsMarkupStorage.getMenuMain();
        InlineKeyboardMarkup actualKeyboard = (InlineKeyboardMarkup) message1.getReplyMarkup();

        String expectedText = DialogStringsStorage.CommandStartOK;
        String actualText = message1.getText();

        State expectedState = State.ClickInMenuMain;
        State actualState  = transmittedData.getState();

        assertThat(expectedDataStorageElement).isEqualTo(actualDataStorageElement);
        assertThat(expectedKeyboard).isEqualTo(actualKeyboard);
        assertThat(expectedText).isEqualTo(actualText);
        assertThat(expectedState).isEqualTo(actualState);
    }
}