package com.bank.transfer.service.cardTransfer;

import com.bank.transfer.dto.transferDto.CardTransferDto;
import com.bank.transfer.entity.transfers.CardTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class CardTransferServiceImlTest {

    @Autowired
    private CardTransferService cardTransferService;

    @MockBean
    private CardTransferRepository cardTransferRepository;

    @Test
    void saveTransfer() {
        cardTransferService.saveTransfer(CardTransferDto.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build());
        Mockito.verify(cardTransferRepository, Mockito.times(1))
                .save(CardTransfer.builder()
                        .id(1L)
                        .cardNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build());
    }

    @Test
    void deleteTransferById() {
        cardTransferService.deleteTransferById(1L);
        Mockito.verify(cardTransferRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void getTransferById() {
        CardTransfer cardTransfer = CardTransfer.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        CardTransferDto cardTransferDto = CardTransferDto.builder()
                .id(1L)
                .cardNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(cardTransferRepository.getReferenceById(1L)).thenReturn(cardTransfer);
        CardTransferDto cardTransferDtoToTest = cardTransferService.getTransferById(1L);
        Assertions.assertNotNull(cardTransferDtoToTest);
        Assertions.assertEquals(cardTransferDto, cardTransferDtoToTest);
    }

    @Test
    void getAllTransfer() {
        List<CardTransfer> cardTransfers = List.of(
                CardTransfer.builder()
                        .id(1L)
                        .cardNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                CardTransfer.builder()
                        .id(2L)
                        .cardNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
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
        Mockito.when(cardTransferRepository.findAll()).thenReturn(cardTransfers);
        List<CardTransferDto> cardTransfersDtoToTest = cardTransferService.getAllTransfer();
        Assertions.assertNotNull(cardTransfersDtoToTest);
        Assertions.assertEquals(cardTransfersDto, cardTransfersDtoToTest);
    }
}