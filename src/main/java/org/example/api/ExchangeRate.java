package org.example.api;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeRate {
    private Date date;
    private double usd;
    private double eur;

    public ExchangeRate(Date date, double usd, double eur) {
        this.date = date;
        this.usd = usd;
        this.eur = eur;
    }

    public String getDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public double getUsd() {
        return usd;
    }

    public double getEur() {
        return eur;
    }
}
