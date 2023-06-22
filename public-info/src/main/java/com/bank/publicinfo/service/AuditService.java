package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;

import java.util.List;

public interface AuditService {
    List<Audit> getAllAudits();
    Audit getAuditById(Long id);
    void createAudit(Audit audit);
}
