package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.BankDetails;

import java.util.List;

public interface BankDetailsService {
    List<BankDetails> getAllBankDetails();
    BankDetails getBankDetailsById(Long id);
    void createBankDetails(BankDetails bankDetails);
    void updateBankDetails(BankDetails bankDetails);
    void deleteBankDetails(Long id);
}
