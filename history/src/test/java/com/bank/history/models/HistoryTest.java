package com.bank.history.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

@SpringBootTest
class HistoryTest {


    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    void testGettersAndSetters() {
        final History history = new History();
        history.setId(1L);
        history.setTransferAuditId(2L);
        history.setProfileAuditId(3L);
        history.setAccountAuditId(4L);
        history.setAntiFraudAuditId(5L);
        history.setPublicBankInfoAuditId(6L);
        history.setAuthorizationAuditId(7L);

        Assertions.assertEquals(1L, history.getId());
        Assertions.assertEquals(2L, history.getTransferAuditId());
        Assertions.assertEquals(3L, history.getProfileAuditId());
        Assertions.assertEquals(4L, history.getAccountAuditId());
        Assertions.assertEquals(5L, history.getAntiFraudAuditId());
        Assertions.assertEquals(6L, history.getPublicBankInfoAuditId());
        Assertions.assertEquals(7L, history.getAuthorizationAuditId());
    }


    @Test
    void testNoArgsConstructor() {
        final History history = new History();
        Assertions.assertNull(history.getId());
        Assertions.assertNull(history.getTransferAuditId());
        Assertions.assertNull(history.getProfileAuditId());
        Assertions.assertNull(history.getAccountAuditId());
        Assertions.assertNull(history.getAntiFraudAuditId());
        Assertions.assertNull(history.getPublicBankInfoAuditId());
        Assertions.assertNull(history.getAuthorizationAuditId());
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Test
    void testAllArgsConstructor() {
        final History history = new History(1L, 2L, 3L, 4L,
                5L, 6L, 7L);
        Assertions.assertEquals(1L, history.getId());
        Assertions.assertEquals(2L, history.getTransferAuditId());
        Assertions.assertEquals(3L, history.getProfileAuditId());
        Assertions.assertEquals(4L, history.getAccountAuditId());
        Assertions.assertEquals(5L, history.getAntiFraudAuditId());
        Assertions.assertEquals(6L, history.getPublicBankInfoAuditId());
        Assertions.assertEquals(7L, history.getAuthorizationAuditId());
    }

    @Test
    void testTableAnnotation() {
        final Table table = History.class.getAnnotation(Table.class);
        Assertions.assertNotNull(table);
        Assertions.assertEquals("history", table.schema());
        Assertions.assertEquals("history", table.name());
    }
}
