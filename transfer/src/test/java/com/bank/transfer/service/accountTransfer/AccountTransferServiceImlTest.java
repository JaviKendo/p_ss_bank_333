package com.bank.transfer.service.accountTransfer;

import com.bank.transfer.dto.transferDto.AccountTransferDto;
import com.bank.transfer.entity.transfers.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class AccountTransferServiceImlTest {

    @Autowired
    private AccountTransferService accountTransferService;

    @MockBean
    private AccountTransferRepository accountTransferRepository;

    @Test
    void saveTransfer() {
        accountTransferService.saveTransfer(AccountTransferDto.builder()
                .id(1L)
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build());
        Mockito.verify(accountTransferRepository, Mockito.times(1))
                .save(AccountTransfer.builder()
                        .id(1L)
                        .accountNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build());
    }

    @Test
    void deleteTransferById() {
        accountTransferService.deleteTransferById(1L);
        Mockito.verify(accountTransferRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void getTransferById() {
        AccountTransfer accountTransfer = AccountTransfer.builder()
                .id(1L)
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        AccountTransferDto accountTransferDto = AccountTransferDto.builder()
                .id(1L)
                .accountNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(accountTransferRepository.getReferenceById(1L)).thenReturn(accountTransfer);
        AccountTransferDto accountTransferDtoToTest = accountTransferService.getTransferById(1L);
        Assertions.assertNotNull(accountTransferDtoToTest);
        Assertions.assertEquals(accountTransferDto, accountTransferDtoToTest);
    }

    @Test
    void getAllTransfer() {
        List<AccountTransfer> accountTransfers = List.of(
                AccountTransfer.builder()
                        .id(1L)
                        .accountNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                AccountTransfer.builder()
                        .id(2L)
                        .accountNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
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
        Mockito.when(accountTransferRepository.findAll()).thenReturn(accountTransfers);
        List<AccountTransferDto> accountTransfersDtoToTest = accountTransferService.getAllTransfer();
        Assertions.assertNotNull(accountTransfersDtoToTest);
        Assertions.assertEquals(accountTransfersDto, accountTransfersDtoToTest);
    }
}