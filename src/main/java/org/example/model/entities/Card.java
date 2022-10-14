package org.example.model.entities;

import java.math.BigDecimal;

public class Card {
    private int id;
    private long chatId;
    private BigDecimal balance;
    private long number;
    private int paymentSystemId;
    private PaymentSystem paymentSystem;

    public Card(int id, long chatId, BigDecimal balance, long number, int paymentSystemId) {
        this.id = id;
        this.chatId = chatId;
        this.balance = balance;
        this.number = number;
        this.paymentSystemId = paymentSystemId;
        this.paymentSystem = null;
    }

    public int getId() {
        return id;
    }

    public long getChatId() {
        return chatId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getNumber() {
        return number;
    }

    public int getPaymentSystemId() {
        return paymentSystemId;
    }

    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }
}
