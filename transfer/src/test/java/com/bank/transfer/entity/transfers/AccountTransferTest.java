package com.bank.transfer.entity.transfers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountTransferTest {

    private AccountTransfer accountTransfer;

    @BeforeEach
    void createAccountTransfer() {
        accountTransfer =
                new AccountTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
    }
    @Test
    void testEquals() {
        Assertions.assertEquals(accountTransfer,
                new AccountTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5));
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(accountTransfer.hashCode(),
                new AccountTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5)
                        .hashCode());
    }

    @Test
    void getId() {
        Assertions.assertEquals(accountTransfer.getId(), 1L);
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(accountTransfer.getAmount(), BigDecimal.valueOf(2));
    }

    @Test
    void getPurpose() {
        Assertions.assertEquals(accountTransfer.getPurpose(), "3");
    }

    @Test
    void getAccountDetailsId() {
        Assertions.assertEquals(accountTransfer.getAccountDetailsId(), 4);
    }

    @Test
    void getAccountNumber() {
        Assertions.assertEquals(accountTransfer.getAccountNumber(), 5);
    }

    @Test
    void setId() {
        accountTransfer.setId(11L);
        Assertions.assertEquals(accountTransfer.getId(), 11L);
    }

    @Test
    void setAmount() {
        accountTransfer.setAmount(BigDecimal.valueOf(22));
        Assertions.assertEquals(accountTransfer.getAmount(), BigDecimal.valueOf(22));
    }

    @Test
    void setPurpose() {
        accountTransfer.setPurpose("33");
        Assertions.assertEquals(accountTransfer.getPurpose(), "33");
    }

    @Test
    void setAccountDetailsId() {
        accountTransfer.setAccountDetailsId(44);
        Assertions.assertEquals(accountTransfer.getAccountDetailsId(), 44);
    }

    @Test
    void setAccountNumber() {
        accountTransfer.setAccountNumber(55);
        Assertions.assertEquals(accountTransfer.getAccountNumber(), 55);
    }

    @Test
    void testToString() {
        Assertions.assertEquals(accountTransfer.toString(),
                "{\"id\":1,\"amount\":2,\"purpose\":\"3\",\"accountDetailsId\":4,\"accountNumber\":5}");
    }

    @Test
    void builder() {
        Assertions.assertEquals(accountTransfer,
                AccountTransfer.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("3")
                        .accountDetailsId(4)
                        .accountNumber(5)
                        .build());
    }
}