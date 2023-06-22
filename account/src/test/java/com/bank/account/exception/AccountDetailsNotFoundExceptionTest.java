package com.bank.account.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AccountDetailsNotFoundExceptionTest {

    @Test
    public void testConstructorWithNoMessage() {
        AccountDetailsNotFoundException exception = new AccountDetailsNotFoundException();
        Assertions.assertNotNull(exception);
        Assertions.assertNull(exception.getMessage());
    }

    @Test
    public void testThrowException() {
        Assertions.assertThrows(AccountDetailsNotFoundException.class, () -> {
            throw new AccountDetailsNotFoundException();
        });
    }
}


