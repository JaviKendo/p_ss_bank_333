package com.bank.transfer.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

class TransferExceptionTest {

    private static final ZonedDateTime zonedDateTime = ZonedDateTime.now();
    private static TransferException transferException;
    @BeforeAll
    static void createTransferException() {
        transferException =
                new TransferException("1", HttpStatus.BAD_REQUEST, zonedDateTime);
        Assertions.assertNotNull(transferException);
    }
    @Test
    void massage() {
        Assertions.assertEquals(transferException.massage(), "1");
    }

    @Test
    void httpStatus() {
        Assertions.assertEquals(transferException.httpStatus(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void zonedDateTime() {
        Assertions.assertEquals(transferException.zonedDateTime(), zonedDateTime);
    }
}