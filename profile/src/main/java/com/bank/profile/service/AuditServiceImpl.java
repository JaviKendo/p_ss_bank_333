package com.bank.profile.service;

import com.bank.profile.entity.Audit;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    @Override
    public Audit getAuditById(long id) {
        return auditRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveAudit(Audit audit) {
        auditRepository.save(audit);
    }

    @Override
    public void updateAudit(long id, Audit updetedAudit) {
        updetedAudit.setId(id);
        auditRepository.save(updetedAudit);
    }

    @Override
    public void deleteAudit(long id) {
        auditRepository.deleteById(id);
    }
}
