package com.bank.history.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HistoryErrorResponseTest {

    @Test
    public void testValidateWithValidId() throws HistoryNotFoundException {
        final HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse();
        final Long id = 1L;
        final Long result = historyErrorResponse.validate(id);
        Assertions.assertEquals(id, result);
    }

    @Test
    public void testValidateWithInvalidId() {
        final HistoryErrorResponse historyErrorResponse = new HistoryErrorResponse();
        final Long id = -1L;
        Assertions.assertThrows(HistoryNotFoundException.class, () -> historyErrorResponse.validate(id));
    }

    @Test
    public void testValidateWithInvalidIdVerify() {
        final HistoryErrorResponse historyErrorResponse = Mockito.spy(new HistoryErrorResponse());
        final Long id = -1L;
        Assertions.assertThrows(HistoryNotFoundException.class, () -> {
            historyErrorResponse.validate(id);
        });
        Mockito.verify(historyErrorResponse).validate(id);
    }
}
