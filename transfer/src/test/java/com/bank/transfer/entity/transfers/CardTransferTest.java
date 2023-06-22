package com.bank.transfer.entity.transfers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CardTransferTest {

    private CardTransfer cardTransfer;

    @BeforeEach
    void createCardTransfer() {
        cardTransfer =
                new CardTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
    }
    @Test
    void testEquals() {
        Assertions.assertEquals(cardTransfer,
                new CardTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5));
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(cardTransfer.hashCode(),
                new CardTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5)
                        .hashCode());
    }

    @Test
    void getId() {
        Assertions.assertEquals(cardTransfer.getId(), 1L);
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(cardTransfer.getAmount(), BigDecimal.valueOf(2));
    }

    @Test
    void getPurpose() {
        Assertions.assertEquals(cardTransfer.getPurpose(), "3");
    }

    @Test
    void getAccountDetailsId() {
        Assertions.assertEquals(cardTransfer.getAccountDetailsId(), 4);
    }

    @Test
    void getCardNumber() {
        Assertions.assertEquals(cardTransfer.getCardNumber(), 5);
    }

    @Test
    void setId() {
        cardTransfer.setId(11L);
        Assertions.assertEquals(cardTransfer.getId(), 11L);
    }

    @Test
    void setAmount() {
        cardTransfer.setAmount(BigDecimal.valueOf(22));
        Assertions.assertEquals(cardTransfer.getAmount(), BigDecimal.valueOf(22));
    }

    @Test
    void setPurpose() {
        cardTransfer.setPurpose("33");
        Assertions.assertEquals(cardTransfer.getPurpose(), "33");
    }

    @Test
    void setAccountDetailsId() {
        cardTransfer.setAccountDetailsId(44);
        Assertions.assertEquals(cardTransfer.getAccountDetailsId(), 44);
    }

    @Test
    void setCardNumber() {
        cardTransfer.setCardNumber(55);
        Assertions.assertEquals(cardTransfer.getCardNumber(), 55);
    }

    @Test
    void testToString() {
        Assertions.assertEquals(cardTransfer.toString(),
                "{\"id\":1,\"amount\":2,\"purpose\":\"3\",\"accountDetailsId\":4,\"cardNumber\":5}");
    }

    @Test
    void builder() {
        Assertions.assertEquals(cardTransfer,
                CardTransfer.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("3")
                        .accountDetailsId(4)
                        .cardNumber(5)
                        .build());
    }
}