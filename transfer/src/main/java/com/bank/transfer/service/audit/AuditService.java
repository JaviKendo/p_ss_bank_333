package com.bank.transfer.service.audit;

import com.bank.transfer.dto.AuditDto;

import java.util.List;

public interface AuditService {
    void saveAudit(AuditDto transferDto);
    List<AuditDto> getAllAudit();

}
