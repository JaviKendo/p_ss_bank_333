package com.bank.antifraud.custom_audit;

import org.junit.jupiter.api.Test;

import static com.bank.antifraud.custom_audit.AuditingActionType.ADD;
import static com.bank.antifraud.custom_audit.AuditingActionType.DELETE;
import static com.bank.antifraud.custom_audit.AuditingActionType.UPDATE;
import static org.assertj.core.api.Assertions.assertThat;

class AuditingActionTypeTest {

    @Test
    void testName() {
        assertThat(ADD.name()).isEqualTo("ADD");
        assertThat(UPDATE.name()).isEqualTo("UPDATE");
        assertThat(DELETE.name()).isEqualTo("DELETE");
    }

    @Test
    void testValues() {
        AuditingActionType[] actionTypes = AuditingActionType.values();

        assertThat(actionTypes).isNotEmpty();
        assertThat(actionTypes.length).isEqualTo(3);
    }

    @Test
    void testValueOf() {
        assertThat(AuditingActionType.valueOf("ADD")).isEqualTo(ADD);
        assertThat(AuditingActionType.valueOf("UPDATE")).isEqualTo(UPDATE);
        assertThat(AuditingActionType.valueOf("DELETE")).isEqualTo(DELETE);
    }

}