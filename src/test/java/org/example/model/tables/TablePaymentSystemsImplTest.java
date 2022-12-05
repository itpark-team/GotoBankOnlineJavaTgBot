package org.example.model.tables;

import org.example.model.connection.DbConnection;
import org.example.model.entities.PaymentSystem;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TablePaymentSystemsImplTest {

    @Test
    public void getById_Id2_ReturnVisaPaymentSystem() throws Exception {
        //подготовка
        Connection connection = new DbConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TablePaymentSystems tablePaymentSystems = new TablePaymentSystemsImpl(connection);

        //тест
        PaymentSystem paymentSystem = tablePaymentSystems.getById(2);

        String expectedName = "VISA";
        String actualName = paymentSystem.getName();

        //проверка
        assertThat(expectedName).isEqualTo(actualName);
    }

}