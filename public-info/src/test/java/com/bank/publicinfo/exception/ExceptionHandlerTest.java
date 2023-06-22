package com.bank.publicinfo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExceptionHandlerTest {
    @Autowired
    ExceptionHandler exceptionHandler;

    @Test
    void handelException() {
        ResponseEntity<PublicException> responseEntity = exceptionHandler.handelException(new PublicRequestException("hello"));
        PublicRequestException publicRequestException = new PublicRequestException("hello");
        assertNotNull(responseEntity);
        assertNotNull(publicRequestException);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void testHandelException() {
        ResponseEntity<PublicException> responseEntity = exceptionHandler.handelException(new PublicRequestException("hello"));
        Exception exception = new Exception("hello");
        assertNotNull(responseEntity);
        assertNotNull(exception);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}