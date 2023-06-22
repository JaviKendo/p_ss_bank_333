package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateRepository;
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
class CertificateServiceImplTest {
    @Mock
    private CertificateRepository certificateRepository;

    @InjectMocks
    private CertificateServiceImpl certificateService;

    private Certificate certificate1;
    private Certificate certificate2;

    @Test
    void shouldReturnAllCertificate() {
        when(certificateRepository.findAll()).thenReturn(createCertificates());
        List<Certificate> result = certificateService.getAllCertificate();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(certificate1, result.get(0));
        assertEquals(certificate2, result.get(1));
    }

    @Test
    void shouldReturnCertificateById() {
        when(certificateRepository.findById(1L)).thenReturn(of(createCertificates().get(0)));
        Certificate result = certificateService.getCertificateById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(certificateRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> certificateService.getCertificateById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        certificateService.createCertificate(any());
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateEntity() {
        Certificate certificate = new Certificate(1L, (byte) 123, new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        certificateService.updateCertificate(certificate);
        verify(certificateRepository, times(1)).save(certificate);
    }

    @Test
    void shouldDeleteToRepository() {
        certificateService.deleteCertificate(any());
        verify(certificateRepository, times(1)).deleteById(any());
    }

    List<Certificate> createCertificates() {
        certificate1 = new Certificate(1L, (byte) 123, new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        certificate2 = new Certificate(2L, (byte) 123, new BankDetails(2L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name"));
        return List.of(certificate1, certificate2);
    }
}