package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.exception.AuditNotFoundException;
import com.bank.account.util.AccountDetailsErrorResponse;
import com.bank.account.util.AuditErrorResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
/**
 * Класс-обработчик исключений для аккаунтов.
 * Обрабатывает различные исключительные ситуации, возникающие в процессе работы с аккаунтами и аудитом.
 * Генерирует соответствующие ответы с ошибками.
 */
@RestControllerAdvice
public class AccountExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(AccountExceptionHandler.class);
    /**
     * Обрабатывает исключение AccountDetailsNotFoundException.
     * Генерирует ответ с ошибкой о том, что информация об аккаунте не найдена.
     * @param ignoredE исключение AccountDetailsNotFoundException
     * @return объект AccountDetailsErrorResponse с информацией об ошибке
     */
    @ExceptionHandler({AccountDetailsNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AccountDetailsErrorResponse handle(AccountDetailsNotFoundException ignoredE) {
        logger.info("запущен метод AccountDetailsErrorResponse");
        return new AccountDetailsErrorResponse("AccountDetails с таким id не найден", OffsetDateTime.now());
    }
    /**
     * Обрабатывает исключение AuditNotFoundException.
     * Генерирует ответ с ошибкой о том, что информация об аудите не найдена.
     * @param ignoredE исключение AuditNotFoundException
     * @return объект AuditErrorResponse с информацией об ошибке
     */
    @ExceptionHandler({AuditNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AuditErrorResponse handle(AuditNotFoundException ignoredE) {
        logger.info("запущен метод AuditErrorResponse");
        return new AuditErrorResponse("Audit с таким id не найден", OffsetDateTime.now());
    }
    /**
     * Обрабатывает исключение MethodArgumentNotValidException, возникающее при невалидных аргументах метода.
     * Генерирует ответ с ошибкой валидации и списком полей и сообщений об ошибках.
     * @param e исключение MethodArgumentNotValidException
     * @return ответ с ошибкой валидации и списком полей и сообщений об ошибках
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            @NonNull MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}