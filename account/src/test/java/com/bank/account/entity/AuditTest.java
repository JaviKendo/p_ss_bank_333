package com.bank.account.entity;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import org.junit.jupiter.api.Assertions;

class AuditTest {
    @Test
    void testIdGetterAndSetter() {

        Long expectedId = 1L;
        Audit audit = new Audit();

        audit.setId(expectedId);
        Long actualId = audit.getId();

        Assertions.assertEquals(expectedId, actualId, "Корректно установлено значение поля Id");
    }
    @Test
    void testEntityTypeGetterAndSetter() {

        String expectedEntityType = "entityType";
        Audit audit = new Audit();

        audit.setEntityType(expectedEntityType);
        String actualEntityType = audit.getEntityType();

        Assertions.assertEquals(expectedEntityType, actualEntityType, "Корректно установлено значение поля EntityType ");
    }
    @Test
    void testOperationTypeGetterAndSetter() {

        String expectedOperationType = "operationType";
        Audit audit = new Audit();

        audit.setOperationType(expectedOperationType);
        String actualOperationType = audit.getOperationType();

        Assertions.assertEquals(expectedOperationType, actualOperationType, "Корректно установлено значение поля OperationType");
    }
    @Test
    void testCreatedByGetterAndSetter() {

        String expectedCreatedBy = "createdBy";
        Audit audit = new Audit();

        audit.setCreatedBy(expectedCreatedBy);
        String actualCreatedBy = audit.getCreatedBy();

        Assertions.assertEquals(expectedCreatedBy, actualCreatedBy, "Корректно установлено значение поля CreatedBy");
    }
    @Test
    void testModifiedByGetterAndSetter() {

        String expectedModifiedBy = "modifiedBy";
        Audit audit = new Audit();

        audit.setModifiedBy(expectedModifiedBy);
        String actualModifiedBy = audit.getModifiedBy();

        Assertions.assertEquals(expectedModifiedBy, actualModifiedBy, "Корректно установлено значение поля ModifiedBy");
    }
    @Test
    void testCreatedAtGetterAndSetter() {

        Timestamp expectedCreatedAt = new Timestamp(System.currentTimeMillis());
        Audit audit = new Audit();

        audit.setCreatedAt(expectedCreatedAt);
        Timestamp actualCreatedAt = audit.getCreatedAt();

        Assertions.assertEquals(expectedCreatedAt, actualCreatedAt, "Корректно установлено значение поля CreatedAt");
    }
    @Test
    void testModifiedAtGetterAndSetter() {

        Timestamp expectedModifiedAt = new Timestamp(System.currentTimeMillis());
        Audit audit = new Audit();

        audit.setModifiedAt(expectedModifiedAt);
        Timestamp actualModifiedAt = audit.getModifiedAt();

        Assertions.assertEquals(expectedModifiedAt, actualModifiedAt, "Корректно установлено значение поля ModifiedAt");
    }
    @Test
    void testNewEntityJsonGetterAndSetter() {

        String expectedNewEntityJson = "newEntityJson";
        Audit audit = new Audit();

        audit.setNewEntityJson(expectedNewEntityJson);
        String actualNewEntityJson = audit.getNewEntityJson();

        Assertions.assertEquals(expectedNewEntityJson, actualNewEntityJson, "Корректно установлено значение поля NewEntityJson");
    }
    @Test
    void testEntityJsonGetterAndSetter() {

        String expectedEntityJson = "entityJson";
        Audit audit = new Audit();

        audit.setEntityJson(expectedEntityJson);
        String actualEntityJson = audit.getEntityJson();

        Assertions.assertEquals(expectedEntityJson, actualEntityJson, "Корректно установлено значение поля EntityJson");
    }
}
