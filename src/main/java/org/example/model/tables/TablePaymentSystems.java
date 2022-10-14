package org.example.model.tables;

import org.example.model.entities.PaymentSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablePaymentSystems {
    private Connection connection;

    public TablePaymentSystems(Connection connection) {
        this.connection = connection;
    }

    public List<PaymentSystem> getAll() throws SQLException {
        List<PaymentSystem> paymentSystems = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM payment_systems ORDER BY id ASC");

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            paymentSystems.add(new PaymentSystem(id, name));
        }
        resultSet.close();

        statement.close();

        return paymentSystems;
    }
}
