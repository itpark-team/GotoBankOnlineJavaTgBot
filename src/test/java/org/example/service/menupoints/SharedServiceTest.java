package org.example.service.menupoints;

import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static org.assertj.core.api.Assertions.assertThat;

class SharedServiceTest {

    @Test
    void goToProcessClickOnInlineButtonInMenuMyCards_ClickOnInlineButtonInMenuMyCards_ReturnMessageWithTextCommandStartOKKeyboardMenuMain_ClickInMenuMain() {

        //подготовка
        TransmittedData transmittedData = new TransmittedData(0);
        transmittedData.getDataStorage().add("aaa",1);

        //тестирование
        SendMessage message = SharedService.goToMenuMainByCommandStart(transmittedData);

        Object actualDataStorageElement = transmittedData.getDataStorage().get("aaa");

        InlineKeyboardMarkup expectedKeyboard = InlineKeyboardsMarkupStorage.getMenuMain();
        InlineKeyboardMarkup actualKeyboard = (InlineKeyboardMarkup) message.getReplyMarkup();

        String expectedText = DialogStringsStorage.CommandStartOK;
        String actualText = message.getText();

        State expectedState = State.ClickInMenuMain;
        State actualState  = transmittedData.getState();

        //проверка
        assertThat(actualDataStorageElement).isNull();

        assertThat(expectedKeyboard).isEqualTo(actualKeyboard);

        assertThat(expectedText).isEqualTo(actualText);

        assertThat(expectedState).isEqualTo(actualState);
    }
}