package org.example.model.tables;

import org.example.model.entities.Card;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TableCardsImpl implements TableCards {
    private Connection connection;

    public TableCardsImpl(Connection connection) {
        this.connection = connection;
    }

    public void addNew(Card card) throws Exception {
        Statement statement = connection.createStatement();

        String insertQuery = String.format(Locale.ENGLISH, "INSERT INTO cards (chat_id, balance, number, payment_system_id) VALUES (%d,%.2f,%d,%d)", card.getChatId(), card.getBalance(), card.getNumber(), card.getPaymentSystemId());

        statement.executeUpdate(insertQuery);

        statement.close();
    }

    public Card getByCardId(long cardId) throws Exception {
        Card card = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM cards WHERE id = %d ORDER BY id ASC", cardId);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        resultSet.next();

        int id = resultSet.getInt("id");
        long chatId = resultSet.getLong("chat_id");
        BigDecimal balance = resultSet.getBigDecimal("balance");
        long number = resultSet.getLong("number");
        int paymentSystemId = resultSet.getInt("payment_system_id");

        card = new Card(id, chatId, balance, number, paymentSystemId);

        resultSet.close();

        statement.close();

        return card;
    }

    public Card getByNumber(long findNumber) throws Exception {
        Card card = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM cards WHERE number = %d", findNumber);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        resultSet.next();

        int id = resultSet.getInt("id");
        long chatId = resultSet.getLong("chat_id");
        BigDecimal balance = resultSet.getBigDecimal("balance");
        long number = resultSet.getLong("number");
        int paymentSystemId = resultSet.getInt("payment_system_id");

        card = new Card(id, chatId, balance, number, paymentSystemId);

        resultSet.close();

        statement.close();

        return card;
    }

    public boolean hasCardWithNumber(long number) throws Exception {
        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM cards WHERE number = %d", number);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        boolean exist = resultSet.next();

        resultSet.close();

        statement.close();

        return exist;
    }

    public void deleteByCardId(int cardId) throws Exception {
        Statement statement = connection.createStatement();
        String deleteQuery = String.format("DELETE FROM cards WHERE id = %d", cardId);

        statement.executeUpdate(deleteQuery);
        statement.close();
    }

    public List<Card> getAllByChatId(long findChatId) throws Exception {

        List<Card> cards = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM cards WHERE chat_id = %d ORDER BY id ASC", findChatId);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            long chatId = resultSet.getLong("chat_id");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            long number = resultSet.getLong("number");
            int paymentSystemId = resultSet.getInt("payment_system_id");

            cards.add(new Card(id, chatId, balance, number, paymentSystemId));
        }

        resultSet.close();

        statement.close();

        return cards;
    }

    public void depositMoneyToBalanceByCardId(int cardId, BigDecimal money) throws Exception {
        Statement statement = connection.createStatement();
        String updateQuery = String.format(Locale.ENGLISH, "UPDATE cards set balance = balance + %.2f WHERE id = %d", money, cardId);

        statement.executeUpdate(updateQuery);
        statement.close();
    }

    public void takeOffMoneyFromBalanceByCardId(int cardId, BigDecimal money) throws Exception {
        Statement statement = connection.createStatement();
        String updateQuery = String.format(Locale.ENGLISH, "UPDATE cards set balance = balance - %.2f WHERE id = %d", money, cardId);

        statement.executeUpdate(updateQuery);
        statement.close();
    }


}
