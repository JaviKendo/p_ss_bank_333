package com.bank.publicinfo.audit;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.AuditRepository;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditRepository auditRepository;
    private final BankDetailsRepository bankDetailsRepository;

    @After(value = "@annotation(auditable)", argNames = "joinPoint,auditable")
    @Transactional
    public void logAuditActivity(JoinPoint joinPoint, Auditable auditable) {
        if (AuditActionType.CREATE == auditable.actionType()) {
            Audit audit = new Audit();
            audit.setEntityType(BankDetails.class.getSimpleName());
            BankDetails details = ((BankDetails) joinPoint.getArgs()[0]);
            addAudit(audit, details, auditable);
        }
        if (AuditActionType.UPDATE == auditable.actionType()) {
            BankDetails details = ((BankDetails) joinPoint.getArgs()[0]);
            updateAudit(details);
        }
        if (AuditActionType.DELETE == auditable.actionType()) {
            Long id = (Long) joinPoint.getArgs()[0];
            deleteAudit(id);
        }
    }

    private void addAudit(Audit audit, BankDetails details, Auditable auditable) {
        try {
            audit.setEntityJson(new ObjectMapper().writeValueAsString(details));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        audit.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        audit.setCreatedBy("TEST_NAME");
        audit.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        audit.setOperationType(auditable.actionType().name());
        audit.setModifiedBy("TEST_NAME");
        auditRepository.save(audit);
    }

    private void updateAudit(BankDetails details) {
        BankDetails updateDetails = bankDetailsRepository.findById(details.getId())
                .orElseThrow(EntityNotFoundException::new);
        Audit updateAudit = auditRepository
                .findById(updateDetails.getId())
                .orElseThrow(EntityNotFoundException::new);
        updateAudit.setOperationType(AuditActionType.UPDATE.name());
        updateAudit.setModifiedBy("TEST_NAME");
        try {
            updateAudit.setNewEntityJson(new ObjectMapper().writeValueAsString(details));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        updateAudit.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        auditRepository.save(updateAudit);
        System.out.println("New Entity JSON: " + updateAudit.getNewEntityJson());
    }

    private void deleteAudit(Long id) {
        Audit deleteAudit = auditRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        deleteAudit.setOperationType(AuditActionType.DELETE.name());
        deleteAudit.setModifiedBy("TEST_NAME");
        deleteAudit.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        try {
            deleteAudit.setNewEntityJson(new ObjectMapper().writeValueAsString(null));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        auditRepository.save(deleteAudit);
    }
}
