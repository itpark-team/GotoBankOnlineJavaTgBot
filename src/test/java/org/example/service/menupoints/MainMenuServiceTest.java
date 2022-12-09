package org.example.service.menupoints;

import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static org.assertj.core.api.Assertions.assertThat;

class MainMenuServiceTest {
    @Test
    public void processCommandStart_CommandStart_ReturnMessageWithTextCommandStartOK_And_KeyboardMenuMain_And_StateClickInMenuMain() {
        //подготовка
        String command = SystemStringsStorage.CommandStart;
        TransmittedData transmittedData = new TransmittedData(0);

        MainMenuService mainMenuService = new MainMenuService(null);

        //тест
        SendMessage message = mainMenuService.processCommandStart(command, transmittedData);

        String expectedText = DialogStringsStorage.CommandStartOK;
        String actualText = message.getText();

        InlineKeyboardMarkup expectedKeyboard = InlineKeyboardsMarkupStorage.getMenuMain();
        InlineKeyboardMarkup actualKeyboard = (InlineKeyboardMarkup) message.getReplyMarkup();

        State expectedState = State.ClickInMenuMain;
        State actualState = transmittedData.getState();

        //проверка
        assertThat(expectedText).isEqualTo(actualText);

        assertThat(expectedKeyboard).isEqualTo(actualKeyboard);

        assertThat(expectedState).isEqualTo(actualState);
    }

}