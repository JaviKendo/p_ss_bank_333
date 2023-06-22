package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateDTOTest {
    BankDetails bankDetails = new BankDetails();
    CertificateDTO certificateDTO = new CertificateDTO((byte) 123, bankDetails);

    @Test
    void getPhoto() {
        assertEquals((byte) 123, certificateDTO.getPhoto(), "photo не совпали");
    }

    @Test
    void getCertificate() {
        assertEquals(bankDetails, certificateDTO.getCertificate(), "BankDetails не совпали");
    }

    @Test
    void setPhoto() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setPhoto((byte) 123);
        assertEquals((byte) 123, certificateDTO.getPhoto(), "setPhoto отработал не верно");
    }

    @Test
    void setCertificate() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setCertificate(bankDetails);
        assertEquals(bankDetails, certificateDTO.getCertificate(), "setCertificate отработал не верно");
    }
}