package com.bank.transfer.handler;

import com.bank.transfer.exception.TransferRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@SpringBootTest
class TransferExceptionHandlerTest {
    @Autowired
    private TransferExceptionHandler transferExceptionHandler;

    @Test
    void handelTransactionException() {
        ResponseEntity<Object> responseEntity
                = transferExceptionHandler.handelTransactionException(
                        new TransferRequestException("hello")
        );
        TransferException transferException
                = new TransferException("hello", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getBody(), transferException);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandelTransactionException() {
        ResponseEntity<Object> responseEntity
                = transferExceptionHandler.handelTransactionException(new SQLException(
                        "ERROR: duplicate key value violates unique constraint \"uk_99wgwmdrjtxqlwvnc3rs8xovy\"\n " +
                                " Подробности: Key (account_number)=(1) already exists.")
        );
        TransferException transferException = new TransferException(
                "Key (account_number)=(1) already exists.", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getBody(), transferException);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void testHandelTransactionException1() {
        ResponseEntity<Object> responseEntity
                = transferExceptionHandler.handelTransactionException(new EntityNotFoundException(
                "Unable to find com.bank.transfer.entity.transfers.AccountTransfer with id 1")
        );
        TransferException transferException = new TransferException(
                "AccountTransfer with id 1 not found.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getBody(), transferException);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}