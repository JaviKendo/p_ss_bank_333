package com.bank.publicinfo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LicenseTest {
    BankDetails bankDetails = new BankDetails();
    License license = new License(1L, (byte) 123, bankDetails);

    @Test
    void getId() {
        assertEquals(1L, license.getId(), "id не совпали");
    }

    @Test
    void getPhoto() {
        assertEquals((byte) 123, license.getPhoto(), "photo не совпали");
    }

    @Test
    void getLicense() {
        assertEquals(bankDetails, license.getLicense(), "BankDetails не совпали");
    }

    @Test
    void setId() {
        License license = new License();
        license.setId(1L);
        assertEquals(1L, license.getId(), "setId отработал не верно");
    }

    @Test
    void setPhoto() {
        License license = new License();
        license.setPhoto((byte) 123);
        assertEquals((byte) 123, license.getPhoto(), "setPhoto отработал не верно");
    }

    @Test
    void setLicense() {
        License license = new License();
        license.setLicense(bankDetails);
        assertEquals(bankDetails, license.getLicense(), "setLicense отработал не верно");
    }
}