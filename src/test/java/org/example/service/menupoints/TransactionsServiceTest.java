package org.example.service.menupoints;

import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.model.tables.TableCards;
import org.example.model.tables.TablePaymentSystems;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionsServiceTest {

    @Test
    void processClickNumberCardFromForTransaction() throws Exception {
        SendMessage message = new SendMessage();
        TransmittedData transmittedData = new TransmittedData(0);
        transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCardIdFrom, 1);

        TableCards tableCards = Mockito.mock(TableCards.class);
        BigDecimal balance = new BigDecimal(1000);
        when(tableCards.getByCardId(1)).thenReturn(new Card(1,0,balance,111,1));

        TablePaymentSystems tablePaymentSystems = Mockito.mock(TablePaymentSystems.class);
        when(tablePaymentSystems.getById(1)).thenReturn(new PaymentSystem(1,"Ã»–"));

        Card card = tableCards.getByCardId(1);
        PaymentSystem paymentSystem = tablePaymentSystems.getById(1);

        message.setText(DialogStringsStorage.createInputNumberCardToForTransaction(paymentSystem.getName(), card.getNumber(), card.getBalance()));

    }
}