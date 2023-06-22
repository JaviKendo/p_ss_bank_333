package com.bank.antifraud.service;

import com.bank.antifraud.custom_audit.AuditorImpl;
import com.bank.antifraud.dto.AuditDTO;
import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.mapper.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    private AuditMapper auditMapper;

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private AuditorImpl auditor;

    @InjectMocks
    private AuditServiceImpl auditService;

    private final Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());

    @Test
    void testGetAllAudits() {
        List<Audit> audits = this.getAllAudits();
        List<AuditDTO> expectedResult = this.getAllAuditsDTO();

        when(auditRepository.findAll()).thenReturn(audits);
        doReturn(expectedResult).when(auditMapper).toDTOList(audits);

        List<AuditDTO> actualResult = auditService.getAllAudits();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(auditRepository, times(1)).findAll();
    }

    @Test
    void testGetEmptyListSuspiciousAuditsTransfers() {
        List<Audit> emptyList = Collections.emptyList();
        List<AuditDTO> expectedResult = Collections.emptyList();

        when(auditRepository.findAll()).thenReturn(emptyList);
        doReturn(expectedResult).when(auditMapper).toDTOList(emptyList);

        List<AuditDTO> actualResult = auditService.getAllAudits();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(0);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(auditRepository, times(1)).findAll();
    }

    @Test
    void testGetAuditById() {
        Audit audit = this.getFirstAudit();
        AuditDTO expectedAuditDTO = this.getFirstAuditDTO();

        when(auditRepository.findById(1L)).thenReturn(Optional.ofNullable(audit));
        doReturn(expectedAuditDTO).when(auditMapper).toDTO(audit);

        AuditDTO actualAuditDTO = auditService.getAuditById(1L);

        assertThat(actualAuditDTO).isNotNull();
        assertThat(actualAuditDTO).isEqualTo(expectedAuditDTO);
        verify(auditRepository, times(1)).findById(1L);
    }

    @Test
    void testAddAudit() {
        Object[] objects = new Object[]{};
        Object object = new Object();

        auditService.addAudit(objects, creationTime, object);

        verify(auditor, times(1)).doAudit(objects, creationTime, object);
    }

    private List<Audit> getAllAudits() {
        return List.of(this.getFirstAudit(), this.getSecondAudit());
    }

    private Audit getFirstAudit() {
        return Audit.builder()
                .id(1L)
                .entityType("ExampleEntityType")
                .operationType("ADD")
                .createdBy("Username")
                .modifiedBy(null)
                .createdAt(creationTime)
                .modifiedAt(null)
                .newEntityJson(null)
                .entityJson("\"keyExample\":\"valueExample\"")
                .build();
    }

    private Audit getSecondAudit() {
        Timestamp modificationTime = Timestamp.valueOf(LocalDateTime.now());
        return Audit.builder()
                .id(2L)
                .entityType("ExampleEntityType2")
                .operationType("UPDATE")
                .createdBy("Username2")
                .modifiedBy("newUsername2")
                .createdAt(creationTime)
                .modifiedAt(modificationTime)
                .newEntityJson("\"newKeyExample\":\"newValueExample\"")
                .entityJson("\"newKeyExample\":\"newValueExample\"")
                .build();
    }

    private List<AuditDTO> getAllAuditsDTO() {
        return Mappers.getMapper(AuditMapper.class).toDTOList(this.getAllAudits());
    }

    private AuditDTO getFirstAuditDTO() {
        return Mappers.getMapper(AuditMapper.class).toDTO(this.getFirstAudit());
    }

}