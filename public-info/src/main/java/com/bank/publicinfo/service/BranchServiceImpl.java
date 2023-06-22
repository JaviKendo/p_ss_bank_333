package com.bank.publicinfo.service;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final AtmRepository atmRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, AtmRepository atmRepository) {
        this.branchRepository = branchRepository;
        this.atmRepository = atmRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Branch с идентификатором " + id + " не найден"));
    }

    @Override
    @Auditable(actionType = AuditActionType.CREATE)
    public void createBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    @Auditable(actionType = AuditActionType.UPDATE)
    public void updateBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    @Auditable(actionType = AuditActionType.DELETE)
    public void deleteBranch(Long id) {
        try {
            branchRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Branch с идентификатором " + id + " не найден");
        }
    }
}
