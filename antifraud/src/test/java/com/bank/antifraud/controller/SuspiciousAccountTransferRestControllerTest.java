package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SuspiciousAccountTransferRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class SuspiciousAccountTransferRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuspiciousAccountTransferService suspiciousAccountTransferService;

    private final SuspiciousAccountTransferDTO firstSuspiciousAccountTransferDTO =
            SuspiciousAccountTransferDTO.builder()
                    .id(1L)
                    .accountTransferId(100L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason(null)
                    .suspiciousReason("Почему перевод по счёту попал в antifraud")
                    .build();

    private final SuspiciousAccountTransferDTO secondSuspiciousAccountTransferDTO =
            SuspiciousAccountTransferDTO.builder()
                    .id(2L)
                    .accountTransferId(200L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason("Подозрительный перевод")
                    .suspiciousReason("Причина, почему перевод по счёту попал в antifraud")
                    .build();

    @Test
    void getAllSuspiciousAccountTransfersAPI() throws Exception {
        when(suspiciousAccountTransferService.getAllSuspiciousAccountTransfers()).thenReturn(Arrays.asList(
                firstSuspiciousAccountTransferDTO, secondSuspiciousAccountTransferDTO
        ));

        mockMvc.perform(get("/suspiciousAccountTransfers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].accountTransferId", containsInAnyOrder(100, 200)))
                .andExpect(jsonPath("$[*].isBlocked", containsInAnyOrder(false, false)))
                .andExpect(jsonPath("$[*].isSuspicious", containsInAnyOrder(true, true)))
                .andExpect(jsonPath("$[*].blockedReason",
                        containsInAnyOrder(null, "Подозрительный перевод")))
                .andExpect(jsonPath("$[*].suspiciousReason",
                        containsInAnyOrder("Почему перевод по счёту попал в antifraud",
                                "Причина, почему перевод по счёту попал в antifraud")));
    }

    @Test
    void getSuspiciousAccountTransferById() throws Exception {
        when(suspiciousAccountTransferService.getSuspiciousAccountTransferById(anyLong()))
                .thenReturn(secondSuspiciousAccountTransferDTO);

        mockMvc.perform(get("/suspiciousAccountTransfers/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.accountTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.accountTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по счёту попал в antifraud")));
    }

    @Test
    void addSuspiciousAccountTransfer() throws Exception {
        when(suspiciousAccountTransferService.addSuspiciousAccountTransfer(any(SuspiciousAccountTransferDTO.class)))
                .thenReturn(secondSuspiciousAccountTransferDTO);

        mockMvc.perform(post("/suspiciousAccountTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousAccountTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.accountTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.accountTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по счёту попал в antifraud")));
    }

    @Test
    void updateSuspiciousAccountTransfer() throws Exception {
        when(suspiciousAccountTransferService.updateSuspiciousAccountTransfer(any(SuspiciousAccountTransferDTO.class)))
                .thenReturn(secondSuspiciousAccountTransferDTO);

        mockMvc.perform(put("/suspiciousAccountTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousAccountTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.accountTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.accountTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по счёту попал в antifraud")));
    }

    @Test
    void removeSuspiciousAccountTransfer() throws Exception {
        when(suspiciousAccountTransferService.deleteSuspiciousAccountTransferById((anyLong())))
                .thenReturn(secondSuspiciousAccountTransferDTO);

        mockMvc.perform(delete("/suspiciousAccountTransfers/2")
                        .content(asJsonString(secondSuspiciousAccountTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}