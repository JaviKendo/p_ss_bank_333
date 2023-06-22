package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDTO;
import com.bank.authorization.entity.User;

import java.util.List;

public interface AuditService {
    AuditDTO findById(Long id);


    List<AuditDTO> findAll();


    void createAudit(User user, String operationType);

}
