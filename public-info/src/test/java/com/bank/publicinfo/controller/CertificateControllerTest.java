package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.CertificateServiceImpl;
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
@WebMvcTest(CertificateController.class)
class CertificateControllerTest {
    @Autowired
    private CertificateController certificateController;

    @MockBean
    private CertificateServiceImpl certificateService;

    private BankDetails bankDetails = new BankDetails();
    private CertificateDTO certificateDTO1;
    private CertificateDTO certificateDTO2;

    @Test
    void testGetAllCertificates() {
        List<CertificateDTO> certificateDTO = List.of(createCertificates().get(0), createCertificates().get(1));
        when(certificateService.getAllCertificate()).thenReturn(certificateDTO.stream().map(PublicInfoMapper.INSTANCE::convertToCertificate).collect(Collectors.toList()));
        List<CertificateDTO> certificateDTOToTest = certificateController.getAllCertificates();
        assertNotNull(certificateDTO);
        assertNotNull(certificateDTOToTest);
        assertEquals(certificateDTO.size(), certificateDTOToTest.size());
    }

    @Test
    void testGetCertificateById() {
        CertificateDTO certificateDTO = createCertificates().get(0);
        when(certificateService.getCertificateById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToCertificate(certificateDTO));
        CertificateDTO certificateDTOToTest = certificateController.getCertificateById(1L);
        assertNotNull(certificateDTO);
        assertNotNull(certificateDTOToTest);
        assertEquals(certificateDTO.getPhoto(), certificateDTOToTest.getPhoto());
        assertEquals(certificateDTO.getCertificate(), certificateDTOToTest.getCertificate());
    }

    @Test
    void testCreateCertificate() {
        Certificate certificate = new Certificate(1L, (byte) 123, bankDetails);
        certificateController.createCertificate(PublicInfoMapper.INSTANCE.convertToCertificateDTO(certificate));
        verify(certificateService, times(1)).createCertificate(any());
    }

    @Test
    void testUpdateCertificate() {
        Certificate certificate = new Certificate(1L, (byte) 123, bankDetails);
        certificateController.updateCertificate(certificate.getId(), PublicInfoMapper.INSTANCE.convertToCertificateDTO(certificate));
        verify(certificateService, times(1)).updateCertificate(any());
    }

    @Test
    void testDeleteCertificate() {
        certificateController.deleteCertificate(any());
        verify(certificateService, times(1)).deleteCertificate(any());
    }

    List<CertificateDTO> createCertificates() {
        certificateDTO1 = new CertificateDTO((byte) 123, bankDetails);
        certificateDTO2 = new CertificateDTO((byte) 123, bankDetails);
        return List.of(certificateDTO1, certificateDTO2);
    }
}