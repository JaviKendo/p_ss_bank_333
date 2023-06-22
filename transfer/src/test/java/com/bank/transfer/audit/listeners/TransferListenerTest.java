package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.service.audit.AuditService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TransferListenerTest {
    private TransferListener transferListener;
    @MockBean
    private AuditService auditService;
    @MockBean
    private BeanUtil beanUtil;

    @BeforeEach
    void setTransferListener() {
        this.transferListener = new TransferListener();
    }
    @Test
    void setBeanUtil() {
        transferListener.setBeanUtil(beanUtil);
        Assertions.assertEquals(transferListener.getBeanUtil(), beanUtil);
    }

    @Test
    void getBeanUtil() {
        Assertions.assertNull(transferListener.getBeanUtil());
        transferListener.setBeanUtil(beanUtil);
        Assertions.assertEquals(transferListener.getBeanUtil(), beanUtil);
    }

    @Test
    void saveAudit() {
        Date date = new Date();
        AuditDto auditDto = AuditDto.builder()
                .id(1L)
                .entityType("1")
                .operationType("1")
                .createdBy("1")
                .modifiedBy("1")
                .createdAt(date)
                .modifiedAt(date)
                .newEntityJson("1")
                .entityJson("1")
                .build();
        transferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        transferListener.saveAudit(auditDto);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);

    }

    @Test
    void getAuditByJson() {
        Date date = new Date();
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
        transferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);
        AuditDto auditDto = transferListener.getAuditByJson("2");
        Assertions.assertNotNull(auditDto);
        Assertions.assertEquals(auditDto, auditDtos.get(1));
        AuditDto auditDto1 = transferListener.getAuditByJson("785");
        Assertions.assertNull(auditDto1);
    }
}