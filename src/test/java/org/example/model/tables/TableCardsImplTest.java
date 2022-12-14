package org.example.model.tables;

import org.example.model.connection.DbConnection;
import org.example.model.entities.Card;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class TableCardsImplTest {

    @Test
    void getByCardId_Id6_ReturnCardWithNumber9376437851174408() throws Exception {

        Connection connection = new DbConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TableCardsImpl tableCards = new TableCardsImpl(connection);

        Card card = tableCards.getByCardId(6);

        long expectedNumber = 9376437851174408l;
        long actualNumber = card.getNumber();

        assertThat(expectedNumber).isEqualTo(actualNumber);

    }

    @Test
    public void getByNumber_findNumber309914510451073_ReturnCardWithNumber309914510451073() throws Exception {
        Connection connection = new DbConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TableCardsImpl tableCards = new TableCardsImpl(connection);

        Card card = tableCards.getByNumber(309914510451073l);

        long expectedNumber = 309914510451073l;
        long actualNumber = card.getNumber();

        assertThat(expectedNumber).isEqualTo(actualNumber);
    }

    @Test
    public void deleteByCardId_id1_verify() throws Exception {
        TableCards tableCards = Mockito.mock(TableCards.class);

        tableCards.deleteByCardId(1);

        verify(tableCards).deleteByCardId(1);
    }

}