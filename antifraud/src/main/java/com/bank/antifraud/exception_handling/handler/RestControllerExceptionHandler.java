package com.bank.antifraud.exception_handling.handler;

import com.bank.antifraud.exception_handling.ErrorMessage;
import com.bank.antifraud.exception_handling.exception.NoSuchTransferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {
    @ExceptionHandler(NoSuchTransferException.class)
    public ResponseEntity<ErrorMessage> handleException(NoSuchTransferException exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                LocalDateTime.now());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        log.warn(String.format("The exception was thrown: \"%s\"", exception.getClass().getSimpleName()), exception);
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                LocalDateTime.now());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
