package org.example.service.handlers;

import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StaticService {
    public SendMessage processCommandReset(TransmittedData transmittedData) {
        transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);
        transmittedData.getDataStorage().clear();

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.getInlineKeyboardMarkupMenuMain());

        return message;
    }
}
