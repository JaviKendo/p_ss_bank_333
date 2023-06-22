package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.AccountTransferDto;
import com.bank.transfer.service.accountTransfer.AccountTransferService;
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
class RestAccountTransferControllerTest {

    @Autowired
    private RestAccountTransferController accountTransferController;

    @MockBean
    private AccountTransferService accountTransferService;
    @Test
    void getAccountTransfers() {
        List<AccountTransferDto> accountTransfersDto = List.of(
                AccountTransferDto.builder()
                        .id(1L)
                        .accountNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                AccountTransferDto.builder()
                        .id(2L)
                        .accountNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
        Mockito.when(accountTransferService.getAllTransfer()).thenReturn(accountTransfersDto);
        List<AccountTransferDto> accountTransfersDtoToTest = accountTransferController.getAccountTransfers();
        Assertions.assertNotNull(accountTransfersDtoToTest);
        Assertions.assertEquals(accountTransfersDto, accountTransfersDtoToTest);
    }

    @Test
    void getAccountTransfer() {
        AccountTransferDto accountTransferDto = AccountTransferDto.builder()
                .id(1L)
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(accountTransferService.getTransferById(1L)).thenReturn(accountTransferDto);
        AccountTransferDto accountTransferDtoToTest = accountTransferController.getAccountTransfer(1L);
        Assertions.assertNotNull(accountTransferDtoToTest);
        Assertions.assertEquals(accountTransferDto, accountTransferDtoToTest);
    }

    @Test
    void saveAccountTransfer() {
        AccountTransferDto accountTransferDto = AccountTransferDto.builder()
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        accountTransferController.saveAccountTransfer(accountTransferDto, bindingResult);
        Mockito.verify(accountTransferService, Mockito.times(1))
                .saveTransfer(accountTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> accountTransferController
                .saveAccountTransfer(accountTransferDto, bindingResult));

        Mockito.verify(accountTransferService, Mockito.times(1))
                .saveTransfer(accountTransferDto);
    }

    @Test
    void updateAccountTransfer() {
        AccountTransferDto accountTransferDto = AccountTransferDto.builder()
                .id(1L)
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        accountTransferController.saveAccountTransfer(accountTransferDto, bindingResult);
        Mockito.verify(accountTransferService, Mockito.times(1))
                .saveTransfer(accountTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> accountTransferController
                .saveAccountTransfer(accountTransferDto, bindingResult));

        Mockito.verify(accountTransferService, Mockito.times(1))
                .saveTransfer(accountTransferDto);
    }

    @Test
    void deleteAccountTransfer() {
        accountTransferController.deleteAccountTransfer(1L);
        Mockito.verify(accountTransferService, Mockito.times(1)).deleteTransferById(1L);
    }
}