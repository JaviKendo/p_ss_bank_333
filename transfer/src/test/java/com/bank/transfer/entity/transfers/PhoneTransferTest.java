package com.bank.transfer.entity.transfers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PhoneTransferTest {

    private PhoneTransfer phoneTransfer;

    @BeforeEach
    void createPhoneTransfer() {
        phoneTransfer =
                new PhoneTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
    }

    @Test
    void getId() {
        Assertions.assertEquals(phoneTransfer.getId(), 1L);
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(phoneTransfer.getAmount(), BigDecimal.valueOf(2));
    }

    @Test
    void getPurpose() {
        Assertions.assertEquals(phoneTransfer.getPurpose(), "3");
    }

    @Test
    void getAccountDetailsId() {
        Assertions.assertEquals(phoneTransfer.getAccountDetailsId(), 4);
    }

    @Test
    void getAccountNumber() {
        Assertions.assertEquals(phoneTransfer.getPhoneNumber(), 5);
    }

    @Test
    void setId() {
        phoneTransfer.setId(11L);
        Assertions.assertEquals(phoneTransfer.getId(), 11L);
    }

    @Test
    void setAmount() {
        phoneTransfer.setAmount(BigDecimal.valueOf(22));
        Assertions.assertEquals(phoneTransfer.getAmount(), BigDecimal.valueOf(22));
    }

    @Test
    void setPurpose() {
        phoneTransfer.setPurpose("33");
        Assertions.assertEquals(phoneTransfer.getPurpose(), "33");
    }

    @Test
    void setAccountDetailsId() {
        phoneTransfer.setAccountDetailsId(44);
        Assertions.assertEquals(phoneTransfer.getAccountDetailsId(), 44);
    }

    @Test
    void setAccountNumber() {
        phoneTransfer.setPhoneNumber(55);
        Assertions.assertEquals(phoneTransfer.getPhoneNumber(), 55);
    }

    @Test
    void testToString() {
        Assertions.assertEquals(phoneTransfer.toString(),
                "{\"id\":1,\"amount\":2,\"purpose\":\"3\",\"accountDetailsId\":4,\"phoneNumber\":5}");
    }

    @Test
    void builder() {
        Assertions.assertEquals(phoneTransfer,
                PhoneTransfer.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("3")
                        .accountDetailsId(4)
                        .phoneNumber(5)
                        .build());
    }
}