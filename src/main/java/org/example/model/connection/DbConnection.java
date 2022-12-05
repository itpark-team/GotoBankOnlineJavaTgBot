package org.example.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;

    public DbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://194.67.105.79:5432/goto_bank_online_db", "goto_bank_online_user", "12345");
    }

    public Connection getConnection() {
        return connection;
    }

}
