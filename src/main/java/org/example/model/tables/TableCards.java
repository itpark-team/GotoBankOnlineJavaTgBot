package org.example.model.tables;

import org.example.model.entities.Card;

import java.math.BigDecimal;
import java.util.List;

public interface TableCards {
    void addNew(Card card) throws Exception;
    Card getByCardId(long cardId) throws Exception;
    Card getByNumber(long findNumber) throws Exception;
    boolean hasCardWithNumber(long number) throws Exception;
    void deleteByCardId(int cardId) throws Exception;
    List<Card> getAllByChatId(long findChatId) throws Exception;
    void depositMoneyToBalanceByCardId(int cardId, BigDecimal money) throws Exception;
    void takeOffMoneyFromBalanceByCardId(int cardId, BigDecimal money) throws Exception;
}
