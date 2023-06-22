package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.BankDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BankDetailsController.class)
class BankDetailsControllerTest {
    @Autowired
    private BankDetailsController bankDetailsController;

    @MockBean
    private BankDetailsServiceImpl bankDetailsService;

    private BankDetailsDTO bankDetailsDTO1;
    private BankDetailsDTO bankDetailsDTO2;

    @Test
    void testGetAllBankDetails() {
        List<BankDetailsDTO> bankDetailsDTO = List.of(createBankDetails().get(0), createBankDetails().get(1));
        when(bankDetailsService.getAllBankDetails()).thenReturn(bankDetailsDTO.stream().map(PublicInfoMapper.INSTANCE::convertToBankDetails).collect(Collectors.toList()));
        List<BankDetailsDTO> bankDetailsDTOToTest = bankDetailsController.getAllBankDetails();
        assertNotNull(bankDetailsDTO);
        assertNotNull(bankDetailsDTOToTest);
        assertEquals(bankDetailsDTO.size(), bankDetailsDTOToTest.size());
    }

    @Test
    void testGetCertificateById() {
        BankDetailsDTO bankDetailsDTO = createBankDetails().get(0);
        when(bankDetailsService.getBankDetailsById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToBankDetails(bankDetailsDTO));
        BankDetailsDTO bankDetailsDTOToTest = bankDetailsController.getCertificateById(1L);
        assertNotNull(bankDetailsDTO);
        assertNotNull(bankDetailsDTOToTest);
        assertEquals(bankDetailsDTO.getBik(), bankDetailsDTOToTest.getBik());
        assertEquals(bankDetailsDTO.getInn(), bankDetailsDTOToTest.getInn());
        assertEquals(bankDetailsDTO.getKpp(), bankDetailsDTOToTest.getKpp());
        assertEquals(bankDetailsDTO.getCorAccount(), bankDetailsDTOToTest.getCorAccount());
        assertEquals(bankDetailsDTO.getCity(), bankDetailsDTOToTest.getCity());
        assertEquals(bankDetailsDTO.getJointStockCompany(), bankDetailsDTOToTest.getJointStockCompany());
        assertEquals(bankDetailsDTO.getName(), bankDetailsDTOToTest.getName());
    }

    @Test
    void testCreateBankDetails() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        bankDetailsController.createBankDetails(PublicInfoMapper.INSTANCE.convertToBankDetailsDTO(bankDetails));
        verify(bankDetailsService, times(1)).createBankDetails(any());
    }

    @Test
    void testUpdateBankDetails() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        bankDetailsController.updateBankDetails(bankDetails.getId(), PublicInfoMapper.INSTANCE.convertToBankDetailsDTO(bankDetails));
        verify(bankDetailsService, times(1)).updateBankDetails(any());
    }

    @Test
    void testDeleteBankDetails() {
        bankDetailsController.deleteBankDetails(any());
        verify(bankDetailsService, times(1)).deleteBankDetails(any());
    }

    List<BankDetailsDTO> createBankDetails() {
        bankDetailsDTO1 = new BankDetailsDTO(123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        bankDetailsDTO2 = new BankDetailsDTO(123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        return List.of(bankDetailsDTO1, bankDetailsDTO2);
    }
}