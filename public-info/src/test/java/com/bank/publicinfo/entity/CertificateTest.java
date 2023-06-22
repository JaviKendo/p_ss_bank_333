package com.bank.publicinfo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificateTest {
    BankDetails bankDetails = new BankDetails();
    Certificate certificate = new Certificate(1L, (byte) 123, bankDetails);

    @Test
    void getId() {
        assertEquals(1L, certificate.getId(), "id не совпали");
    }

    @Test
    void getPhoto() {
        assertEquals((byte) 123, certificate.getPhoto(), "photo не совпали");
    }

    @Test
    void getCertificate() {
        assertEquals(bankDetails, certificate.getCertificate(), "BankDetails не совпали");
    }

    @Test
    void setId() {
        Certificate certificate = new Certificate();
        certificate.setId(1L);
        assertEquals(1L, certificate.getId(), "setId отработал не верно");
    }

    @Test
    void setPhoto() {
        Certificate certificate = new Certificate();
        certificate.setPhoto((byte) 123);
        assertEquals((byte) 123, certificate.getPhoto(), "setPhoto отработал не верно");
    }

    @Test
    void setCertificate() {
        Certificate certificate = new Certificate();
        certificate.setCertificate(bankDetails);
        assertEquals(bankDetails, certificate.getCertificate(), "setCertificate отработал не верно");
    }
}