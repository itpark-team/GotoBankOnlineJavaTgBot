package org.example.model.tables;

import org.example.model.connection.DbConnection;
import org.example.model.entities.Card;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TableCardsImplTest {

    @Test
    void getByCardId_Id6_ReturnCardWithNumber9376437851174408() throws Exception {

        Connection connection = new DbConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TableCardsImpl tableCards = new TableCardsImpl(connection);

        Card card = tableCards.getByCardId(6);

        String expectedNumber = "9376437851174408";
        String actualNumber = Long.toString(card.getNumber());

        assertThat(expectedNumber).isEqualTo(actualNumber);

    }

    @Test
    public void getByNumber() throws Exception {
        Connection connection = new DbConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TableCardsImpl tableCards = new TableCardsImpl(connection);


        Card card = tableCards.getByNumber(309914510451073l);


        String expectedNumber = "309914510451073";
        String actualNumber = Long.toString(card.getNumber());

        assertThat(expectedNumber).isEqualTo(actualNumber);
    }
}