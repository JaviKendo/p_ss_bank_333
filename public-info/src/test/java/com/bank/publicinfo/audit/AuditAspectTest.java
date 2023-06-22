package com.bank.publicinfo.audit;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.AuditAspect;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.AuditRepository;
import com.bank.publicinfo.repository.BankDetailsRepository;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.util.Optional.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuditAspectTest {
    @Mock
    private AuditRepository auditRepository;
    @Mock
    private BankDetailsRepository accountRepository;
    @Mock
    private Auditable auditable;
    @Mock
    private JoinPoint joinPoint;

    @InjectMocks
    private AuditAspect auditAspect;
    Audit audit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too",
            Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");

    BankDetails bankDetails = new BankDetails(1L, 123L, 123L, 123L, 123,
            "city", "jointStockCompany", "name");

    @Test
    public void shouldCreateOptionAudit() {
        when(auditable.actionType()).thenReturn(AuditActionType.CREATE);
        when(joinPoint.getArgs()).thenReturn(new Object[]{bankDetails});
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, times(1)).save(any());
    }

    @Test
    public void shouldUpdateOptionAudit() {
        when(auditable.actionType()).thenReturn(AuditActionType.UPDATE);
        when(auditRepository.findById(1L)).thenReturn(of(audit));
        when(joinPoint.getArgs()).thenReturn(new Object[]{bankDetails});
        when(accountRepository.findById(1L)).thenReturn(of(bankDetails));
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, times(1)).save(any());
    }

    @Test
    public void shouldDeleteOptionAudit() {
        when(auditable.actionType()).thenReturn(AuditActionType.DELETE);
        when(joinPoint.getArgs()).thenReturn(new Object[]{1L});
        when(auditRepository.findById(1L)).thenReturn(of(audit));
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, times(1)).save(any());
    }

    @Test
    public void shouldHandleUnknownActionType() {
        when(auditable.actionType()).thenReturn(null);
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, times(0)).save(any());
        verify(accountRepository, times(0)).findById(anyLong());
        verify(auditRepository, times(0)).findById(anyLong());
    }
}