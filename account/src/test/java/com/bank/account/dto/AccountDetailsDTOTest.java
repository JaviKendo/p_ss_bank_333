package com.bank.account.dto;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import org.springframework.test.util.AssertionErrors;


class AccountDetailsDTOTest {

    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    void getId() {
        AssertionErrors.assertEquals("id не совпали", 1L, detailsDTO.getId());
    }

    @Test
    void getPassportId() {
        AssertionErrors.assertEquals("Passport id не совпали", 1L, detailsDTO.getPassportId());

    }

    @Test
    void getAccountNumber() {
        AssertionErrors.assertEquals("account number не совпали", 1L, detailsDTO.getAccountNumber());
    }

    @Test
    void getBankDetailsId() {
        AssertionErrors.assertEquals("bank details id не совпали", 1L, detailsDTO.getBankDetailsId());
    }

    @Test
    void getMoney() {
        AssertionErrors.assertEquals("money не совпали", BigInteger.valueOf(220L), detailsDTO.getMoney());

    }

    @Test
    void getNegativeBalance() {
        AssertionErrors.assertEquals("negative balance не совпали", false, detailsDTO.getNegativeBalance());

    }

    @Test
    void getProfileId() {
        AssertionErrors.assertEquals("profile id не совпали", 1L, detailsDTO.getProfileId());

    }

    @Test
    void setId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setId(1L);
        AssertionErrors.assertEquals("setId отработал не верно", 1L, accountDetailsDTO.getId());
    }

    @Test
    void setPassportId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setPassportId(1L);
        AssertionErrors.assertEquals("setPassportId отработал не верно", 1L, accountDetailsDTO.getPassportId());
    }

    @Test
    void setAccountNumber() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setAccountNumber(1L);
        AssertionErrors.assertEquals("setAccountNumber отработал не верно", 1L, accountDetailsDTO.getAccountNumber());
    }

    @Test
    void setBankDetailsId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setBankDetailsId(1L);
        AssertionErrors.assertEquals("setBankDetailsId отработал не верно", 1L, accountDetailsDTO.getBankDetailsId());
    }

    @Test
    void setMoney() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setMoney(BigInteger.valueOf(200L));
        AssertionErrors.assertEquals("setMoney отработал не верно", BigInteger.valueOf(200L), accountDetailsDTO.getMoney());
    }

    @Test
    void setNegativeBalance() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setNegativeBalance(true);
        AssertionErrors.assertEquals("setNegativeBalance отработал не верно", true, accountDetailsDTO.getNegativeBalance());
    }

    @Test
    void setProfileId() {
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setProfileId(1L);
        AssertionErrors.assertEquals("setProfileId отработал не верно", 1L, accountDetailsDTO.getProfileId());
    }
}