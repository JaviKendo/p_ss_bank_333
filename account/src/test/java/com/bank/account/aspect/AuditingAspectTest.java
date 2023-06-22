package com.bank.account.aspect;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.entity.Audit;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.AuditRepository;
import com.bank.account.util.AuditingActionType;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuditingAspectTest {
    @Mock
    private AuditRepository auditRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Auditable auditable;
    @Mock
    private JoinPoint joinPoint;

    @InjectMocks
    private AuditingAspect auditAspect;
    Audit audit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too",
            Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");

    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
    AccountDetails accountDetails = new AccountDetails(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    public void shouldCreateOptionAudit() {
        Mockito.when(auditable.actionType()).thenReturn(AuditingActionType.CREATE);
        Mockito.when(joinPoint.getArgs()).thenReturn(new Object[]{detailsDTO});
        auditAspect.logAuditActivity(joinPoint, auditable);
        Mockito.verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldUpdateOptionAudit() {
        Mockito.when(auditable.actionType()).thenReturn(AuditingActionType.UPDATE);
        Mockito.when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        Mockito.when(joinPoint.getArgs()).thenReturn(new Object[]{detailsDTO});
        Mockito.when(accountRepository.findAccountDetailsByAccountNumber(1L)).thenReturn(Optional.of(accountDetails));
        auditAspect.logAuditActivity(joinPoint, auditable);
        Mockito.verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldDeleteOptionAudit() {
        Mockito.when(auditable.actionType()).thenReturn(AuditingActionType.DELETE);
        Mockito.when(joinPoint.getArgs()).thenReturn(new Object[]{1L});
        Mockito.when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        auditAspect.logAuditActivity(joinPoint, auditable);
        Mockito.verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldHandleUnknownActionType() {
        Mockito.when(auditable.actionType()).thenReturn(null);
        auditAspect.logAuditActivity(joinPoint, auditable);
        Mockito.verify(auditRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(accountRepository, Mockito.times(0)).findAccountDetailsByAccountNumber(Mockito.anyLong());
        Mockito.verify(auditRepository, Mockito.times(0)).findById(Mockito.anyLong());
    }
}

