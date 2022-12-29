package org.example.model.tables;

import org.example.model.entities.PaymentSystem;
import org.hibernate.SessionFactory;

import java.util.List;

public class TablePaymentSystemsHiberImpl implements TablePaymentSystems{

    private SessionFactory sessionFactory;

    public TablePaymentSystemsHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PaymentSystem> getAll() throws Exception {
        return (List<PaymentSystem>) sessionFactory.openSession().createQuery("FROM PaymentSystem ORDER BY id").list();
    }

    @Override
    public PaymentSystem getById(int paymentSystemId) throws Exception {
        return sessionFactory.openSession().get(PaymentSystem.class, paymentSystemId);
    }
}
