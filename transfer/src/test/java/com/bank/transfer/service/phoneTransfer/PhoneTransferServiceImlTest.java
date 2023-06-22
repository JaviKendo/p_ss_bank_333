package com.bank.transfer.service.phoneTransfer;

import com.bank.transfer.dto.transferDto.PhoneTransferDto;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class PhoneTransferServiceImlTest {
    @Autowired
    private PhoneTransferService phoneTransferService;

    @MockBean
    private PhoneTransferRepository phoneTransferRepository;

    @Test
    void saveTransfer() {
        phoneTransferService.saveTransfer(PhoneTransferDto.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build());
        Mockito.verify(phoneTransferRepository, Mockito.times(1))
                .save(PhoneTransfer.builder()
                        .id(1L)
                        .phoneNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build());
    }

    @Test
    void deleteTransferById() {
        phoneTransferService.deleteTransferById(1L);
        Mockito.verify(phoneTransferRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void getTransferById() {
        PhoneTransfer phoneTransfer = PhoneTransfer.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        PhoneTransferDto phoneTransferDto = PhoneTransferDto.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(phoneTransferRepository.getReferenceById(1L)).thenReturn(phoneTransfer);
        PhoneTransferDto phoneTransferDtoToTest = phoneTransferService.getTransferById(1L);
        Assertions.assertNotNull(phoneTransferDtoToTest);
        Assertions.assertEquals(phoneTransferDto, phoneTransferDtoToTest);
    }

    @Test
    void getAllTransfer() {
        List<PhoneTransfer> phoneTransfers = List.of(
                PhoneTransfer.builder()
                        .id(1L)
                        .phoneNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                PhoneTransfer.builder()
                        .id(2L)
                        .phoneNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
        List<PhoneTransferDto> phoneTransfersDto = List.of(
                PhoneTransferDto.builder()
                        .id(1L)
                        .phoneNumber(1)
                        .amount(BigDecimal.valueOf(1))
                        .purpose("1")
                        .accountDetailsId(1)
                        .build(),
                PhoneTransferDto.builder()
                        .id(2L)
                        .phoneNumber(2)
                        .amount(BigDecimal.valueOf(2))
                        .purpose("2")
                        .accountDetailsId(2)
                        .build());
        Mockito.when(phoneTransferRepository.findAll()).thenReturn(phoneTransfers);
        List<PhoneTransferDto> phoneTransfersDtoToTest = phoneTransferService.getAllTransfer();
        Assertions.assertNotNull(phoneTransfersDtoToTest);
        Assertions.assertEquals(phoneTransfersDto, phoneTransfersDtoToTest);
    }
}