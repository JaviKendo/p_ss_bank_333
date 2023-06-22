package com.bank.transfer.service.audit;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

@SpringBootTest
class AuditServiceImlTest {

    @Autowired
    private AuditService auditService;

    @MockBean
    private AuditRepository auditRepository;
    @Test
    void saveAudit() {
        Date date = new Date();
        auditService.saveAudit(
                AuditDto.builder()
                        .id(1L)
                        .entityType("1")
                        .operationType("1")
                        .createdBy("1")
                        .modifiedBy("1")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("1")
                        .entityJson("1")
                        .build());
        Mockito.verify(auditRepository, Mockito.times(1)).save(
                Audit.builder()
                        .id(1L)
                        .entityType("1")
                        .operationType("1")
                        .createdBy("1")
                        .modifiedBy("1")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("1")
                        .entityJson("1")
                        .build());
    }

    @Test
    void getAllAudit() {
        Date date = new Date();
        List<Audit> audits = List.of(
                Audit.builder()
                        .id(1L)
                        .entityType("1")
                        .operationType("1")
                        .createdBy("1")
                        .modifiedBy("1")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("1")
                        .entityJson("1")
                        .build(),
                Audit.builder()
                        .id(2L)
                        .entityType("2")
                        .operationType("2")
                        .createdBy("2")
                        .modifiedBy("2")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("2")
                        .entityJson("2")
                        .build());
        List<AuditDto> auditDtos = List.of(
                AuditDto.builder()
                        .id(1L)
                        .entityType("1")
                        .operationType("1")
                        .createdBy("1")
                        .modifiedBy("1")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("1")
                        .entityJson("1")
                        .build(),
                AuditDto.builder()
                        .id(2L)
                        .entityType("2")
                        .operationType("2")
                        .createdBy("2")
                        .modifiedBy("2")
                        .createdAt(date)
                        .modifiedAt(date)
                        .newEntityJson("2")
                        .entityJson("2")
                        .build());
        Mockito.when(auditRepository.findAll()).thenReturn(audits);
        List<AuditDto> accountTransfersDtoToTest = auditService.getAllAudit();
        Assertions.assertNotNull(accountTransfersDtoToTest);
        Assertions.assertEquals(auditDtos, accountTransfersDtoToTest);
    }
}