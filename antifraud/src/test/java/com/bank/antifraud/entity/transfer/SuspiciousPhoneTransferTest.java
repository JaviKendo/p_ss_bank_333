package com.bank.antifraud.entity.transfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuspiciousPhoneTransferTest {

    private SuspiciousPhoneTransfer suspiciousPhoneTransfer;

    @BeforeEach
    void prepare() {
        suspiciousPhoneTransfer = new SuspiciousPhoneTransfer(1L, 100L, false,
                true, null, "Почему перевод по телефону попал в antifraud");
    }

    @Test
    void testGetId() {
        assertEquals(1L, suspiciousPhoneTransfer.getId());
    }

    @Test
    void testGetCardTransferId() {
        assertEquals(100L, suspiciousPhoneTransfer.getPhoneTransferId());
    }

    @Test
    void testGetIsBlocked() {
        assertFalse(suspiciousPhoneTransfer.getIsBlocked());
    }

    @Test
    void testGetIsSuspicious() {
        assertTrue(suspiciousPhoneTransfer.getIsSuspicious());
    }

    @Test
    void testGetBlockedReason() {
        assertNull(suspiciousPhoneTransfer.getBlockedReason());
    }

    @Test
    void testGetSuspiciousReason() {
        assertEquals("Почему перевод по телефону попал в antifraud",
                suspiciousPhoneTransfer.getSuspiciousReason());
    }

    @Test
    void testSetId() {
        suspiciousPhoneTransfer.setId(10L);
        assertEquals(10L, suspiciousPhoneTransfer.getId());
    }

    @Test
    void testSetCardTransferId() {
        suspiciousPhoneTransfer.setPhoneTransferId(200L);
        assertEquals(200L, suspiciousPhoneTransfer.getPhoneTransferId());
    }

    @Test
    void testSetIsBlocked() {
        suspiciousPhoneTransfer.setIsBlocked(true);
        assertTrue(suspiciousPhoneTransfer.getIsBlocked());
    }

    @Test
    void testSetIsSuspicious() {
        suspiciousPhoneTransfer.setIsSuspicious(false);
        assertFalse(suspiciousPhoneTransfer.getIsSuspicious());
    }

    @Test
    void testSetBlockedReason() {
        suspiciousPhoneTransfer.setBlockedReason("Подозрительное действие");
        assertEquals("Подозрительное действие", suspiciousPhoneTransfer.getBlockedReason());
    }

    @Test
    void testSetSuspiciousReason() {
        suspiciousPhoneTransfer.setSuspiciousReason("Причина подозрения");
        assertEquals("Причина подозрения", suspiciousPhoneTransfer.getSuspiciousReason());
    }

}