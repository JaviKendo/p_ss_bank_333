package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.AuditServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuditController.class)
class AuditControllerTest {
    @Autowired
    private AuditController auditController;

    @MockBean
    private AuditServiceImpl auditService;

    private AuditDTO auditDTO1;
    private AuditDTO auditDTO2;

    @Test
    void testGetAllAudits() {
        List<AuditDTO> auditDTO = List.of(createAudits().get(0), createAudits().get(1));
        when(auditService.getAllAudits()).thenReturn(auditDTO.stream().map(PublicInfoMapper.INSTANCE::convertToAudit).collect(Collectors.toList()));
        List<AuditDTO> auditDTOToTest = auditController.getAllAudits();
        assertNotNull(auditDTO);
        assertNotNull(auditDTOToTest);
        assertEquals(auditDTO.size(), auditDTOToTest.size());
    }

    @Test
    void testGetAuditById() {
        AuditDTO auditDTO = createAudits().get(0);
        when(auditService.getAuditById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToAudit(auditDTO));
        AuditDTO auditDTOToTest = auditController.getAuditById(1L);
        assertNotNull(auditDTO);
        assertNotNull(auditDTOToTest);
        assertEquals(auditDTO.getEntityType(), auditDTOToTest.getEntityType());
        assertEquals(auditDTO.getOperationType(), auditDTOToTest.getOperationType());
        assertEquals(auditDTO.getCreatedBy(), auditDTOToTest.getCreatedBy());
        assertEquals(auditDTO.getModifiedBy(), auditDTOToTest.getModifiedBy());
        assertEquals(auditDTO.getCreatedAt(), auditDTOToTest.getCreatedAt());
        assertEquals(auditDTO.getModifiedAt(), auditDTOToTest.getModifiedAt());
        assertEquals(auditDTO.getNewEntityJson(), auditDTOToTest.getNewEntityJson());
        assertEquals(auditDTO.getEntityJson(), auditDTOToTest.getEntityJson());
    }

    @Test
    void testCreateAudit() {
        Audit audit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        auditController.createAudit(PublicInfoMapper.INSTANCE.convertToAuditDTO(audit));
        verify(auditService, times(1)).createAudit(any());
    }

    List<AuditDTO> createAudits() {
        auditDTO1 = new AuditDTO("entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        auditDTO2 = new AuditDTO("entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        return List.of(auditDTO1, auditDTO2);
    }
}