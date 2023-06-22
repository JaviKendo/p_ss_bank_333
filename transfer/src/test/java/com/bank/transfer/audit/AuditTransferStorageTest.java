package com.bank.transfer.audit;

import com.bank.transfer.entity.transfers.AccountTransfer;
import com.bank.transfer.entity.transfers.CardTransfer;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AuditTransferStorageTest {

    private AuditTransferStorage auditTransferStorage;
    @BeforeEach
    void createAudit() {
        AccountTransfer accountTransfer =
                new AccountTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
        CardTransfer cardTransfer =
                new CardTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
        PhoneTransfer phoneTransfer =
                new PhoneTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5);
        this.auditTransferStorage = new AuditTransferStorage(accountTransfer, cardTransfer, phoneTransfer);
    }
    @Test
    void getAccountTransfer() {
        Assertions.assertEquals(auditTransferStorage.getAccountTransfer(),
                new AccountTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5));
    }

    @Test
    void getCardTransfer() {
        Assertions.assertEquals(auditTransferStorage.getCardTransfer(),
                new CardTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5));
    }

    @Test
    void getPhoneTransfer() {
        Assertions.assertEquals(auditTransferStorage.getPhoneTransfer(),
                new PhoneTransfer(1L, BigDecimal.valueOf(2), "3", 4, 5));
    }

    @Test
    void setAccountTransfer() {
        auditTransferStorage.setAccountTransfer(
                new AccountTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
        Assertions.assertEquals(auditTransferStorage.getAccountTransfer(),
                new AccountTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
    }

    @Test
    void setCardTransfer() {
        auditTransferStorage.setCardTransfer(
                new CardTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
        Assertions.assertEquals(auditTransferStorage.getCardTransfer(),
                new CardTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
    }

    @Test
    void setPhoneTransfer() {
        auditTransferStorage.setPhoneTransfer(
                new PhoneTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
        Assertions.assertEquals(auditTransferStorage.getPhoneTransfer(),
                new PhoneTransfer(11L, BigDecimal.valueOf(22), "33", 44, 55));
    }
}