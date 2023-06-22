package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseRepository;
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
class LicenseServiceImplTest {
    @Mock
    private LicenseRepository licenseRepository;

    @InjectMocks
    private LicenseServiceImpl licenseService;

    private License license1;
    private License license2;

    @Test
    void shouldReturnAllLicense() {
        when(licenseRepository.findAll()).thenReturn(createLicenses());
        List<License> result = licenseService.getAllLicense();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(license1, result.get(0));
        assertEquals(license2, result.get(1));
    }

    @Test
    void shouldReturnLicenseById() {
        when(licenseRepository.findById(1L)).thenReturn(of(createLicenses().get(0)));
        License result = licenseService.getLicenseById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(licenseRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> licenseService.getLicenseById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        licenseService.createLicense(any());
        verify(licenseRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateEntity() {
        License license = new License(1L, (byte) 123, new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        when(licenseRepository.save(license)).thenReturn(license);
        licenseService.updateLicense(license);
        verify(licenseRepository, times(1)).save(license);
    }

    @Test
    void shouldDeleteToRepository() {
        licenseService.deleteLicense(any());
        verify(licenseRepository, times(1)).deleteById(any());
    }

    List<License> createLicenses() {
        license1 = new License(1L, (byte) 123, new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        license2 = new License(2L, (byte) 123, new BankDetails(2L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        return List.of(license1, license2);
    }
}