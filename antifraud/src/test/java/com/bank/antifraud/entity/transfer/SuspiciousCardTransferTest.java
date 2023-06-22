package com.bank.antifraud.entity.transfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuspiciousCardTransferTest {

    private SuspiciousCardTransfer suspiciousCardTransfer;

    @BeforeEach
    void prepare() {
        suspiciousCardTransfer = new SuspiciousCardTransfer(1L, 100L, false,
                true, null, "Почему перевод по карте попал в antifraud");
    }

    @Test
    void testGetId() {
        assertEquals(1L, suspiciousCardTransfer.getId());
    }

    @Test
    void testGetCardTransferId() {
        assertEquals(100L, suspiciousCardTransfer.getCardTransferId());
    }

    @Test
    void testGetIsBlocked() {
        assertFalse(suspiciousCardTransfer.getIsBlocked());
    }

    @Test
    void testGetIsSuspicious() {
        assertTrue(suspiciousCardTransfer.getIsSuspicious());
    }

    @Test
    void testGetBlockedReason() {
        assertNull(suspiciousCardTransfer.getBlockedReason());
    }

    @Test
    void testGetSuspiciousReason() {
        assertEquals("Почему перевод по карте попал в antifraud",
                suspiciousCardTransfer.getSuspiciousReason());
    }

    @Test
    void testSetId() {
        suspiciousCardTransfer.setId(10L);
        assertEquals(10L, suspiciousCardTransfer.getId());
    }

    @Test
    void testSetCardTransferId() {
        suspiciousCardTransfer.setCardTransferId(200L);
        assertEquals(200L, suspiciousCardTransfer.getCardTransferId());
    }

    @Test
    void testSetIsBlocked() {
        suspiciousCardTransfer.setIsBlocked(true);
        assertTrue(suspiciousCardTransfer.getIsBlocked());
    }

    @Test
    void testSetIsSuspicious() {
        suspiciousCardTransfer.setIsSuspicious(false);
        assertFalse(suspiciousCardTransfer.getIsSuspicious());
    }

    @Test
    void testSetBlockedReason() {
        suspiciousCardTransfer.setBlockedReason("Подозрительное действие");
        assertEquals("Подозрительное действие", suspiciousCardTransfer.getBlockedReason());
    }

    @Test
    void testSetSuspiciousReason() {
        suspiciousCardTransfer.setSuspiciousReason("Причина подозрения");
        assertEquals("Причина подозрения", suspiciousCardTransfer.getSuspiciousReason());
    }

}