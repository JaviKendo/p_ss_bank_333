package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import lombok.Getter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Getter
public class PhoneTransferListener extends TransferListener {
    @PostPersist
    @Transactional
    public void postCreate(PhoneTransfer phoneTransfer) {
        saveAudit(AuditDto.builder()
                .operationType("Создан")
                .entityType(phoneTransfer.getClass().getSimpleName())
                .entityJson(phoneTransfer.toString())
                .build());
    }

    @PostUpdate
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postUpdate(PhoneTransfer phoneTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldPhoneTransfer().toString());
        auditDto.setOperationType("Обновлен");
        auditDto.setNewEntityJson(phoneTransfer.toString());
        saveAudit(auditDto);
    }

    @PostRemove
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postRemove(PhoneTransfer phoneTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldPhoneTransfer().toString());
        auditDto.setOperationType("Удален");
        auditDto.setNewEntityJson(null);
        saveAudit(auditDto);
    }


    @Transactional
    public PhoneTransfer getOldPhoneTransfer() {
        if (beanUtil == null) {
            setBeanUtil(new BeanUtil());
        }
        final AuditTransferStorage auditService = beanUtil.getBeans(AuditTransferStorage.class);
        return auditService.getPhoneTransfer();
    }
}
