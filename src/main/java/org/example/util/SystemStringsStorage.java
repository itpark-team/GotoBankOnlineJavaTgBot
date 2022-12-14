package org.example.util;


import java.math.BigDecimal;

public class SystemStringsStorage {
    public final static String Empty = "Empty";
    public final static String CommandStart = "/start";
    public final static String CommandReset = "/reset";

    public final static String DataStorageCurrentCard = "DataStorageCurrentCard";

    public final static String CallbackCardId = "CallbackCardId";
    public final static String CallbackPaymentSystemsId = "CallbackPaymentSystemsId";

    public final static String DataStorageCardIdFrom = "DataStorageCardIdFrom";
    public final static String DataStorageCardIdTo = "DataStorageCardIdTo";


    public final static String DbUrl = "jdbc:postgresql://194.67.105.79:5432/goto_bank_online_db";
    public final static String DbUser = "goto_bank_online_user";
    public final static String DbPassword = "12345";


//    private final static String POSTGRES_DB = System.getenv("POSTGRES_DB");
//    private final static String POSTGRES_USER = System.getenv("POSTGRES_USER");
//    private final static String POSTGRES_PASSWORD = System.getenv("POSTGRES_PASSWORD");
//
//    public final static String DbUrl = "jdbc:postgresql://postgres:5432/"+POSTGRES_DB;
//    public final static String DbUser = POSTGRES_USER;
//    public final static String DbPassword = POSTGRES_PASSWORD;

    public final static String TestDbUrl = "jdbc:postgresql://194.67.105.79:5432/test_goto_bank_online_db";
    public final static String TestDbUser = "test_goto_bank_online_user";
    public final static String TestDbPassword = "12345";

}
