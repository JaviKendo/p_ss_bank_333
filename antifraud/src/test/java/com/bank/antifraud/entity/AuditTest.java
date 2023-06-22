package com.bank.antifraud.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuditTest {

    private Audit audit;
    private final Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp modificationTime = null;

    @BeforeEach
    void prepare() {
        audit = new Audit(1L, "ExampleEntityType", "ADD",
                "Username", null, creationTime, modificationTime,
                null, "\"keyExample\":\"valueExample\"");
    }

    @Test
    void testGetId() {
        assertEquals(1L, audit.getId());
    }

    @Test
    void testGetEntityType() {
        assertEquals("ExampleEntityType", audit.getEntityType());
    }

    @Test
    void testGetOperationType() {
        assertEquals("ADD", audit.getOperationType());
    }

    @Test
    void testGetCreatedBy() {
        assertEquals("Username", audit.getCreatedBy());
    }

    @Test
    void testGetModifiedBy() {
        assertNull(audit.getModifiedBy());
    }

    @Test
    void testGetCreatedAt() {
        assertEquals(creationTime, audit.getCreatedAt());
    }

    @Test
    void testGetModifiedAt() {
        assertNull(audit.getModifiedAt());
    }

    @Test
    void testGetNewEntityJson() {
        assertNull(audit.getNewEntityJson());
    }

    @Test
    void testGetEntityJson() {
        assertEquals("\"keyExample\":\"valueExample\"", audit.getEntityJson());
    }

    @Test
    void testSetId() {
        audit.setId(2L);
        assertEquals(2L, audit.getId());
    }

    @Test
    void testSetEntityType() {
        audit.setEntityType("EntityType");
        assertEquals("EntityType", audit.getEntityType());
    }

    @Test
    void testSetOperationType() {
        audit.setOperationType("UPDATE");
        assertEquals("UPDATE", audit.getOperationType());
    }

    @Test
    void testSetCreatedBy() {
        audit.setCreatedBy("firstName");
        assertEquals("firstName", audit.getCreatedBy());
    }

    @Test
    void testSetModifiedBy() {
        audit.setModifiedBy("newUsername");
        assertEquals("newUsername", audit.getModifiedBy());
    }

    @Test
    void testSetCreatedAt() {
        Timestamp newCreationTime = Timestamp.valueOf(LocalDateTime.now());
        audit.setCreatedAt(newCreationTime);
        assertEquals(newCreationTime, audit.getCreatedAt());
    }

    @Test
    void testSetModifiedAt() {
        modificationTime = Timestamp.valueOf(LocalDateTime.now());
        audit.setModifiedAt(modificationTime);
        assertEquals(modificationTime, audit.getModifiedAt());
    }

    @Test
    void testSetNewEntityJson() {
        audit.setNewEntityJson("\"newKeyExample\":\"newValueExample\"");
        assertEquals("\"newKeyExample\":\"newValueExample\"", audit.getNewEntityJson());
    }

    @Test
    void testSetEntityJson() {
        audit.setEntityJson("\"newKeyExample\":\"newValueExample\"");
        assertEquals("\"newKeyExample\":\"newValueExample\"", audit.getEntityJson());
    }

}