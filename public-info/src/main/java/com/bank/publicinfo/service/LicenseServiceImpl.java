package com.bank.publicinfo.service;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<License> getAllLicense() {
        return licenseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public License getLicenseById(Long id) {
        return licenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("License с идентификатором " + id + " не найден"));
    }

    @Override
    @Auditable(actionType = AuditActionType.CREATE)
    public void createLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    @Auditable(actionType = AuditActionType.UPDATE)
    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    @Auditable(actionType = AuditActionType.DELETE)
    public void deleteLicense(Long id) {
        try {
            licenseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("License с идентификатором " + id + " не найден");
        }
    }
}
