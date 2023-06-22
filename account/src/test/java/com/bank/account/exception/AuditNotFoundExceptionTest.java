package com.bank.account.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AuditNotFoundExceptionTest {

    @Test
    public void testConstructorWithNoMessage() {
        AuditNotFoundException exception = new AuditNotFoundException();
        Assertions.assertNotNull(exception);
        Assertions.assertNull(exception.getMessage());
    }


    @Test
    public void testThrowException() {
        Assertions.assertThrows(AuditNotFoundException.class, () -> {
            throw new AuditNotFoundException();
        });
    }
}
