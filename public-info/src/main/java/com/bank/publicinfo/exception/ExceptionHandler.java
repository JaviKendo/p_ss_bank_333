package com.bank.publicinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<PublicException> handelException(PublicRequestException exception) {
        PublicException publicException = new PublicException();
        publicException.setInfo(exception.getMessage());
        return new ResponseEntity<>(publicException, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<PublicException> handelException(Exception exception) {
        PublicException publicException = new PublicException();
        publicException.setInfo(exception.getMessage());
        return new ResponseEntity<>(publicException, HttpStatus.NOT_FOUND);
    }
}
