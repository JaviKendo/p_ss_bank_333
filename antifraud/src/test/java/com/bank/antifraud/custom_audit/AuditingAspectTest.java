package com.bank.antifraud.custom_audit;

import com.bank.antifraud.service.AuditServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuditingAspectTest {

    @Mock
    private AuditServiceImpl auditService;

    @InjectMocks
    private AuditingAspect auditingAspect;

    private JoinPoint joinPoint;

    private Auditable auditable;

    private ObservableDTO observableDTO;

    @BeforeEach
    void prepare() {
        joinPoint = new JoinPoint() {
            @Override
            public String toShortString() {
                return null;
            }

            @Override
            public String toLongString() {
                return null;
            }

            @Override
            public Object getThis() {
                return null;
            }

            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Object[] getArgs() {
                return new Object[0];
            }

            @Override
            public Signature getSignature() {
                return null;
            }

            @Override
            public SourceLocation getSourceLocation() {
                return null;
            }

            @Override
            public String getKind() {
                return null;
            }

            @Override
            public StaticPart getStaticPart() {
                return null;
            }
        };

        auditable = new Auditable() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public AuditingActionType actionType() {
                return null;
            }
        };

        observableDTO = new ObservableDTO() {};
    }

    @Test
    void testLogAuditActivity() {
        LocalDateTime localDateTime = LocalDateTime.now();

        auditingAspect.logAuditActivity(joinPoint, auditable, observableDTO);

        verify(auditService, times(1))
                .addAudit(new Object[]{joinPoint, auditable}, localDateTime, observableDTO);
    }

}