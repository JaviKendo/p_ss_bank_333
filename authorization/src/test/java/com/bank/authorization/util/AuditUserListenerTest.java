package com.bank.authorization.util;

import com.bank.authorization.entity.User;
import com.bank.authorization.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditUserListenerTest {

    @Mock
    private AuditService auditService;
    @InjectMocks
    private AuditUserListener auditUserListener;
    private User user;
    @BeforeEach
    public void setUp() {
        user = new User(1L, "ROLE_ADMIN", 101L, "password");
        AuditUserListener.setAuditService(auditService);
    }

    @Test
    public void prePersistTest() {
        auditUserListener.prePersist(user);
        verify(auditService).createAudit(user, "INSERT");
    }
    @Test
    public void preUpdateTest() {
        auditUserListener.preUpdate(user);
        verify(auditService).createAudit(user, "UPDATE");
    }
}
