package com.bank.antifraud.custom_audit;

import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.SuspiciousTransferTypeChecker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditorImpl implements Auditor {

    private final AuditRepository auditRepository;
    private final ObjectMapper objectMapper;
    private final SuspiciousTransferTypeChecker suspiciousTransferTypeChecker;

    @Override
    public <U, T> void doAudit(Object[] objects, U timeCreatedOrUpdatedObject, T object) {

        JoinPoint joinPoint = (JoinPoint) objects[0];
        Auditable auditable = (Auditable) objects[1];

        Class<?> someClass = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        ObservableEntity observableEntity =
                suspiciousTransferTypeChecker.checkAndConvertEntityType(object, someClass);
        List<Audit> allAudits = auditRepository.findAll();
        String entityJson;

        try {
            entityJson = objectMapper.writeValueAsString(observableEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String[] splittedEntityJson = entityJson.split(":", 3);

        Audit lastAuditWithThisEntity = null;
        for (int i = allAudits.size() - 1; i >= 0; i--) {
            if (allAudits.get(i).getEntityJson().startsWith(splittedEntityJson[0] + ":" + splittedEntityJson[1])) {
                lastAuditWithThisEntity = allAudits.get(i);
                i = -1;
            }
        }

        this.getCompletedAudit(new Object[]{observableEntity, lastAuditWithThisEntity, auditable.actionType().name(),
                entityJson}, timeCreatedOrUpdatedObject);
    }

    @Override
    @Transactional
    public <T> Audit getCompletedAudit(Object[] objects, T timeCreatedOrUpdatedObject) {
        Audit lastAuditWithThisEntity = (Audit) objects[1];
        String auditAction = (String) objects[2];
        String entityJson = (String) objects[3];
        Timestamp creationOrModificationTime = Timestamp.valueOf((LocalDateTime) timeCreatedOrUpdatedObject);

        Audit audit;

        if (lastAuditWithThisEntity == null) {
            audit = Audit.builder()
                    .entityType(((ObservableEntity) objects[0]).getClass().getSimpleName())
                    .operationType(auditAction)
                    .createdBy("Username")
                    .modifiedBy(null)
                    .createdAt(creationOrModificationTime)
                    .modifiedAt(null)
                    .newEntityJson(null)
                    .entityJson(entityJson)
                    .build();
        } else {
            audit = Audit.builder()
                    .entityType(lastAuditWithThisEntity.getEntityType())
                    .operationType(auditAction)
                    .createdBy(lastAuditWithThisEntity.getCreatedBy())
                    .modifiedBy("newUsername")
                    .createdAt(lastAuditWithThisEntity.getCreatedAt())
                    .modifiedAt(creationOrModificationTime)
                    .newEntityJson(entityJson)
                    .entityJson(entityJson)
                    .build();
        }

        auditRepository.save(audit);
        return audit;
    }

}
