package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDTO;
import com.bank.authorization.entity.Audit;
import com.bank.authorization.entity.User;
import com.bank.authorization.repository.AuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@Disabled
@ExtendWith(MockitoExtension.class)
public class AuditServiceImplTest {
    @InjectMocks
    private AuditServiceImpl auditService;
    @Mock
    private AuditRepository auditRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private EntityManager entityManager;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Audit audit1;
    private Audit audit2;
    private AuditDTO auditDTO1;
    private AuditDTO auditDTO2;
    private User user;
    @BeforeEach
    public void setUp() {
        audit1 = new Audit(1L, "com.bank.authorization.entity.User", "CREATE", "{\"id\": 1, \"role\": \"USER\", \"profileId\": 101, \"password\": \"password1\"}", "system", Timestamp.from(Instant.now()));
        audit2 = new Audit(2L, "com.bank.authorization.entity.User", "UPDATE", "{\"id\": 1, \"role\": \"ADMIN\", \"profileId\": 101, \"password\": \"password1\"}", "system", Timestamp.from(Instant.now()));
        auditDTO1 = modelMapper.map(audit1, AuditDTO.class);
        auditDTO2 = modelMapper.map(audit2, AuditDTO.class);
        user = new User(1L, "USER", 101L, "password1");
    }
    @Test
    public void findById_success() {
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit1));
        when(modelMapper.map(audit1, AuditDTO.class)).thenReturn(auditDTO1);
        AuditDTO result = auditService.findById(1L);
        assertEquals(auditDTO1, result);
    }
    @Test
    public void findById_notFound() {
        when(auditRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> auditService.findById(1L));
    }
    @Test
    public void findAll() {
        when(auditRepository.findAll()).thenReturn(Arrays.asList(audit1, audit2));
        when(modelMapper.map(audit1, AuditDTO.class)).thenReturn(auditDTO1);
        when(modelMapper.map(audit2, AuditDTO.class)).thenReturn(auditDTO2);
        List<AuditDTO> result = auditService.findAll();
        assertEquals(2, result.size());
        assertEquals(auditDTO1, result.get(0));
        assertEquals(auditDTO2, result.get(1));
    }
    @Test
    public void createAudit() throws Exception {
        // Test createAudit()
        auditService.createAudit(user, "CREATE");
        // Verify interactions with the mocked dependencies
        verify(entityManager, times(1)).persist(any(Audit.class));
    }
}