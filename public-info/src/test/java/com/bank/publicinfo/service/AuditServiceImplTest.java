package com.bank.publicinfo.service;


import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuditServiceImplTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditServiceImpl auditService;

    private Audit audit1;
    private Audit audit2;

    @Test
    void shouldReturnAllAudit() {
        when(auditRepository.findAll()).thenReturn(createAudits());
        List<Audit> result = auditService.getAllAudits();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(audit1, result.get(0));
        assertEquals(audit2, result.get(1));
    }

    @Test
    void shouldReturnAuditById() {
        when(auditRepository.findById(1L)).thenReturn(of(createAudits().get(0)));
        Audit result = auditService.getAuditById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(auditRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> auditService.getAuditById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        auditService.createAudit(any());
        verify(auditRepository, times(1)).save(any());
    }

    List<Audit> createAudits() {
        audit1 = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        audit2 = new Audit(2L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");
        return List.of(audit1, audit2);
    }
}