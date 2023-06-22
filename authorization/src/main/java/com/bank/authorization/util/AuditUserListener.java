package com.bank.authorization.util;

import com.bank.authorization.entity.User;
import com.bank.authorization.service.AuditService;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class AuditUserListener {
    private static AuditService auditService;
    public static void setAuditService(AuditService auditService) {
        AuditUserListener.auditService = auditService;
    }

    @PostPersist
    public void prePersist(User user) {
        auditService.createAudit(user, "INSERT");
    }
    @PostUpdate
    public void preUpdate(User user) {
        auditService.createAudit(user, "UPDATE");
    }
}
