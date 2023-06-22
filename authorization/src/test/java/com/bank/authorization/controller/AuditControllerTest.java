package com.bank.authorization.controller;

import com.bank.authorization.entity.Audit;
import com.bank.authorization.repository.AuditRepository;
import com.bank.authorization.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditService;

    @MockBean
    private AuditRepository auditRepository;

    private List<Audit> auditList;

    @BeforeEach
    public void setUp() {
        Audit audit1 = new Audit(1L, "User", "CREATE", "John", null, new Timestamp(System.currentTimeMillis()).toLocalDateTime(), null, "{}", "{}");
        Audit audit2 = new Audit(2L, "User", "UPDATE", "John", "Jane", new Timestamp(System.currentTimeMillis()).toLocalDateTime(), new Timestamp(System.currentTimeMillis()).toLocalDateTime(), "{}", "{}");
        auditList = Arrays.asList(audit1, audit2);
    }

    @Test
    public void testGetAllAudits() throws Exception {
        when(auditRepository.findAll()).thenReturn(auditList);

        mockMvc.perform(get("/audits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].entityType").value("User"))
                .andExpect(jsonPath("$[0].operationType").value("CREATE"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].entityType").value("User"))
                .andExpect(jsonPath("$[1].operationType").value("UPDATE"));
    }
}
