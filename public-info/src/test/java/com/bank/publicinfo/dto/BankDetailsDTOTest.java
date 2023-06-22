package com.bank.publicinfo.dto;


import com.bank.publicinfo.entity.BankDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDetailsDTOTest {
    BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123,
            "city", "jointStockCompany", "name");

    @Test
    void getBik() {
        assertEquals(123L, bankDetailsDTO.getBik(), "bik не совпали");
    }

    @Test
    void getInn() {
        assertEquals(123L, bankDetailsDTO.getInn(), "inn не совпали");
    }

    @Test
    void getKpp() {
        assertEquals(123L, bankDetailsDTO.getKpp(), "kpp не совпали");
    }

    @Test
    void getCorAccount() {
        assertEquals(123, bankDetailsDTO.getCorAccount(), "corAccount не совпали");
    }

    @Test
    void getCity() {
        assertEquals("city", bankDetailsDTO.getCity(), "city не совпали");
    }

    @Test
    void getJointStockCompany() {
        assertEquals("jointStockCompany", bankDetailsDTO.getJointStockCompany(), "jointStockCompany не совпали");
    }

    @Test
    void getName() {
        assertEquals("name", bankDetailsDTO.getName(), "name не совпали");
    }

    @Test
    void setBik() {
        BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetails.setBik(123L);
        assertEquals(123L, bankDetails.getBik(), "setBik отработал не верно");
    }

    @Test
    void setInn() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setInn(123L);
        assertEquals(123L, bankDetailsDTO.getInn(), "setInn отработал не верно");
    }

    @Test
    void setKpp() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setKpp(123L);
        assertEquals(123L, bankDetailsDTO.getKpp(), "setKpp отработал не верно");
    }

    @Test
    void setCorAccount() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setCorAccount(123);
        assertEquals(123, bankDetailsDTO.getCorAccount(), "setCorAccount отработал не верно");
    }

    @Test
    void setCity() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setCity("city");
        assertEquals("city", bankDetailsDTO.getCity(), "setCity отработал не верно");
    }

    @Test
    void setJointStockCompany() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setJointStockCompany("jointStockCompany");
        assertEquals("jointStockCompany", bankDetailsDTO.getJointStockCompany(), "setJointStockCompany отработал не верно");
    }

    @Test
    void setName() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(123L, 123L, 123L, 123, "city", "jointStockCompany", "name");
        bankDetailsDTO.setName("name");
        assertEquals("name", bankDetailsDTO.getName(), "setName отработал не верно");
    }
}