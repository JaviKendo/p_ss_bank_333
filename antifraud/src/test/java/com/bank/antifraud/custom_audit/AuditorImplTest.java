package com.bank.antifraud.custom_audit;

import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.util.SuspiciousTransferTypeChecker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AuditorImplTest {

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SuspiciousTransferTypeChecker suspiciousTransferTypeChecker;

    @InjectMocks
    private AuditorImpl auditor;

    private ObservableEntity observableEntity;

    private final LocalDateTime creationLocalDateTime = LocalDateTime.now();

    private final LocalDateTime updationLocalDateTime = creationLocalDateTime.plusMinutes(2);

    private Audit firstAudit;

    private Audit secondAudit;

    @BeforeEach
    void prepare() {
        observableEntity = new ExampleEntityType();

        firstAudit = Audit.builder()
                .entityType("ExampleEntityType")
                .operationType("ADD")
                .createdBy("Username")
                .modifiedBy(null)
                .createdAt(Timestamp.valueOf(creationLocalDateTime))
                .modifiedAt(null)
                .newEntityJson(null)
                .entityJson("{\"id\":1,\"exampleKey\":\"exampleValue\"}")
                .build();

        secondAudit = Audit.builder()
                .entityType("ExampleEntityType2")
                .operationType("UPDATE")
                .createdBy("Username")
                .modifiedBy("newUsername")
                .createdAt(Timestamp.valueOf(creationLocalDateTime))
                .modifiedAt(Timestamp.valueOf(updationLocalDateTime))
                .newEntityJson("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")
                .entityJson("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")
                .build();
    }

    @Test
    void testGetCompletedCreationAudit() {
        ObservableEntity testObservableEntity = observableEntity;
        Audit lastAuditWithThisEntity = secondAudit;
        String auditAction = secondAudit.getOperationType();
        String entityJson = "{\"id\":2,\"exampleKey2\":\"exampleValue2\"}";

        Audit actualResult = auditor.getCompletedAudit(new Object[]{testObservableEntity, lastAuditWithThisEntity,
                auditAction, entityJson}, updationLocalDateTime);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getId()).isEqualTo(null);
        assertThat(actualResult.getEntityType()).isEqualTo(secondAudit.getEntityType());
        assertThat(actualResult.getOperationType()).isEqualTo(secondAudit.getOperationType());
        assertThat(actualResult.getCreatedBy()).isEqualTo(secondAudit.getCreatedBy());
        assertThat(actualResult.getModifiedBy()).isEqualTo(secondAudit.getModifiedBy());
        assertThat(actualResult.getCreatedAt()).isEqualTo(secondAudit.getCreatedAt());
        assertThat(actualResult.getModifiedAt()).isEqualTo(secondAudit.getModifiedAt());
        assertThat(actualResult.getNewEntityJson()).isEqualTo(secondAudit.getNewEntityJson());
        assertThat(actualResult.getEntityJson()).isEqualTo(secondAudit.getEntityJson());
    }

    @Test
    void testGetCompletedUpdationAudit() {
        ObservableEntity testObservableEntity = observableEntity;
        String auditAction = firstAudit.getOperationType();
        String entityJson = "{\"id\":1,\"exampleKey\":\"exampleValue\"}";

        Audit actualResult = auditor.getCompletedAudit(new Object[]{testObservableEntity, null,
                auditAction, entityJson}, creationLocalDateTime);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getId()).isEqualTo(null);
        assertThat(actualResult.getEntityType()).isEqualTo(firstAudit.getEntityType());
        assertThat(actualResult.getOperationType()).isEqualTo(firstAudit.getOperationType());
        assertThat(actualResult.getCreatedBy()).isEqualTo(firstAudit.getCreatedBy());
        assertThat(actualResult.getModifiedBy()).isEqualTo(null);
        assertThat(actualResult.getCreatedAt()).isEqualTo(firstAudit.getCreatedAt());
        assertThat(actualResult.getModifiedAt()).isEqualTo(firstAudit.getModifiedAt());
        assertThat(actualResult.getNewEntityJson()).isEqualTo(null);
        assertThat(actualResult.getEntityJson()).isEqualTo(firstAudit.getEntityJson());
    }

}

class ExampleEntityType implements ObservableEntity {}