package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.service.audit.AuditService;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
public class TransferListener {

    protected BeanUtil beanUtil;

    public void setBeanUtil(BeanUtil beanUtil) {
        this.beanUtil = beanUtil;
    }
    @Transactional
    public void saveAudit(AuditDto auditDto) {
        if (beanUtil == null) {
            setBeanUtil(new BeanUtil());
        }
        final AuditService auditService = beanUtil.getBeans(AuditService.class);
        auditService.saveAudit(auditDto);
    }
    @Transactional
    public AuditDto getAuditByJson(String json) {
        if (beanUtil == null) {
            setBeanUtil(new BeanUtil());
        }
        final AuditService auditService = beanUtil.getBeans(AuditService.class);
        final List<AuditDto> auditDtoList = auditService.getAllAudit();
        for (int i = auditDtoList.size() - 1; i >= 0; i--) {
            if (auditDtoList.get(i).getNewEntityJson() == null) {
                if (auditDtoList.get(i).getEntityJson().equals(json)) {
                    return auditDtoList.get(i);
                }
            } else {
                if (auditDtoList.get(i).getNewEntityJson().equals(json)) {
                    return auditDtoList.get(i);
                }
            }
        }
        return null;
    }
}
