package org.example.model.tables;

import org.example.model.connection.JdbcConnection;
import org.example.model.entities.PaymentSystem;
import org.example.util.SystemStringsStorage;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class TablePaymentSystemsJdbcImplTest {

   /* @Test
    public void getById_Id2_ReturnVisaPaymentSystem() throws Exception {
        //подготовка
        Connection connection = new JdbcConnection(
                SystemStringsStorage.TestDbUrl,
                SystemStringsStorage.TestDbUser,
                SystemStringsStorage.TestDbPassword).getConnection();

        TablePaymentSystems tablePaymentSystems = new TablePaymentSystemsJdbcImpl(connection);

        //тест
        PaymentSystem paymentSystem = tablePaymentSystems.getById(2);

        String expectedName = "VISA";
        String actualName = paymentSystem.getName();

        //проверка
        assertThat(expectedName).isEqualTo(actualName);
    }*/

}