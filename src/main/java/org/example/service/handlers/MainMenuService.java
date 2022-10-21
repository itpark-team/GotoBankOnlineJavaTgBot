package org.example.service.handlers;

import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.ButtonsStorage;
import org.example.util.DialogStringsStorage;
import org.example.util.InlineKeyboardsMarkupStorage;
import org.example.util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MainMenuService {
    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);

        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain());

        return message;
    }

    public SendMessage processClickOnInlineButtonInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonMyCardsInMenuMain.getCallBackData())) {
            message.setText("нажали на мои карты");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonTransferMoneyInMenuMain.getCallBackData())) {
            message.setText("нажали на перевод денег");
            return message;
        }else if (callBackData.equals(ButtonsStorage.ButtonInstructionInMenuMain.getCallBackData())) {
            message.setText("нажали на инструкцию");
            return message;
        }

        throw new Exception("ввели хуйню");
    }
}
