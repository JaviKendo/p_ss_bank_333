package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.AccountTransfer;
import lombok.Getter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
@Getter
public class AccountTransferListener extends TransferListener {

    @PostPersist
    @Transactional
    public void postCreate(AccountTransfer accountTransfer) {
        saveAudit(AuditDto.builder()
                .operationType("Создан")
                .entityType(accountTransfer.getClass().getSimpleName())
                .entityJson(accountTransfer.toString())
                .build());
    }
    @PostUpdate
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postUpdate(AccountTransfer accountTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldAccountTransfer().toString());
        auditDto.setOperationType("Обновлен");
        auditDto.setNewEntityJson(accountTransfer.toString());
        saveAudit(auditDto);
    }

    @PostRemove
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postRemove(AccountTransfer accountTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldAccountTransfer().toString());
        auditDto.setOperationType("Удален");
        auditDto.setNewEntityJson(null);
        saveAudit(auditDto);
    }

    @Transactional
    public AccountTransfer getOldAccountTransfer() {
        if (beanUtil == null) {
            setBeanUtil(new BeanUtil());
        }
        final AuditTransferStorage auditTransferStorage = beanUtil.getBeans(AuditTransferStorage.class);
        return auditTransferStorage.getAccountTransfer();
    }
}
