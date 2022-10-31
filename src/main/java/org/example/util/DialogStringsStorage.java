package org.example.util;

import java.math.BigDecimal;

public class DialogStringsStorage {

    public final static String CommandStartOK = "Выберите действие";
    public final static String CommandStartError = "Команда не распознана. Для начала работы с ботом введите /start";

    public final static String MenuMyCardsText = "Выберите действие:";
    public final static String MenuMyCardsNoCardsText = "В Вашем списке еще нет карт! Создайте новую карту.";

    public static String CreateMenuChooseSpecificCard(String paymentSystemName, long number, BigDecimal balance) {

        return String.format("Выбрана карта\n%s %d\nБаланс: %.2f\n\nВыберите действие с картой", paymentSystemName, number, balance);
    }

    public final static String ActionIncomeMoneyForSpecificCard = "Введите сумму пополнения (от 1 до 150000)";

    public final static String  ActionIncomeMoneyForSpecificCardFailInput = "Вы ввели некоректное значение";
    public final static String  ActionIncomeMoneyForSpecificCardOutOfRange = "Пожалуйста введите сумму от 1 до 150000";
    public final static String  ActionIncomeMoneyForSpecificCardOk = "Баланс успешно пополнен. Введите /start для возврата в меню";

}
