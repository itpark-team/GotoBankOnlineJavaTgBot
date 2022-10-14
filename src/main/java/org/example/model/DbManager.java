package org.example.model;

import org.example.model.connection.DbConnection;
import org.example.model.tables.TableCards;
import org.example.model.tables.TablePaymentSystems;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {

    private TablePaymentSystems tablePaymentSystems;
    private TableCards tableCards;

    private DbManager() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        tableCards = new TableCards(connection);
        tablePaymentSystems = new TablePaymentSystems(connection);
    }

    public TablePaymentSystems getTablePaymentSystems() {
        return tablePaymentSystems;
    }

    public TableCards getTableCards() {
        return tableCards;
    }

    private static DbManager instance;

    public static DbManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }
}
