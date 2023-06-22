package com.bank.publicinfo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDetailsTest {
    BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123,
            "city", "jointStockCompany", "name");
    @Test
    void getId() {
        assertEquals(1L, bankDetails.getId(), "id не совпали");
    }

    @Test
    void getBik() {
        assertEquals(123L, bankDetails.getBik(), "bik не совпали");
    }

    @Test
    void getInn() {
        assertEquals(123L, bankDetails.getInn(), "inn не совпали");
    }

    @Test
    void getKpp() {
        assertEquals(123L, bankDetails.getKpp(), "kpp не совпали");
    }

    @Test
    void getCorAccount() {
        assertEquals(123, bankDetails.getCorAccount(), "corAccount не совпали");
    }

    @Test
    void getCity() {
        assertEquals("city", bankDetails.getCity(), "city не совпали");
    }

    @Test
    void getJointStockCompany() {
        assertEquals("jointStockCompany", bankDetails.getJointStockCompany(), "jointStockCompany не совпали");
    }

    @Test
    void getName() {
        assertEquals("name", bankDetails.getName(), "name не совпали");
    }

    @Test
    void setId() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setId(1L);
        assertEquals(1L, bankDetails.getId(), "setId отработал не верно");
    }

    @Test
    void setBik() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setBik(123L);
        assertEquals(123L, bankDetails.getBik(), "setBik отработал не верно");
    }

    @Test
    void setInn() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setInn(123L);
        assertEquals(123L, bankDetails.getInn(), "setInn отработал не верно");
    }

    @Test
    void setKpp() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setKpp(123L);
        assertEquals(123L, bankDetails.getKpp(), "setKpp отработал не верно");
    }

    @Test
    void setCorAccount() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setCorAccount(123);
        assertEquals(123, bankDetails.getCorAccount(), "setCorAccount отработал не верно");
    }

    @Test
    void setCity() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setCity("city");
        assertEquals("city", bankDetails.getCity(), "setCity отработал не верно");
    }

    @Test
    void setJointStockCompany() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setJointStockCompany("jointStockCompany");
        assertEquals("jointStockCompany", bankDetails.getJointStockCompany(), "setJointStockCompany отработал не верно");
    }

    @Test
    void setName() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setName("name");
        assertEquals("name", bankDetails.getName(), "setName отработал не верно");
    }
}