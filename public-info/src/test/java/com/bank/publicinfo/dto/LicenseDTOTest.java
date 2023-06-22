package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LicenseDTOTest {
    BankDetails bankDetails = new BankDetails();
    LicenseDTO licenseDTO = new LicenseDTO((byte) 123, bankDetails);

    @Test
    void getPhoto() {
        assertEquals((byte) 123, licenseDTO.getPhoto(), "photo не совпали");
    }

    @Test
    void getLicense() {
        assertEquals(bankDetails, licenseDTO.getLicense(), "BankDetails не совпали");
    }

    @Test
    void setPhoto() {
        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setPhoto((byte) 123);
        assertEquals((byte) 123, licenseDTO.getPhoto(), "setPhoto отработал не верно");
    }

    @Test
    void setLicense() {
        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setLicense(bankDetails);
        assertEquals(bankDetails, licenseDTO.getLicense(), "setLicense отработал не верно");
    }
}