package org.example.service.menupoints;

import org.example.model.DbManager;
import org.example.model.entities.Card;
import org.example.model.entities.PaymentSystem;
import org.example.model.tables.TableCards;
import org.example.model.tables.TablePaymentSystems;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.example.util.DialogStringsStorage;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionsServiceTest {

//    @Test
//    void processClickNumberCardFromForTransaction_ReturnMessageWithTextDynamic_And_StateInputNumberCardToForTransaction() throws Exception {
//        //подготовка
//        String callBackData = "CallbackCardId1";
//
//        TransmittedData transmittedData = new TransmittedData(0);
//        transmittedData.getDataStorage().add(SystemStringsStorage.DataStorageCardIdFrom, 1);
//
//        TableCards tableCards = Mockito.mock(TableCards.class);
//        when(tableCards.getByCardId(1)).thenReturn(
//                new Card(
//                        1,
//                        0,
//                        new BigDecimal(1000),
//                        111,
//                        1)
//        );
//
//        TablePaymentSystems tablePaymentSystems = Mockito.mock(TablePaymentSystems.class);
//        when(tablePaymentSystems.getById(1)).thenReturn(
//                new PaymentSystem(
//                        1,
//                        "МИР")
//        );
//
//        DbManager dbManager = new DbManager(tablePaymentSystems, tableCards);
//
//        TransactionsService transactionsService = new TransactionsService(dbManager);
//
//        //тестирование
//        SendMessage sendMessage = transactionsService.processClickNumberCardFromForTransaction(callBackData, transmittedData);
//
//        String expectedText = DialogStringsStorage.createInputNumberCardToForTransaction("МИР", 111, new BigDecimal(1000));
//        String actualText = sendMessage.getText();
//
//        State expectedState = State.InputNumberCardToForTransaction;
//        State actualState = transmittedData.getState();
//
//        //проверка
//        assertThat(expectedText).isEqualTo(actualText);
//
//        assertThat(expectedState).isEqualTo(actualState);
//    }

    @Test
    public void processClickNumberCardFromForTransaction_WrongCallback_ThrowException() throws Exception {
        //подготовка
        String callBackData = "wrong_callback";
        TransmittedData transmittedData = new TransmittedData(0);

        TransactionsService transactionsService = new TransactionsService(null);

        //тестирование
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> transactionsService.processClickNumberCardFromForTransaction(callBackData, transmittedData));
    }
}