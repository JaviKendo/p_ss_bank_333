package com.bank.history.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HistoryNotFoundExceptionTest {
    @Test
    public void testConstructor() {
        final String message = "Invalid id value";
        final HistoryNotFoundException exception = new HistoryNotFoundException(message);
        Assertions.assertEquals(message, exception.getMessage());
    }
}
