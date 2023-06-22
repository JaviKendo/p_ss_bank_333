package com.bank.transfer.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class AuditTest {

    private final Date date = new Date(100000L);
    private final Date date2 = new Date(200000L);
    private final Date date3 = new Date(300000L);
    private Audit audit;

    @BeforeEach
    void createAudit() {
        audit = new Audit(1L, "2", "3", "4", "5",
                date, date2, "6", "7");
    }
    @Test
    void getId() {
        Assertions.assertEquals(audit.getId(), 1L);
    }

    @Test
    void getEntityType() {
        Assertions.assertEquals(audit.getEntityType(), "2");
    }

    @Test
    void getOperationType() {
        Assertions.assertEquals(audit.getOperationType(), "3");
    }

    @Test
    void getCreatedBy() {
        Assertions.assertEquals(audit.getCreatedBy(), "4");
    }

    @Test
    void getModifiedBy() {
        Assertions.assertEquals(audit.getModifiedBy(), "5");
    }

    @Test
    void getCreatedAt() {
        Assertions.assertEquals(audit.getCreatedAt(), date);
    }

    @Test
    void getModifiedAt() {
        Assertions.assertEquals(audit.getModifiedAt(), date2);
    }

    @Test
    void getEntityJson() {
        Assertions.assertEquals(audit.getEntityJson(), "6");
    }

    @Test
    void getNewEntityJson() {
        Assertions.assertEquals(audit.getNewEntityJson(), "7");
    }

    @Test
    void setId() {
        audit.setId(11L);
        Assertions.assertEquals(audit.getId(), 11L);
    }

    @Test
    void setEntityType() {
        audit.setEntityType("22");
        Assertions.assertEquals(audit.getEntityType(), "22");
    }

    @Test
    void setOperationType() {
        audit.setOperationType("33");
        Assertions.assertEquals(audit.getOperationType(), "33");
    }

    @Test
    void setCreatedBy() {
        audit.setCreatedBy("44");
        Assertions.assertEquals(audit.getCreatedBy(), "44");
    }

    @Test
    void setModifiedBy() {
        audit.setModifiedBy("55");
        Assertions.assertEquals(audit.getModifiedBy(), "55");
    }

    @Test
    void setCreatedAt() {
        audit.setCreatedAt(date3);
        Assertions.assertEquals(audit.getCreatedAt(), date3);
    }

    @Test
    void setModifiedAt() {
        audit.setModifiedAt(date3);
        Assertions.assertEquals(audit.getModifiedAt(), date3);
    }

    @Test
    void setEntityJson() {
        audit.setEntityJson("66");
        Assertions.assertEquals(audit.getEntityJson(), "66");
    }

    @Test
    void setNewEntityJson() {
        audit.setNewEntityJson("77");
        Assertions.assertEquals(audit.getNewEntityJson(), "77");
    }

    @Test
    void builder() {

        Audit audit1 = Audit.builder()
                .id(1L)
                .entityType("2")
                .operationType("3")
                .createdBy("4")
                .modifiedBy("5")
                .createdAt(date)
                .modifiedAt(date2)
                .entityJson("6")
                .newEntityJson("7")
                .build();
        Assertions.assertEquals(audit.getId(), audit1.getId());
        Assertions.assertEquals(audit.getEntityType(), audit1.getEntityType());
        Assertions.assertEquals(audit.getOperationType(), audit1.getOperationType());
        Assertions.assertEquals(audit.getCreatedBy(), audit1.getCreatedBy());
        Assertions.assertEquals(audit.getModifiedBy(), audit1.getModifiedBy());
        Assertions.assertEquals(audit.getCreatedAt(), audit1.getCreatedAt());
        Assertions.assertEquals(audit.getModifiedAt(), audit1.getModifiedAt());
        Assertions.assertEquals(audit.getEntityJson(), audit1.getEntityJson());
        Assertions.assertEquals(audit.getNewEntityJson(), audit1.getNewEntityJson());
    }
}