package org.example.model.tables;

import org.example.model.entities.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.math.BigDecimal;
import java.util.List;

public class TableCardsHiberImpl implements TableCards {

    private SessionFactory sessionFactory;

    public TableCardsHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNew(Card card) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(card);

        transaction.commit();
        session.close();
    }

    @Override
    public Card getByCardId(int cardId) throws Exception {
        return sessionFactory.openSession().get(Card.class, cardId);
    }

    @Override
    public Card getByNumber(long findNumber) throws Exception {
        Query query = sessionFactory.openSession().createQuery("FROM Card WHERE number = :findNumber");
        query.setParameter("findNumber", findNumber);
        return ((List<Card>) query.list()).get(0);
    }

    @Override
    public boolean hasCardWithNumber(long number) throws Exception {
        Query query = sessionFactory.openSession().createQuery("FROM Card WHERE number = :findNumber");
        query.setParameter("findNumber", number);
        return query.list().size() > 0;
    }

    @Override
    public void deleteByCardId(int cardId) throws Exception {
        Card card = Card.builder().id(cardId).build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(card);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Card> getAllByChatId(long findChatId) throws Exception {
        Query query = sessionFactory.openSession().createQuery("FROM Card WHERE chatId = :findChatId  ORDER BY id");
        query.setParameter("findChatId", findChatId);
        return (List<Card>) query.list();
    }

    @Override
    public void depositMoneyToBalanceByCardId(int cardId, BigDecimal money) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Card SET balance = balance + :money WHERE id = :cardId");

        query.setParameter("money", money);
        query.setParameter("cardId", cardId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void takeOffMoneyFromBalanceByCardId(int cardId, BigDecimal money) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Card SET balance = balance - :money WHERE id = :cardId");

        query.setParameter("money", money);
        query.setParameter("cardId", cardId);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
