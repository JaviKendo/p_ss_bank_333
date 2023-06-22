package com.bank.account.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
/**
 * Класс AccountDetailsErrorResponse представляет собой модель ошибки для деталей аккаунта.
 * Используется для создания объекта, содержащего информацию об ошибке, включая сообщение об ошибке и время возникновения ошибки.
 */
@Getter
@Setter
@AllArgsConstructor
public class AccountDetailsErrorResponse {
    private String message;
    private OffsetDateTime errorTime;
}
