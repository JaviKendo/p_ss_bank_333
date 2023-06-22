package com.bank.publicinfo.dto;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {
    @Test
    void testEntityTypeGetterAndSetter() {
        String expectedEntityType = "entityType";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setEntityType(expectedEntityType);
        String actualEntityType = auditDTO.getEntityType();
        assertEquals(expectedEntityType, actualEntityType, "Корректно установлено значение поля EntityType ");
    }

    @Test
    void testOperationTypeGetterAndSetter() {
        String expectedOperationType = "operationType";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setOperationType(expectedOperationType);
        String actualOperationType = auditDTO.getOperationType();
        assertEquals(expectedOperationType, actualOperationType, "Корректно установлено значение поля OperationType");
    }

    @Test
    void testCreatedByGetterAndSetter() {
        String expectedCreatedBy = "createdBy";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setCreatedBy(expectedCreatedBy);
        String actualCreatedBy = auditDTO.getCreatedBy();
        assertEquals(expectedCreatedBy, actualCreatedBy, "Корректно установлено значение поля CreatedBy");
    }

    @Test
    void testModifiedByGetterAndSetter() {
        String expectedModifiedBy = "modifiedBy";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setModifiedBy(expectedModifiedBy);
        String actualModifiedBy = auditDTO.getModifiedBy();
        assertEquals(expectedModifiedBy, actualModifiedBy, "Корректно установлено значение поля ModifiedBy");
    }

    @Test
    void testCreatedAtGetterAndSetter() {
        Timestamp expectedCreatedAt = new Timestamp(System.currentTimeMillis());
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setCreatedAt(expectedCreatedAt);
        Timestamp actualCreatedAt = auditDTO.getCreatedAt();
        assertEquals(expectedCreatedAt, actualCreatedAt, "Корректно установлено значение поля CreatedAt");
    }

    @Test
    void testModifiedAtGetterAndSetter() {
        Timestamp expectedModifiedAt = new Timestamp(System.currentTimeMillis());
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setModifiedAt(expectedModifiedAt);
        Timestamp actualModifiedAt = auditDTO.getModifiedAt();
        assertEquals(expectedModifiedAt, actualModifiedAt, "Корректно установлено значение поля ModifiedAt");
    }

    @Test
    void testNewEntityJsonGetterAndSetter() {
        String expectedNewEntityJson = "newEntityJson";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setNewEntityJson(expectedNewEntityJson);
        String actualNewEntityJson = auditDTO.getNewEntityJson();
        assertEquals(expectedNewEntityJson, actualNewEntityJson, "Корректно установлено значение поля NewEntityJson");
    }

    @Test
    void testEntityJsonGetterAndSetter() {
        String expectedEntityJson = "entityJson";
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setEntityJson(expectedEntityJson);
        String actualEntityJson = auditDTO.getEntityJson();
        assertEquals(expectedEntityJson, actualEntityJson, "Корректно установлено значение поля EntityJson");
    }
}