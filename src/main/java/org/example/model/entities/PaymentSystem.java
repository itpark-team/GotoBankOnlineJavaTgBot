package org.example.model.entities;

public class PaymentSystem {
    private int id;
    private String name;

    public PaymentSystem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
