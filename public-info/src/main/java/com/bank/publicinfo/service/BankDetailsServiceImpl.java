package com.bank.publicinfo.service;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class BankDetailsServiceImpl implements BankDetailsService {
    private final BankDetailsRepository bankDetailsRepository;

    @Autowired
    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankDetails> getAllBankDetails() {
        return bankDetailsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BankDetails getBankDetailsById(Long id) {
        return bankDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bank details с идентификатором " + id + " не найден"));
    }

    @Override
    @Auditable(actionType = AuditActionType.CREATE)
    public void createBankDetails(BankDetails bankDetails) {
        bankDetailsRepository.save(bankDetails);
    }

    @Override
    @Auditable(actionType = AuditActionType.UPDATE)
    public void updateBankDetails(BankDetails bankDetails) {
        bankDetailsRepository.save(bankDetails);
    }

    @Override
    @Auditable(actionType = AuditActionType.DELETE)
    public void deleteBankDetails(Long id) {
        try {
            bankDetailsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Bank details с идентификатором " + id + " не найден");
        }
    }
}
