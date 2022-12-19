package org.example.util;

import java.math.BigDecimal;

public class DialogStringsStorage {

    public final static String CommandStartOK = "Выберите действие";
    public final static String CommandStartError = "Команда не распознана. Для начала работы с ботом введите /start";

    public final static String MenuMyCardsText = "Выберите действие:";
    public final static String MenuMyCardsNoCardsText = "В Вашем списке еще нет карт! Создайте новую карту.";

    public final static String MenuTransactionsNoCardsText = "В Вашем списке еще нет карт! Создайте новую карту.";
    public final static String MenuTransactionsExistCardsText = "Выберите карту, с которой вы хотите перевести деньги";

    public static String createMenuChooseSpecificCard(String paymentSystemName, long number, BigDecimal balance) {

        return String.format("Выбрана карта\n%s %d\nБаланс: %.2f\n\nВыберите действие с картой", paymentSystemName, number, balance);
    }

    public final static String ActionIncomeMoneyForSpecificCard = "Введите сумму пополнения (от 1 до 150000)";

    public final static String ActionIncomeMoneyForSpecificCardFailInput = "Вы ввели некоректное значение";
    public final static String ActionIncomeMoneyForSpecificCardOutOfRange = "Пожалуйста введите сумму от 1 до 150000";
    public final static String ActionIncomeMoneyForSpecificCardOk = "Баланс успешно пополнен.\nВернитесь назад в главное меню";

    public static String createMenuApproveDeleteSpecificCard(String paymentSystemName, long number) {

        return String.format("Вы действительно хотите удалить карту?\n%s %d", paymentSystemName, number);
    }

    public final static String ActionApproveDeleteSpecificCardYes = "Карта успешно удалена.\nВернитесь назад в главное меню";
    public final static String ActionApproveDeleteSpecificCardNo = "Вы отменили удаление.\nВернитесь назад в главное меню";

    public final static String ActionMenuChoosePaySystemForNewCard = "Выберите платёжную систему для карты";


    public static String createMenuChoosePaySystemForNewCard(String paymentSystemName, long number) {

        return String.format("Ваша новая карта успешно создана\n%s %d\nВернитесь назад в главное меню", paymentSystemName, number);
    }

    public final static String createInputNumberCardToForTransaction(String paymentSystemName, long number, BigDecimal balance) {
        return String.format("Вы выбрали карту %s %d ₽%.2f\nВведите номер карты куда вы будете переводить деньги", paymentSystemName, number, balance);
    }

    public final static String ActionIncomeNumberCardToForTransactionFailInput = "Вы ввели некоректное значение номера карты";
    public final static String ActionIncomeNumberCardToForTransactionNoCard = "Карты с таким номером в системе не существует";

    public final static String ActionInputMoneyForTransaction = "Номер карты введён успешно\nТеперь введите количество денег, которое вы хотите перевести";

    public final static String ActionIncomeMoneyForTransactionFailInput = "Вы ввели некоректное значение";

    public final static String createIncomeMoneyForTransactionOutOfRange(BigDecimal maxMoney) {
        return String.format("Пожалуйста введите сумму от 0 до %.2f", maxMoney);
    }

    public final static String createInputMoneyForTransactionOk(String paymentSystemName, long number, BigDecimal balance) {
        return String.format("Деньги успешно переведены.\nТекущий баланс карты %s %d ₽%.2f\nВернитесь назад в главное меню", paymentSystemName, number, balance);
    }

    public final static String createCurrentExchangeRate(String currentDate, double usd, double eur) {
        return String.format("Текущая дата %s\n1 доллар США = %.4f руб.\n1 евро = %.4f руб.\nВернитесь назад в главное меню", currentDate, usd, eur);
    }
}
