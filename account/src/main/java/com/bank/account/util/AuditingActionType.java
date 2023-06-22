package com.bank.account.util;
/**
 * Перечисление AuditingActionType представляет типы действий аудита.
 * Определяет возможные операции аудита: CREATE, UPDATE, DELETE.
 */
public enum AuditingActionType {
    CREATE("CREATE"), UPDATE("UPDATE"),
    DELETE("DELETE");
    /**
     * Конструктор перечисления AuditingActionType.
     * @param operation Операция аудита
     */
    AuditingActionType(String operation) {
    }
}
