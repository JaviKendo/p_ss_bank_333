package com.bank.antifraud.exception_handling.handler;

import com.bank.antifraud.exception_handling.ErrorMessage;
import com.bank.antifraud.exception_handling.exception.NoSuchTransferException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class RestControllerExceptionHandlerTest {
    private final RestControllerExceptionHandler exceptionHandler = new RestControllerExceptionHandler();

    @Test
    void handleException() {
        NoSuchTransferException exception = new NoSuchTransferException("ExampleEntity not found with ID = 1");

        ResponseEntity<ErrorMessage> actualResult = exceptionHandler.handleException(exception);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(Objects.requireNonNull(actualResult.getBody()).getMessage())
                .isEqualTo("ExampleEntity not found with ID = 1");
        assertThat(Objects.requireNonNull(actualResult.getBody()).getStatusCode()).isEqualTo(404);
        assertThat(actualResult.getBody().getError()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @Test
    void testHandleException() {
        IllegalArgumentException exception = new IllegalArgumentException("ExampleEntity not found with ID = 0");

        ResponseEntity<ErrorMessage> actualResult = exceptionHandler.handleException(exception);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(actualResult.getBody()).getMessage())
                .isEqualTo("ExampleEntity not found with ID = 0");
        assertThat(Objects.requireNonNull(actualResult.getBody()).getStatusCode()).isEqualTo(400);
        assertThat(actualResult.getBody().getError()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

}