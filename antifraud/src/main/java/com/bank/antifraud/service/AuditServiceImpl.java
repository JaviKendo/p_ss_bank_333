package com.bank.antifraud.service;

import com.bank.antifraud.custom_audit.AuditorImpl;
import com.bank.antifraud.dto.AuditDTO;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;
    private final AuditorImpl auditor;


    @Override
    public List<AuditDTO> getAllAudits() {
        return auditMapper.toDTOList(auditRepository.findAll());
    }

    @Override
    public AuditDTO getAuditById(Long id) {
        return auditMapper.toDTO(auditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Audit not found with id " + id)));
    }

    @Override
    @Transactional
    public <U, T> void addAudit(Object[] objects, U timeCreatedOrUpdatedObject, T object) {
        auditor.doAudit(objects, timeCreatedOrUpdatedObject, object);
    }

}
