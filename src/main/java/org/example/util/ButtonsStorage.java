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

    public final static Button ButtonMyCardsInMenuMain = new Button("Мои карты", "ButtonMyCardsInMenuMain");
    public final static Button ButtonTransferMoneyInMenuMain = new Button("Перевести деньги", "ButtonTransferMoneyInMenuMain");
    public final static Button ButtonInstructionInMenuMain = new Button("Инструкция", "ButtonInstructionInMenuMain");

    public final static Button ButtonAddNewCardInMenuMyCard = new Button("Добавить новую","ButtonAddNewCardInMenuMyCard");
    public final static Button ButtonBackInMenuMyCard = new Button("Назад","ButtonBackInMenuMyCard");

    public final static Button ButtonAddMoneyToBalanceInMenuChooseSpecificCard = new Button("Полнить баланс","ButtonAddMoneyToBalanceInMenuChooseSpecificCard");
    public final static Button ButtonDeleteCardInMenuChooseSpecificCard = new Button("Удалить карту","ButtonDeleteCardInMenuChooseSpecificCard");
    public final static Button ButtonBackInMenuChooseSpecificCard = new Button("Назад","ButtonBackInMenuChooseSpecificCard");



}
