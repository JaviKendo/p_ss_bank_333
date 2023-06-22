package com.bank.history.controllersTest;

import com.bank.history.controllers.HistoryRestController;
import com.bank.history.dto.HistoryDTO;
import com.bank.history.models.History;
import com.bank.history.services.HistoryService;
import com.bank.history.util.HistoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class HistoryRestControllerTest {

    private HistoryRestController historyRestController;

    @Mock
    private HistoryService historyService;

    @Mock
    private HistoryMapper historyMapper;

    @BeforeEach
    void setUp() {
        historyRestController = new HistoryRestController(historyService, historyMapper);
    }

    @Test
    void saveHistoryShouldReturnCreatedStatus() {
        final HistoryDTO historyDto = new HistoryDTO();
        final History history = new History();
        Mockito.when(historyMapper.toEntity(historyDto)).thenReturn(history);
        Mockito.when(historyService.saveHistory(history)).thenReturn(history);
        Mockito.when(historyMapper.toHistoryDTO(history)).thenReturn(historyDto);

        final ResponseEntity<HistoryDTO> responseEntity = historyRestController.saveHistory(historyDto);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Mockito.verify(historyService, Mockito.times(1)).saveHistory(history);
        Mockito.verify(historyMapper, Mockito.times(1)).toEntity(historyDto);
        Mockito.verify(historyMapper, Mockito.times(1)).toHistoryDTO(history);
    }

    @Test
    void testGetHistoryById() {
        final History history = new History();
        history.setId(1L);
        history.setTransferAuditId(1L);
        history.setProfileAuditId(1L);
        history.setAccountAuditId(1L);
        history.setAntiFraudAuditId(1L);
        history.setPublicBankInfoAuditId(1L);
        history.setAuthorizationAuditId(1L);
        Mockito.when(historyService.findOne(1L)).thenReturn(history);

        final HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(history.getId());
        historyDTO.setTransferAuditId(history.getTransferAuditId());
        historyDTO.setProfileAuditId(history.getProfileAuditId());
        historyDTO.setAccountAuditId(history.getAccountAuditId());
        historyDTO.setAntiFraudAuditId(history.getAntiFraudAuditId());
        historyDTO.setPublicBankInfoAuditId(history.getPublicBankInfoAuditId());
        historyDTO.setAuthorizationAuditId(history.getAuthorizationAuditId());

        Mockito.when(historyMapper.toHistoryDTO(history)).thenReturn(historyDTO);

        final ResponseEntity<HistoryDTO> response = historyRestController.getHistoryById(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(history.getId(), response.getBody().getId());
        Assertions.assertEquals(history.getTransferAuditId(), response.getBody().getTransferAuditId());
        Assertions.assertEquals(history.getProfileAuditId(), response.getBody().getProfileAuditId());
        Assertions.assertEquals(history.getAccountAuditId(), response.getBody().getAccountAuditId());
        Assertions.assertEquals(history.getAntiFraudAuditId(), response.getBody().getAntiFraudAuditId());
        Assertions.assertEquals(history.getPublicBankInfoAuditId(), response.getBody().getPublicBankInfoAuditId());
        Assertions.assertEquals(history.getAuthorizationAuditId(), response.getBody().getAuthorizationAuditId());
    }

    @Test
    void testGetHistoryByIdNotFound() {
        Mockito.when(historyService.findOne(1L)).thenReturn(null);

        final ResponseEntity<HistoryDTO> response = historyRestController.getHistoryById(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    void testUpdateHistory() {
        final HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(1L);
        historyDTO.setTransferAuditId(1L);
        historyDTO.setProfileAuditId(1L);
        historyDTO.setAccountAuditId(1L);
        historyDTO.setAntiFraudAuditId(1L);
        historyDTO.setPublicBankInfoAuditId(1L);
        historyDTO.setAuthorizationAuditId(1L);

        final History history = new History();
        history.setId(1L);
        history.setTransferAuditId(1L);
        history.setProfileAuditId(1L);
        history.setAccountAuditId(1L);
        history.setAntiFraudAuditId(1L);
        history.setPublicBankInfoAuditId(1L);
        history.setAuthorizationAuditId(1L);

        Mockito.when(historyService.findOne(1L)).thenReturn(history);

        final ResponseEntity<Void> response = historyRestController.updateHistory(1L, historyDTO);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(historyService).update(history);
        Assertions.assertEquals(historyDTO.getTransferAuditId(), history.getTransferAuditId());
        Assertions.assertEquals(historyDTO.getProfileAuditId(), history.getProfileAuditId());
        Assertions.assertEquals(historyDTO.getAccountAuditId(), history.getAccountAuditId());
        Assertions.assertEquals(historyDTO.getAntiFraudAuditId(), history.getAntiFraudAuditId());
        Assertions.assertEquals(historyDTO.getPublicBankInfoAuditId(), history.getPublicBankInfoAuditId());
        Assertions.assertEquals(historyDTO.getAuthorizationAuditId(), history.getAuthorizationAuditId());
    }

    @Test
    void testUpdateHistoryNotFound() {
        final HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(1L);
        historyDTO.setTransferAuditId(1L);
        historyDTO.setProfileAuditId(1L);
        historyDTO.setAccountAuditId(1L);
        historyDTO.setAntiFraudAuditId(1L);
        historyDTO.setPublicBankInfoAuditId(1L);
        historyDTO.setAuthorizationAuditId(1L);

        Mockito.when(historyService.findOne(1L)).thenReturn(null);

        final ResponseEntity<Void> response = historyRestController.updateHistory(1L, historyDTO);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(historyService, Mockito.never()).update(ArgumentMatchers.any(History.class));
    }

    @Test
    void testGetAllHistories() {
        final List<History> histories = new ArrayList<>();
        final History history1 = new History();
        history1.setId(1L);
        history1.setTransferAuditId(1L);
        history1.setProfileAuditId(1L);
        history1.setAccountAuditId(1L);
        history1.setAntiFraudAuditId(1L);
        history1.setPublicBankInfoAuditId(1L);
        history1.setAuthorizationAuditId(1L);
        histories.add(history1);

        final History history2 = new History();
        history2.setId(2L);
        history2.setTransferAuditId(2L);
        history2.setProfileAuditId(2L);
        history2.setAccountAuditId(2L);
        history2.setAntiFraudAuditId(2L);
        history2.setPublicBankInfoAuditId(2L);
        history2.setAuthorizationAuditId(2L);
        histories.add(history2);

        Mockito.when(historyService.findAll()).thenReturn(histories);

        final HistoryDTO historyDTO1 = new HistoryDTO();
        historyDTO1.setId(history1.getId());
        historyDTO1.setTransferAuditId(history1.getTransferAuditId());
        historyDTO1.setProfileAuditId(history1.getProfileAuditId());
        historyDTO1.setAccountAuditId(history1.getAccountAuditId());
        historyDTO1.setAntiFraudAuditId(history1.getAntiFraudAuditId());
        historyDTO1.setPublicBankInfoAuditId(history1.getPublicBankInfoAuditId());
        historyDTO1.setAuthorizationAuditId(history1.getAuthorizationAuditId());

        final HistoryDTO historyDTO2 = new HistoryDTO();
        historyDTO2.setId(history2.getId());
        historyDTO2.setTransferAuditId(history2.getTransferAuditId());
        historyDTO2.setProfileAuditId(history2.getProfileAuditId());
        historyDTO2.setAccountAuditId(history2.getAccountAuditId());
        historyDTO2.setAntiFraudAuditId(history2.getAntiFraudAuditId());
        historyDTO2.setPublicBankInfoAuditId(history2.getPublicBankInfoAuditId());
        historyDTO2.setAuthorizationAuditId(history2.getAuthorizationAuditId());

        Mockito.when(historyMapper.toHistoryDTO(history1)).thenReturn(historyDTO1);
        Mockito.when(historyMapper.toHistoryDTO(history2)).thenReturn(historyDTO2);

        final ResponseEntity<List<HistoryDTO>> response = historyRestController.getAllHistories();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());

        Assertions.assertEquals(history1.getId(), historyDTO1.getId());
        Assertions.assertEquals(history1.getTransferAuditId(), historyDTO1.getTransferAuditId());
        Assertions.assertEquals(history1.getProfileAuditId(), historyDTO1.getProfileAuditId());
        Assertions.assertEquals(history1.getAccountAuditId(), historyDTO1.getAccountAuditId());
        Assertions.assertEquals(history1.getAntiFraudAuditId(), historyDTO1.getAntiFraudAuditId());
        Assertions.assertEquals(history1.getPublicBankInfoAuditId(), historyDTO1.getPublicBankInfoAuditId());
        Assertions.assertEquals(history1.getAuthorizationAuditId(), historyDTO1.getAuthorizationAuditId());

        Assertions.assertEquals(history2.getId(), historyDTO2.getId());
        Assertions.assertEquals(history2.getTransferAuditId(), historyDTO2.getTransferAuditId());
        Assertions.assertEquals(history2.getProfileAuditId(), historyDTO2.getProfileAuditId());
        Assertions.assertEquals(history2.getAccountAuditId(), historyDTO2.getAccountAuditId());
        Assertions.assertEquals(history2.getAntiFraudAuditId(), historyDTO2.getAntiFraudAuditId());
        Assertions.assertEquals(history2.getPublicBankInfoAuditId(), historyDTO2.getPublicBankInfoAuditId());
        Assertions.assertEquals(history2.getAuthorizationAuditId(), historyDTO2.getAuthorizationAuditId());
    }

    @Test
    void testDeleteHistoryById() {
        final History history = new History();
        history.setId(1L);
        history.setTransferAuditId(1L);
        history.setProfileAuditId(1L);
        history.setAccountAuditId(1L);
        history.setAntiFraudAuditId(1L);
        history.setPublicBankInfoAuditId(1L);
        history.setAuthorizationAuditId(1L);

        Mockito.when(historyService.findOne(1L)).thenReturn(history);

        final ResponseEntity<Void> response = historyRestController.deleteHistoryById(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(historyService).deleteHistoryById(1L);
    }
}
