package com.bank.account.util;

import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;

class AccountDetailsErrorResponseTest {

    @Test
    void getMessage_ReturnsCorrectMessage() {
        String message = "Error message";
        OffsetDateTime errorTime = OffsetDateTime.now();

        AccountDetailsErrorResponse errorResponse = new AccountDetailsErrorResponse(message, errorTime);

        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void getErrorTime_ReturnsCorrectErrorTime() {
        String message = "Error message";
        OffsetDateTime errorTime = OffsetDateTime.now();

        AccountDetailsErrorResponse errorResponse = new AccountDetailsErrorResponse(message, errorTime);

        Assertions.assertEquals(errorTime, errorResponse.getErrorTime());
    }

    @Test
    void setMessage_SetsNewMessage() {
        String message = "Error message";
        OffsetDateTime errorTime = OffsetDateTime.now();

        AccountDetailsErrorResponse errorResponse = new AccountDetailsErrorResponse(message, errorTime);

        String newMessage = "New error message";
        errorResponse.setMessage(newMessage);

        Assertions.assertEquals(newMessage, errorResponse.getMessage());
    }

    @Test
    void setErrorTime_SetsNewErrorTime() {
        String message = "Error message";
        OffsetDateTime errorTime = OffsetDateTime.now();

        AccountDetailsErrorResponse errorResponse = new AccountDetailsErrorResponse(message, errorTime);

        OffsetDateTime newErrorTime = OffsetDateTime.now().minusHours(1);
        errorResponse.setErrorTime(newErrorTime);

        Assertions.assertEquals(newErrorTime, errorResponse.getErrorTime());
    }
}
