package org.example.util;

import java.math.BigDecimal;

public class DialogStringsStorage {

    public final static String CommandStartOK = "Выберите действие";
    public final static String CommandStartError = "Команда не распознана. Для начала работы с ботом введите /start";

    public final static String MenuMyCardsText = "Выберите действие:";
    public final static String MenuMyCardsNoCardsText = "В Вашем списке еще нет карт! Создайте новую карту.";

    public static String CreateMenuChooseSpecificCard(String paymentSystemName, long number, BigDecimal balance){

        return String.format("Выбрана карта\n%s %d\nБаланс: %.2f\n\nВыберите действие с картой",paymentSystemName,number,balance);
    }


}
