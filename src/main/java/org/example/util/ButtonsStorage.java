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
}