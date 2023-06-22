package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.PhoneTransferDto;
import com.bank.transfer.service.phoneTransfer.PhoneTransferService;
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
class RestPhoneTransferControllerTest {

    @Autowired
    private RestPhoneTransferController phoneTransferController;

    @MockBean
    private PhoneTransferService phoneTransferService;

    @Test
    void getPhoneTransfers() {
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
        Mockito.when(phoneTransferService.getAllTransfer()).thenReturn(phoneTransfersDto);
        List<PhoneTransferDto> phoneTransfersDtoToTest = phoneTransferController.getPhoneTransfers();
        Assertions.assertNotNull(phoneTransfersDtoToTest);
        Assertions.assertEquals(phoneTransfersDto, phoneTransfersDtoToTest);
    }

    @Test
    void getPhoneTransfer() {
        PhoneTransferDto phoneTransferDto = PhoneTransferDto.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        Mockito.when(phoneTransferService.getTransferById(1L)).thenReturn(phoneTransferDto);
        PhoneTransferDto phoneTransferDtoToTest = phoneTransferController.getPhoneTransfer(1L);
        Assertions.assertNotNull(phoneTransferDtoToTest);
        Assertions.assertEquals(phoneTransferDto, phoneTransferDtoToTest);
    }

    @Test
    void savePhoneTransfer() {
        PhoneTransferDto phoneTransferDto = PhoneTransferDto.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        phoneTransferController.savePhoneTransfer(phoneTransferDto, bindingResult);
        Mockito.verify(phoneTransferService, Mockito.times(1))
                .saveTransfer(phoneTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> phoneTransferController
                .savePhoneTransfer(phoneTransferDto, bindingResult));

        Mockito.verify(phoneTransferService, Mockito.times(1))
                .saveTransfer(phoneTransferDto);
    }

    @Test
    void updatePhoneTransfer() {
        PhoneTransferDto phoneTransferDto = PhoneTransferDto.builder()
                .id(1L)
                .phoneNumber(1)
                .amount(BigDecimal.valueOf(1))
                .purpose("1")
                .accountDetailsId(1)
                .build();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        phoneTransferController.savePhoneTransfer(phoneTransferDto, bindingResult);
        Mockito.verify(phoneTransferService, Mockito.times(1))
                .saveTransfer(phoneTransferDto);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(
                List.of(new ObjectError("123", "123")));

        assertThrows(Exception.class, () -> phoneTransferController
                .savePhoneTransfer(phoneTransferDto, bindingResult));

        Mockito.verify(phoneTransferService, Mockito.times(1))
                .saveTransfer(phoneTransferDto);
    }

    @Test
    void deletePhoneTransfer() {
        phoneTransferController.deletePhoneTransfer(1L);
        Mockito.verify(phoneTransferService, Mockito.times(1)).deleteTransferById(1L);
    }
}