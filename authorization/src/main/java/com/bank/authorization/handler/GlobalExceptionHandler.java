package com.bank.authorization.handler;

import com.bank.authorization.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MESSAGE_KEY = "message";
    private static final String TIMESTAMP_KEY = "timestamp";
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        final Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(MESSAGE_KEY, ex.getMessage());
        errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGeneralException(Exception ex) {

        final Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(MESSAGE_KEY, ex.getMessage());
        errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
