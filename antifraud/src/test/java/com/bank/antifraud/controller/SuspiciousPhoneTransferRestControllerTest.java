package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
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
@WebMvcTest(SuspiciousPhoneTransferRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class SuspiciousPhoneTransferRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuspiciousPhoneTransferService suspiciousPhoneTransferService;

    private final SuspiciousPhoneTransferDTO firstSuspiciousPhoneTransferDTO =
            SuspiciousPhoneTransferDTO.builder()
                    .id(1L)
                    .phoneTransferId(100L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason(null)
                    .suspiciousReason("Почему перевод по телефону попал в antifraud")
                    .build();

    private final SuspiciousPhoneTransferDTO secondSuspiciousPhoneTransferDTO =
            SuspiciousPhoneTransferDTO.builder()
                    .id(2L)
                    .phoneTransferId(200L)
                    .isBlocked(false)
                    .isSuspicious(true)
                    .blockedReason("Подозрительный перевод")
                    .suspiciousReason("Причина, почему перевод по телефону попал в antifraud")
                    .build();

    @Test
    void getAllSuspiciousPhoneTransfersAPI() throws Exception {
        when(suspiciousPhoneTransferService.getAllSuspiciousPhoneTransfers()).thenReturn(Arrays.asList(
                firstSuspiciousPhoneTransferDTO, secondSuspiciousPhoneTransferDTO
        ));

        mockMvc.perform(get("/suspiciousPhoneTransfers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].phoneTransferId", containsInAnyOrder(100, 200)))
                .andExpect(jsonPath("$[*].isBlocked", containsInAnyOrder(false, false)))
                .andExpect(jsonPath("$[*].isSuspicious", containsInAnyOrder(true, true)))
                .andExpect(jsonPath("$[*].blockedReason",
                        containsInAnyOrder(null, "Подозрительный перевод")))
                .andExpect(jsonPath("$[*].suspiciousReason",
                        containsInAnyOrder("Почему перевод по телефону попал в antifraud",
                                "Причина, почему перевод по телефону попал в antifraud")));
    }

    @Test
    void getSuspiciousPhoneTransferById() throws Exception {
        when(suspiciousPhoneTransferService.getSuspiciousPhoneTransferById(anyLong()))
                .thenReturn(secondSuspiciousPhoneTransferDTO);

        mockMvc.perform(get("/suspiciousPhoneTransfers/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.phoneTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.phoneTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по телефону попал в antifraud")));
    }

    @Test
    void addSuspiciousPhoneTransfer() throws Exception {
        when(suspiciousPhoneTransferService.addSuspiciousPhoneTransfer(any(SuspiciousPhoneTransferDTO.class)))
                .thenReturn(secondSuspiciousPhoneTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/suspiciousPhoneTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousPhoneTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.phoneTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.phoneTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по телефону попал в antifraud")));
    }

    @Test
    void updateSuspiciousPhoneTransfer() throws Exception {
        when(suspiciousPhoneTransferService.updateSuspiciousPhoneTransfer(any(SuspiciousPhoneTransferDTO.class)))
                .thenReturn(secondSuspiciousPhoneTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/suspiciousPhoneTransfers")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(secondSuspiciousPhoneTransferDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.phoneTransferId").isNumber())
                .andExpect(jsonPath("$.isBlocked").isBoolean())
                .andExpect(jsonPath("$.isSuspicious").isBoolean())
                //blockedReason может быть null(проверять isEmpty), но может быть String(проверять isString), поэтому не проверяется вообще
                .andExpect(jsonPath("$.suspiciousReason").isString())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.phoneTransferId", equalTo(200)))
                .andExpect(jsonPath("$.isBlocked", equalTo(false)))
                .andExpect(jsonPath("$.isSuspicious", equalTo(true)))
                .andExpect(jsonPath("$.blockedReason", equalTo("Подозрительный перевод")))
                .andExpect(jsonPath("$.suspiciousReason",
                        equalTo("Причина, почему перевод по телефону попал в antifraud")));
    }

    @Test
    void removeSuspiciousPhoneTransfer() throws Exception {
        when(suspiciousPhoneTransferService.deleteSuspiciousPhoneTransferById((anyLong())))
                .thenReturn(secondSuspiciousPhoneTransferDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/suspiciousPhoneTransfers/2")
                        .content(asJsonString(secondSuspiciousPhoneTransferDTO))
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