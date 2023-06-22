package com.bank.account.aspect;

import com.bank.account.util.AuditingActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Аннотация {@code Auditable} используется для пометки класса или метода как аудируемого,
 * указывая, что он должен отслеживаться для целей аудита.
 * Она определяет тип выполняемого аудиторского действия.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Auditable {
    /**
     * Определяет тип выполняемого аудиторского действия.
     * @return тип выполняемого аудиторского действия
     */
    AuditingActionType actionType();
}
