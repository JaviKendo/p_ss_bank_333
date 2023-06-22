package com.bank.account.entity;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import org.springframework.test.util.AssertionErrors;
class AccountDetailsTest {

    AccountDetails details = new AccountDetails(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    void getId() {
        AssertionErrors.assertEquals("id не совпали", 1L, details.getId());
    }

    @Test
    void getPassportId() {
        AssertionErrors.assertEquals("Passport id не совпали", 1L, details.getPassportId());

    }

    @Test
    void getAccountNumber() {
        AssertionErrors.assertEquals("account number не совпали", 1L, details.getAccountNumber());

    }

    @Test
    void getBankDetailsId() {
        AssertionErrors.assertEquals("bank details id не совпали", 1L, details.getBankDetailsId());
    }

    @Test
    void getMoney() {
        AssertionErrors.assertEquals("money не совпали", BigInteger.valueOf(220L), details.getMoney());
    }

    @Test
    void getNegativeBalance() {
        AssertionErrors.assertEquals("negative balance не совпали", false, details.getNegativeBalance());

    }

    @Test
    void getProfileId() {
        AssertionErrors.assertEquals("profile id не совпали", 1L, details.getProfileId());
    }

    @Test
    void setId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(1L);
        AssertionErrors.assertEquals("setId отработал не верно", 1L, accountDetails.getId());

    }

    @Test
    void setPassportId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setPassportId(1L);
        AssertionErrors.assertEquals("setPassportId отработал не верно", 1L, accountDetails.getPassportId());

    }

    @Test
    void setAccountNumber() {
        AccountDetails accountDetails = new AccountDetails();;
        accountDetails.setAccountNumber(1L);
        AssertionErrors.assertEquals("setAccountNumber отработал не верно", 1L, accountDetails.getAccountNumber());

    }

    @Test
    void setBankDetailsId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setBankDetailsId(1L);
        AssertionErrors.assertEquals("setBankDetailsId отработал не верно", 1L, accountDetails.getBankDetailsId());

    }

    @Test
    void setMoney() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setMoney(BigInteger.valueOf(200L));
        AssertionErrors.assertEquals("setMoney отработал не верно", BigInteger.valueOf(200L), accountDetails.getMoney());

    }

    @Test
    void setNegativeBalance() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setNegativeBalance(true);
        AssertionErrors.assertEquals("setNegativeBalance отработал не верно", true, accountDetails.getNegativeBalance());

    }

    @Test
    void setProfileId() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setProfileId(1L);
        AssertionErrors.assertEquals("setProfileId отработал не верно", 1L, accountDetails.getProfileId());

    }
}