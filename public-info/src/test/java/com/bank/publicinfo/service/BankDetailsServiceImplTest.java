package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BankDetailsServiceImplTest {
    @Mock
    private BankDetailsRepository bankDetailsRepository;

    @InjectMocks
    private BankDetailsServiceImpl bankDetailsService;

    private BankDetails bankDetails1;
    private BankDetails bankDetails2;

    @Test
    void shouldReturnAllBankDetails() {
        when(bankDetailsRepository.findAll()).thenReturn(createBankDetails());
        List<BankDetails> result = bankDetailsService.getAllBankDetails();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(bankDetails1, result.get(0));
        assertEquals(bankDetails2, result.get(1));
    }

    @Test
    void shouldReturnBankDetailsById() {
        when(bankDetailsRepository.findById(1L)).thenReturn(of(createBankDetails().get(0)));
        BankDetails result = bankDetailsService.getBankDetailsById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(bankDetailsRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> bankDetailsService.getBankDetailsById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        bankDetailsService.createBankDetails(any());
        verify(bankDetailsRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateEntity() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        when(bankDetailsRepository.save(bankDetails)).thenReturn(bankDetails);
        bankDetailsService.updateBankDetails(bankDetails);
        verify(bankDetailsRepository, times(1)).save(bankDetails);
    }

    @Test
    void shouldDeleteToRepository() {
        bankDetailsService.deleteBankDetails(any());
        verify(bankDetailsRepository, times(1)).deleteById(any());
    }

    List<BankDetails> createBankDetails() {
        bankDetails1 = new BankDetails(1L, 123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        bankDetails2 = new BankDetails(2L, 123L, 123L, 123L, 123,
                "city", "jointStockCompany", "name");
        return List.of(bankDetails1, bankDetails2);
    }
}