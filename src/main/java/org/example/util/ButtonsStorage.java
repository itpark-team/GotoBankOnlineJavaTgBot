package org.example.util;

public class ButtonsStorage {
    public static class Button {
        private String name;
        private String callBackData;

        public Button(String name, String callBackData) {
            this.name = name;
            this.callBackData = callBackData;
        }

        public String getName() {
            return name;
        }

        public String getCallBackData() {
            return callBackData;
        }
    }

    public final static Button MyCardsInMenuMain = new Button("Мои карты", "ButtonMyCardsInMenuMain");
    public final static Button TransferMoneyInMenuMain = new Button("Перевести деньги", "ButtonTransferMoneyInMenuMain");
    public final static Button CurrentExchangeRateInMenuMain = new Button("Курс валют", "GetCurrentExchangeRateInMenuMain");
    public final static Button InstructionInMenuMain = new Button("Инструкция", "ButtonInstructionInMenuMain");

    public final static Button AddNewCardInMenuMyCard = new Button("Добавить новую","ButtonAddNewCardInMenuMyCard");
    public final static Button BackInMenuMyCard = new Button("Назад","ButtonBackInMenuMyCard");

    public final static Button AddMoneyToBalanceInMenuChooseSpecificCard = new Button("Пополнить баланс","ButtonAddMoneyToBalanceInMenuChooseSpecificCard");
    public final static Button DeleteCardInMenuChooseSpecificCard = new Button("Удалить карту","ButtonDeleteCardInMenuChooseSpecificCard");
    public final static Button BackInMenuChooseSpecificCard = new Button("Назад","ButtonBackInMenuChooseSpecificCard");

    public final static Button MenuApproveDeleteSpecificCardYes = new Button("Да", "ButtonMenuApproveDeleteSpecificCardYes");
    public final static Button MenuApproveDeleteSpecificCardNo = new Button("Нет", "ButtonMenuApproveDeleteSpecificCardNo");

    public final static Button GoToMainMenuSharedButton = new Button("В главное меню", "BackToMainMenuSharedButton");

}
