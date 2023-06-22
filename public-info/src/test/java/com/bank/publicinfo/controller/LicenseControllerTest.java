package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.LicenseServiceImpl;
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
@WebMvcTest(LicenseController.class)
class LicenseControllerTest {
    @Autowired
    private LicenseController licenseController;

    @MockBean
    private LicenseServiceImpl licenseService;

    private BankDetails bankDetails = new BankDetails();
    private LicenseDTO licenseDTO1;
    private LicenseDTO licenseDTO2;

    @Test
    void testGetAllLicenses() {
        List<LicenseDTO> licenseDTO = List.of(createLicenses().get(0), createLicenses().get(1));
        when(licenseService.getAllLicense()).thenReturn(licenseDTO.stream().map(PublicInfoMapper.INSTANCE::convertToLicense).collect(Collectors.toList()));
        List<LicenseDTO> licenseDTOToTest = licenseController.getAllLicenses();
        assertNotNull(licenseDTO);
        assertNotNull(licenseDTOToTest);
        assertEquals(licenseDTO.size(), licenseDTOToTest.size());
    }

    @Test
    void testGetLicenseById() {
        LicenseDTO licenseDTO = createLicenses().get(0);
        when(licenseService.getLicenseById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToLicense(licenseDTO));
        LicenseDTO licenseDTOToTest = licenseController.getLicenseById(1L);
        assertNotNull(licenseDTO);
        assertNotNull(licenseDTOToTest);
        assertEquals(licenseDTO.getPhoto(), licenseDTOToTest.getPhoto());
        assertEquals(licenseDTO.getLicense(), licenseDTOToTest.getLicense());
    }

    @Test
    void testCreateLicense() {
        License license = new License(1L, (byte) 123, bankDetails);
        licenseController.createLicense(PublicInfoMapper.INSTANCE.convertToLicenseDTO(license));
        verify(licenseService, times(1)).createLicense(any());
    }

    @Test
    void testUpdateLicense() {
        License license = new License(1L, (byte) 123, bankDetails);
        licenseController.updateLicense(license.getId(), PublicInfoMapper.INSTANCE.convertToLicenseDTO(license));
        verify(licenseService, times(1)).updateLicense(any());
    }

    @Test
    void testDeleteLicense() {
        licenseController.deleteLicense(any());
        verify(licenseService, times(1)).deleteLicense(any());
    }

    List<LicenseDTO> createLicenses() {
        licenseDTO1 = new LicenseDTO((byte) 123, bankDetails);
        licenseDTO2 = new LicenseDTO((byte) 123, bankDetails);
        return List.of(licenseDTO1, licenseDTO2);
    }
}