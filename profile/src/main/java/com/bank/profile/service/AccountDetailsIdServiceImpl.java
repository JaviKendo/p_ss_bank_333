package com.bank.profile.service;

import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.AccountDetailsIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountDetailsIdServiceImpl implements AccountDetailsIdService {

    private final AccountDetailsIdRepository accountDetailsIdRepository;

    @Autowired
    public AccountDetailsIdServiceImpl(AccountDetailsIdRepository accountDetailsIdRepository) {
        this.accountDetailsIdRepository = accountDetailsIdRepository;
    }

    @Override
    public List<AccountDetailsId> getAllAccountDetailsId() {
        return accountDetailsIdRepository.findAll();
    }

    @Override
    public AccountDetailsId getAccountDetailsIdById(long id) {
        return accountDetailsIdRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveAccountDetailsId(AccountDetailsId accountDetailsId) {
        accountDetailsIdRepository.save(accountDetailsId);
    }

    @Override
    @Transactional
    public void updateAccountDetailsId(long id, AccountDetailsId updetedAccountDetailsId) {
        updetedAccountDetailsId.setId(id);
        accountDetailsIdRepository.save(updetedAccountDetailsId);
    }

    @Override
    @Transactional
    public void deleteAccountDetailsId(long id) {
        accountDetailsIdRepository.deleteById(id);
    }
}
