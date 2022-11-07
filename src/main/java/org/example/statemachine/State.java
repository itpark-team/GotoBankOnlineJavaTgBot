package org.example.statemachine;

public enum State {
    WaitingCommandStart,
    WaitingClickInMenuMain,
    WaitingClickInMenuMyCards,
    WaitingClickInMenuChooseSpecificCard,
    WaitingInputIncomeMoneyForSpecificCard,
    WaitingClickInMenuChoosePaySystemForNewCard,
    WaitingClickInMenuApproveDeleteSpecificCard,
    WaitingClickNumberCardFromForTransaction,
    WaitingInputNumberCardToForTransaction,
    WaitingInputMoneyForTransaction,
    WaitingClickOnInlineButtonInMenuInstruction
}
