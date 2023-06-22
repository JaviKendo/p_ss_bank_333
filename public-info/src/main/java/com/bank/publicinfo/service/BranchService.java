package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;

import java.util.List;

public interface BranchService {
    List<Branch> getAllBranch();
    Branch getBranchById(Long id);
    void createBranch(Branch branch);
    void updateBranch(Branch branch);
    void deleteBranch(Long id);
}
