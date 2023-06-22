package com.bank.profile.service;

import com.bank.profile.entity.Audit;

import java.util.List;

public interface AuditService {
    List<Audit> getAllAudits();
    Audit getAuditById(long id);
    void saveAudit(Audit audit);
    void updateAudit(long id, Audit updetedAudit);
    void deleteAudit(long id);
}
