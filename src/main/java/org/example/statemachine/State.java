package org.example.statemachine;

public enum State {
    WaitingCommandStart,
    WaitingClickOnInlineButtonInMenuMain,
    WaitingClickOnInlineButtonInMenuMyCards,
    WaitingClickOnInlineButtonInMenuChooseSpecificCard,
    WaitingInputIncomeMoneyForSpecificCard,
    WaitingClickOnInlineButtonInMenuChoosePaySystemForNewCard,
    WaitingClickOnInlineButtonInMenuApproveDeleteSpecificCard,
    WaitingInputNumberCardFromForTransaction,
    WaitingInputNumberCardToForTransaction,
    WaitingInputMoneyForTransaction,
    WaitingClickOnInlineButtonInMenuInstruction
}
