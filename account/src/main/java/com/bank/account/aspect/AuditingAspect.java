package com.bank.account.aspect;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.entity.Audit;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.AuditRepository;
import com.bank.account.util.AuditingActionType;
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
/**
 * Аспект для аудита активности.
 * Следит за выполнением методов, помеченных аннотацией @Auditable,
 * и сохраняет соответствующую аудит-информацию.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuditingAspect {
    private final AuditRepository auditRepository;
    private final AccountRepository accountRepository;
    /**
     * Выполняет логирование аудит-активности после выполнения метода, помеченного аннотацией @Auditable.
     *
     * @param joinPoint  точка соединения, на которой был вызван метод
     * @param auditable  аннотация @Auditable, описывающая тип действия
     */

    @After(value = "@annotation(auditable)", argNames = "joinPoint,auditable")
    @Transactional
    public void logAuditActivity(JoinPoint joinPoint, Auditable auditable) {

        if (AuditingActionType.CREATE == auditable.actionType()) {
            Audit audit = new Audit();
            audit.setEntityType(AccountDetails.class.getSimpleName());
            AccountDetails details = AccountDetailsMapper.INSTANCE.toEntity((AccountDetailsDTO) joinPoint.getArgs()[0]);
            addAudit(audit, details, auditable);
        }
        if (AuditingActionType.UPDATE == auditable.actionType()) {
            AccountDetails details = AccountDetailsMapper.INSTANCE.toEntity((AccountDetailsDTO) joinPoint.getArgs()[0]);
            updateAudit(details);
        }
        if (AuditingActionType.DELETE == auditable.actionType()) {
            Long id = (Long) joinPoint.getArgs()[0];
            deleteAudit(id);
        }
    }
    /**
     * Добавляет аудит-запись при создании сущности.
     *
     * @param audit      объект аудита
     * @param details    детали счета
     * @param auditable  аннотация @Auditable
     */
    private void addAudit(Audit audit, AccountDetails details, Auditable auditable) {
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
        /**
         * Обновляет аудит-запись при обновлении деталей счета.
         *
         * @param details  детали счета
         */
    }private void updateAudit(AccountDetails details) {
        AccountDetails updateDetails = accountRepository
                .findAccountDetailsByAccountNumber(details.getAccountNumber())
                .orElseThrow(AccountDetailsNotFoundException::new);
        Audit updateAudit = auditRepository
                .findById(updateDetails.getId())
                .orElseThrow(EntityNotFoundException::new);
        updateAudit.setOperationType(AuditingActionType.UPDATE.name());
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
    /**
     * Удаляет аудит-запись при удалении сущности.
     *
     * @param id  идентификатор аудит-записи
     */
    private void deleteAudit(Long id) {
        Audit deleteAudit = auditRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        deleteAudit.setOperationType(AuditingActionType.DELETE.name());
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
