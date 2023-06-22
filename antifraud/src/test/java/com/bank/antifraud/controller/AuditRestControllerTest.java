package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDTO;
import com.bank.antifraud.service.AuditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuditRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuditRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditService;

    private final LocalDateTime creationLocalDateTime = LocalDateTime.now();

    private final LocalDateTime updationLocalDateTime = LocalDateTime.now().plusMinutes(2);

    private final Timestamp creationTimestamp = Timestamp.valueOf(creationLocalDateTime);

    private final Timestamp updationTimestamp = Timestamp.valueOf(updationLocalDateTime);

    private final AuditDTO firstAuditDTO =
            AuditDTO.builder()
                    .id(1L)
                    .entityType("ExampleEntityType")
                    .operationType("ADD")
                    .createdBy("Username")
                    .modifiedBy(null)
                    .createdAt(creationTimestamp)
                    .modifiedAt(null)
                    .newEntityJson(null)
                    .entityJson("{\"id\":1,\"exampleKey\":\"exampleValue\"}")
                    .build();

    private final AuditDTO secondAuditDTO =
            AuditDTO.builder()
                    .id(2L)
                    .entityType("ExampleEntityType2")
                    .operationType("UPDATE")
                    .createdBy("Username")
                    .modifiedBy("newUsername")
                    .createdAt(creationTimestamp)
                    .modifiedAt(updationTimestamp)
                    .newEntityJson("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")
                    .entityJson("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")
                    .build();

    @Test
    void getAllAudits() throws Exception {
        when(auditService.getAllAudits()).thenReturn(Arrays.asList(firstAuditDTO, secondAuditDTO));

        mockMvc.perform(get("/audits")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].entityType",
                        containsInAnyOrder("ExampleEntityType", "ExampleEntityType2")))
                .andExpect(jsonPath("$[*].operationType", containsInAnyOrder("ADD", "UPDATE")))
                .andExpect(jsonPath("$[*].createdBy", containsInAnyOrder("Username", "Username")))
                .andExpect(jsonPath("$[*].modifiedBy", containsInAnyOrder(null, "newUsername")))
//                .andExpect(jsonPath("$[*].createdAt",
//                        containsInAnyOrder(creationTimestamp, updationTimestamp)))
//                .andExpect(jsonPath("$[*].modifiedAt",
//                        containsInAnyOrder(null, updationTimestamp)))
                .andExpect(jsonPath("$[*].newEntityJson",
                        containsInAnyOrder(null, "{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")))
                .andExpect(jsonPath("$[*].entityJson",
                        containsInAnyOrder("{\"id\":1,\"exampleKey\":\"exampleValue\"}",
                                "{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")));
    }

    @Test
    void getAuditById() throws Exception {
        when(auditService.getAuditById(anyLong())).thenReturn(secondAuditDTO);

        mockMvc.perform(get("/audits/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.entityType").isString())
                .andExpect(jsonPath("$.operationType").isString())
                .andExpect(jsonPath("$.createdBy").isString())
                .andExpect(jsonPath("$.modifiedBy").isString())
                .andExpect(jsonPath("$.entityJson").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.entityType", equalTo("ExampleEntityType2")))
                .andExpect(jsonPath("$.operationType", equalTo("UPDATE")))
                .andExpect(jsonPath("$.createdBy", equalTo("Username")))
                .andExpect(jsonPath("$.modifiedBy", equalTo("newUsername")))
//                .andExpect(jsonPath("$.createdAt", equalTo(creationTimestamp)))
//                .andExpect(jsonPath("$.modifiedAt", equalTo(updationTimestamp)))
                .andExpect(jsonPath("$.newEntityJson",
                        equalTo("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")))
                .andExpect(jsonPath("$.entityJson",
                        equalTo("{\"id\":2,\"exampleKey2\":\"exampleValue2\"}")));
    }

}