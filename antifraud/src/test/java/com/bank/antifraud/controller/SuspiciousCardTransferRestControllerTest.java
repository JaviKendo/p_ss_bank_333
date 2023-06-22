package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SuspiciousCardTransferRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class SuspiciousCardTransferRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuspiciousCardTransferService suspiciousCardTransferService;

    private final SuspiciousCardTransferDTO firstSuspiciousCardTransferDTO =
            SuspiciousCardTransferDTO.builder()
                    .id(1L)
                    .cardTransferId(100L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason(null)
                    .suspiciousReason("Почему перевод по карте попал в antifraud")
                    .build();

    private final SuspiciousCardTransferDTO secondSuspiciousCardTransferDTO =
            SuspiciousCardTransferDTO.builder()
                    .id(2L)
                    .cardTransferId(200L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason("Подозрительный перевод")
                    .suspiciousReason("Причина, почему перевод по карте попал в antifraud")
                    .build();

    @Test
    void getAllSuspiciousCardTransfersAPI() throws Exception {
        when(suspiciousCardTransferService.getAllSuspiciousCardTransfers()).thenReturn(Arrays.asList(
                firstSuspiciousCardTransferDTO, secondSuspiciousCardTransferDTO
        ));

        mockMvc.perform(get("/suspiciousCardTransfers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].cardTransferId", containsInAnyOrder(100, 200)))
                .andExpect(jsonPath("$[*].isBlocked", containsInAnyOrder(false, false)))
                .andExpect(jsonPath("$[*].isSuspicious", containsInAnyOrder(true, true)))
                .andExpect(jsonPath("$[*].blockedReason",
                        containsInAnyOrder(null, "Подозрительный перевод")))
                .andExpect(jsonPath("$[*].suspiciousReason",
                        containsInAnyOrder("Почему перевод по карте попал в antifraud",
                                "Причина, почему перевод по карте попал в antifraud")));
    }

    @Test
    void getSuspiciousCardTransferById() throws Exception {
        when(suspiciousCardTransferService.getSuspiciousCardTransferById(anyLong()))
                .thenReturn(secondSuspiciousCardTransferDTO);

        mockMvc.perform(get("/suspiciousCardTransfers/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.cardTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.cardTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по карте попал в antifraud")));
    }

    @Test
    void addSuspiciousCardTransfer() throws Exception {
        when(suspiciousCardTransferService.addSuspiciousCardTransfer(any(SuspiciousCardTransferDTO.class)))
                .thenReturn(secondSuspiciousCardTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/suspiciousCardTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousCardTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.cardTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.cardTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по карте попал в antifraud")));
    }

    @Test
    void updateSuspiciousCardTransfer() throws Exception {
        when(suspiciousCardTransferService.updateSuspiciousCardTransfer(any(SuspiciousCardTransferDTO.class)))
                .thenReturn(secondSuspiciousCardTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/suspiciousCardTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousCardTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.cardTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.cardTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по карте попал в antifraud")));
    }

    @Test
    void removeSuspiciousCardTransfer() throws Exception {
        when(suspiciousCardTransferService.deleteSuspiciousCardTransferById((anyLong())))
                .thenReturn(secondSuspiciousCardTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/suspiciousCardTransfers/2")
                        .content(asJsonString(secondSuspiciousCardTransferDTO))
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