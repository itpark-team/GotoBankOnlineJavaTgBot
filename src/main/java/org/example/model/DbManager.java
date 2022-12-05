package org.example.model;

import org.example.model.tables.TableCards;
import org.example.model.tables.TablePaymentSystems;


public class DbManager {

    private TablePaymentSystems tablePaymentSystems;
    private TableCards tableCards;

    public DbManager(TablePaymentSystems tablePaymentSystems, TableCards tableCards) {
        this.tablePaymentSystems = tablePaymentSystems;
        this.tableCards = tableCards;
    }

    public TablePaymentSystems getTablePaymentSystems() {
        return tablePaymentSystems;
    }

    public TableCards getTableCards() {
        return tableCards;
    }
}
