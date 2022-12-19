package org.example.service.menupoints;

import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SharedService {
    public static SendMessage goToMenuMainByCommandStart(TransmittedData transmittedData) {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuMain());

        transmittedData.getDataStorage().clear();
        transmittedData.setState(State.ClickInMenuMain);
        return message;
    }

    public static SendMessage processGoToMenuMainByInlineButton(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.GoToMainMenuSharedButton.getCallBackData())) {
            message.setText(DialogStringsStorage.CommandStartOK);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.getMenuMain());
            transmittedData.getDataStorage().clear();
            transmittedData.setState(State.ClickInMenuMain);
            return message;
        }

        throw new Exception("Ошибка распознавания callBackData");
    }
}
