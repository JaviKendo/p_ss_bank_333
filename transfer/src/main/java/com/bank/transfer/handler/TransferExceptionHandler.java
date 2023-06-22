package com.bank.transfer.handler;

import com.bank.transfer.exception.TransferRequestException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class TransferExceptionHandler {
    @ExceptionHandler(value = TransferRequestException.class)
    @ApiResponse(responseCode = "400", description = "Не найдено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransferException.class)))
    public ResponseEntity<Object> handelTransactionException(TransferRequestException e) {
        final TransferException transferException = new TransferException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(transferException, HttpStatus.BAD_REQUEST); }
    @ExceptionHandler(value = SQLException.class)
    @ApiResponse(responseCode = "400", description = "Не найдено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransferException.class)))
    public ResponseEntity<Object> handelTransactionException(SQLException e) {
        final TransferException transferException = new TransferException(
                e.getLocalizedMessage().substring(e.getLocalizedMessage().lastIndexOf(":") + 2),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(transferException, HttpStatus.BAD_REQUEST); }
    @ExceptionHandler(value = EntityNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Не найдено",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransferException.class)))
    public ResponseEntity<Object> handelTransactionException(EntityNotFoundException e) {
        final TransferException transferException = new TransferException(
                e.getLocalizedMessage()
                        .substring(e.getLocalizedMessage().lastIndexOf(".") + 1) + " not found.",
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(transferException, HttpStatus.NOT_FOUND); }
}
