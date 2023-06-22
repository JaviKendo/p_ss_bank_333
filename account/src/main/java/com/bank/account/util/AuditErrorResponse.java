package com.bank.account.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
/**
 * Класс AuditErrorResponse представляет собой модель ошибки для аудита.
 * Используется для создания объекта, содержащего информацию об ошибке, включая сообщение об ошибке и время возникновения ошибки.
 */
@Getter
@Setter
@AllArgsConstructor
public class AuditErrorResponse {
    private String message;
    private OffsetDateTime errorTime;
}
