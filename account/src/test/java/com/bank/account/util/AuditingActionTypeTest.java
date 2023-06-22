package com.bank.account.util;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class AuditingActionTypeTest {

    @Test
    void values_ReturnsAllEnumValues() {
        AuditingActionType[] values = AuditingActionType.values();

        Assertions.assertNotNull(values);
        Assertions.assertEquals(3, values.length);
        Assertions.assertArrayEquals(new AuditingActionType[]{AuditingActionType.CREATE, AuditingActionType.UPDATE, AuditingActionType.DELETE}, values);
    }

    @Test
    void valueOf_ReturnsCorrectEnumValue() {
        AuditingActionType create = AuditingActionType.valueOf("CREATE");
        AuditingActionType update = AuditingActionType.valueOf("UPDATE");
        AuditingActionType delete = AuditingActionType.valueOf("DELETE");

        Assertions.assertNotNull(create);
        Assertions.assertNotNull(update);
        Assertions.assertNotNull(delete);
        Assertions.assertEquals(AuditingActionType.CREATE, create);
        Assertions.assertEquals(AuditingActionType.UPDATE, update);
        Assertions.assertEquals(AuditingActionType.DELETE, delete);
    }
}