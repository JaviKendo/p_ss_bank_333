package com.bank.profile.service;

import com.bank.profile.entity.AccountDetailsId;

import java.util.List;

public interface AccountDetailsIdService {
    List<AccountDetailsId> getAllAccountDetailsId();
    AccountDetailsId getAccountDetailsIdById(long id);
    void saveAccountDetailsId(AccountDetailsId accountDetailsId);
    void updateAccountDetailsId(long id, AccountDetailsId updetedAccountDetailsId);
    void deleteAccountDetailsId(long id);
}
