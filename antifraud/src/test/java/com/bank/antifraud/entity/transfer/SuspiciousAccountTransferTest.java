package com.bank.antifraud.entity.transfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuspiciousAccountTransferTest {

    private SuspiciousAccountTransfer suspiciousAccountTransfer;

    @BeforeEach
    void prepare() {
        suspiciousAccountTransfer = new SuspiciousAccountTransfer(1L, 100L, false,
                true, null, "Почему перевод по счёту попал в antifraud");
    }

    @Test
    void testGetId() {
        assertEquals(1L, suspiciousAccountTransfer.getId());
    }

    @Test
    void testGetAccountTransferId() {
        assertEquals(100L, suspiciousAccountTransfer.getAccountTransferId());
    }

    @Test
    void testGetIsBlocked() {
        assertFalse(suspiciousAccountTransfer.getIsBlocked());
    }

    @Test
    void testGetIsSuspicious() {
        assertTrue(suspiciousAccountTransfer.getIsSuspicious());
    }

    @Test
    void testGetBlockedReason() {
        assertNull(suspiciousAccountTransfer.getBlockedReason());
    }

    @Test
    void testGetSuspiciousReason() {
        assertEquals("Почему перевод по счёту попал в antifraud",
                suspiciousAccountTransfer.getSuspiciousReason());
    }

    @Test
    void testSetId() {
        suspiciousAccountTransfer.setId(10L);
        assertEquals(10L, suspiciousAccountTransfer.getId());
    }

    @Test
    void testSetAccountTransferId() {
        suspiciousAccountTransfer.setAccountTransferId(200L);
        assertEquals(200L, suspiciousAccountTransfer.getAccountTransferId());
    }

    @Test
    void testSetIsBlocked() {
        suspiciousAccountTransfer.setIsBlocked(true);
        assertTrue(suspiciousAccountTransfer.getIsBlocked());
    }

    @Test
    void testSetIsSuspicious() {
        suspiciousAccountTransfer.setIsSuspicious(false);
        assertFalse(suspiciousAccountTransfer.getIsSuspicious());
    }

    @Test
    void testSetBlockedReason() {
        suspiciousAccountTransfer.setBlockedReason("Подозрительное действие");
        assertEquals("Подозрительное действие", suspiciousAccountTransfer.getBlockedReason());
    }

    @Test
    void testSetSuspiciousReason() {
        suspiciousAccountTransfer.setSuspiciousReason("Причина подозрения");
        assertEquals("Причина подозрения", suspiciousAccountTransfer.getSuspiciousReason());
    }

//    @Test
//    void builder() {
//        SuspiciousAccountTransfer.builder().
//    }

}