package com.bank.publicinfo.service;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificate> getAllCertificate() {
        return certificateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Certificate с идентификатором " + id + " не найден"));
    }

    @Override
    @Auditable(actionType = AuditActionType.CREATE)
    public void createCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    @Override
    @Auditable(actionType = AuditActionType.UPDATE)
    public void updateCertificate(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    @Override
    @Auditable(actionType = AuditActionType.DELETE)
    public void deleteCertificate(Long id) {
        try {
            certificateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Certificate с идентификатором " + id + " не найден");
        }
    }
}
