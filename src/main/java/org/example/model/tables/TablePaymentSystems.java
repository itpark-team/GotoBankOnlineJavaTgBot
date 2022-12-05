package org.example.model.tables;

import org.example.model.entities.PaymentSystem;

import java.util.List;

public interface TablePaymentSystems {
    List<PaymentSystem> getAll() throws Exception;
    PaymentSystem getById(int paymentSystemId) throws Exception;

}
