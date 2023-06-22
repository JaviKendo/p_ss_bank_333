package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.CardTransferDto;
import com.bank.transfer.service.cardTransfer.CardTransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RestCardTransferControllerTest {
    @Autowired
    private RestCardTransferController cardTransferController;

    @MockBean
    private CardTransferService cardTransferService;

    @Test
    void getCardTransfers() {
        List<CardTransferDto> cardTransfersDto = List.of(
                CardTransferDto.builder()
                        .id(1L)
                        .cardNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                CardTransferDto.builder()
                        .id(2L)
                        .cardNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
        Mockito.when(cardTransferService.getAllTransfer()).thenReturn(cardTransfersDto);
        List<CardTransferDto> cardTransfersDtoToTest = cardTransferController.getCardTransfers();
        Assertions.assertNotNull(cardTransfersDtoToTest);
        Assertions.assertEquals(cardTransfersDto, cardTransfersDtoToTest);
    }

    @Test
    void getCardTransfer() {
        CardTransferDto cardTransferDto = CardTransferDto.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(cardTransferService.getTransferById(1L)).thenReturn(cardTransferDto);
        CardTransferDto cardTransferDtoToTest = cardTransferController.getCardTransfer(1L);
        Assertions.assertNotNull(cardTransferDtoToTest);
        Assertions.assertEquals(cardTransferDto, cardTransferDtoToTest);
    }

    @Test
    void saveCardTransfer() {
        CardTransferDto cardTransferDto = CardTransferDto.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        cardTransferController.saveCardTransfer(cardTransferDto, bindingResult);
        Mockito.verify(cardTransferService, Mockito.times(1))
                .saveTransfer(cardTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> cardTransferController
                .saveCardTransfer(cardTransferDto, bindingResult));

        Mockito.verify(cardTransferService, Mockito.times(1))
                .saveTransfer(cardTransferDto);
    }

    @Test
    void updateCardTransfer() {
        CardTransferDto cardTransferDto = CardTransferDto.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        cardTransferController.saveCardTransfer(cardTransferDto, bindingResult);
        Mockito.verify(cardTransferService, Mockito.times(1))
                .saveTransfer(cardTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> cardTransferController
                .saveCardTransfer(cardTransferDto, bindingResult));

        Mockito.verify(cardTransferService, Mockito.times(1))
                .saveTransfer(cardTransferDto);
    }

    @Test
    void deleteCardTransfer() {
        cardTransferController.deleteCardTransfer(1L);
        Mockito.verify(cardTransferService, Mockito.times(1)).deleteTransferById(1L);
    }
}