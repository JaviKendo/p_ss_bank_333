package com.bank.account.controller;

import com.bank.account.entity.Audit;
import com.bank.account.exception.AuditNotFoundException;
import com.bank.account.service.AuditService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService service;

    @Autowired
    private AuditController controller;

    @Test
    public void test() {
        org.hamcrest.MatcherAssert.assertThat(controller, Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void shouldReturnAllAudits() throws Exception {
        Mockito.when(service.getAllAudits()).thenReturn(getAudits());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void shouldReturnExpectedNumberOfAudits() throws Exception {
        List<Audit> audits1 = getAudits();
        List<Audit> audits2 = Collections.emptyList();
        List<Audit> audits3 = Collections.singletonList(new Audit());

        Mockito.when(service.getAllAudits()).thenReturn(audits1, audits2, audits3);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void shouldReturnAuditById() throws Exception {
        Mockito.when(service.getAudit(1L)).thenReturn(getAudits().get(0));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    public void shouldReturnExceptionOnFindAuditById() throws Exception {
        Mockito.when(service.getAudit(2L)).thenThrow(new AuditNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    List<Audit> getAudits() {
        Audit firstAudit = new Audit(1L, "entity_type", "operation_type", "by_me", null, Timestamp.valueOf(LocalDateTime.now()),
                null, null, "entity_json_1");
        Audit secondAudit = new Audit(2L, "entity_type_2", "operation_type_2", "by_you", null, Timestamp.valueOf(LocalDateTime.now()),
                null, null, "entity_json_2");
        List<Audit> expectedAudits = Arrays.asList(firstAudit, secondAudit);

        return List.of(firstAudit, secondAudit);
    }

}
