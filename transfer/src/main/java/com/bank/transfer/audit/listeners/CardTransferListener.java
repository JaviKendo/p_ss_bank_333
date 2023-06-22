package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.CardTransfer;
import lombok.Getter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Getter
public class CardTransferListener extends TransferListener {

    @PostPersist
    @Transactional
    public void postCreate(CardTransfer cardTransfer) {
        saveAudit(AuditDto.builder()
                .operationType("Создан")
                .entityType(cardTransfer.getClass().getSimpleName())
                .entityJson(cardTransfer.toString())
                .build());
    }

    @PostUpdate
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postUpdate(CardTransfer cardTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldCardTransfer().toString());
        auditDto.setOperationType("Обновлен");
        auditDto.setNewEntityJson(cardTransfer.toString());
        saveAudit(auditDto);
    }

    @PostRemove
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postRemove(CardTransfer cardTransfer) {
        final AuditDto auditDto = getAuditByJson(getOldCardTransfer().toString());
        auditDto.setOperationType("Удален");
        auditDto.setNewEntityJson(null);
        saveAudit(auditDto);
    }

    @Transactional
    public CardTransfer getOldCardTransfer() {
        if (beanUtil == null) {
            setBeanUtil(new BeanUtil());
        }
        final AuditTransferStorage auditService = beanUtil.getBeans(AuditTransferStorage.class);
        return auditService.getCardTransfer();
    }
}
