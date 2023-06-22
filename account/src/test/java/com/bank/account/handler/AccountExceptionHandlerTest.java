package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.exception.AuditNotFoundException;
import com.bank.account.util.AccountDetailsErrorResponse;
import com.bank.account.util.AuditErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


class AccountExceptionHandlerTest {

    @Test
    void handle_AccountDetailsNotFoundException_ReturnsAccountDetailsErrorResponse() {
        AccountDetailsNotFoundException exception = new AccountDetailsNotFoundException();
        AccountExceptionHandler handler = new AccountExceptionHandler();

        AccountDetailsErrorResponse response = handler.handle(exception);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("AccountDetails с таким id не найден", response.getMessage());
    }

    @Test
    void handle_AuditNotFoundException_ReturnsAuditErrorResponse() {
        AuditNotFoundException exception = new AuditNotFoundException();
        AccountExceptionHandler handler = new AccountExceptionHandler();

        AuditErrorResponse response = handler.handle(exception);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Audit с таким id не найден", response.getMessage());
    }


    @Test
    void handleValidationExceptions_ReturnsBadRequestResponseWithErrorsMap() {
        AccountExceptionHandler handler = new AccountExceptionHandler();
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, Mockito.mock(BindingResult.class));

        ResponseEntity<?> response = handler.handleValidationExceptions(exception);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody() instanceof Map);
    }
}
