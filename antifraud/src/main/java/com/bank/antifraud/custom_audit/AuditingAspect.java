package com.bank.antifraud.custom_audit;

import com.bank.antifraud.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditingAspect {

    private final AuditService auditService;

    @AfterReturning(value = "@annotation(auditable)", returning = "returnedObject")
    public void logAuditActivity(JoinPoint joinPoint, Auditable auditable, ObservableDTO returnedObject) {
        auditService.addAudit(new Object[]{joinPoint, auditable}, LocalDateTime.now(), returnedObject);
    }

}
