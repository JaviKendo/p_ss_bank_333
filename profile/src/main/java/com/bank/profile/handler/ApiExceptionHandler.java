package com.bank.profile.handler;

import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.util.ProfileErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ProfileErrorResponse> handleException(ProfileNotFoundException e) {
        ProfileErrorResponse response = new ProfileErrorResponse("Запись с таким id не найдена");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ProfileErrorResponse> handleException(ProfileNotValidException e) {
        ProfileErrorResponse response = new ProfileErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
