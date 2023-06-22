package com.bank.publicinfo.audit;

public enum AuditActionType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    AuditActionType(String operation) {
    }
}
