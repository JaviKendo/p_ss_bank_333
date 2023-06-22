package com.bank.history.services.impl;

import com.bank.history.models.History;
import com.bank.history.repositories.HistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class HistoryServiceImplTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryServiceImpl historyService;

    private History history;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        history = new History();
        history.setId(1L);
        history.setTransferAuditId(2L);
        history.setProfileAuditId(2L);
        history.setAccountAuditId(2L);
        history.setAntiFraudAuditId(2L);
        history.setPublicBankInfoAuditId(2L);
        history.setAuthorizationAuditId(2L);
    }

    @Test
    void saveHistory() {
        Mockito.when(historyRepository.save(history)).thenReturn(history);
        final History savedHistory = historyService.saveHistory(history);
        Assertions.assertEquals(savedHistory, history);
        Mockito.verify(historyRepository, Mockito.times(1)).save(history);
    }

    @Test
    void findOne() {
        Mockito.when(historyRepository.findById(1L)).thenReturn(Optional.of(history));
        final History foundHistory = historyService.findOne(1L);
        Assertions.assertEquals(foundHistory, history);
        Mockito.verify(historyRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void update() {
        Mockito.when(historyRepository.save(history)).thenReturn(history);
        historyService.update(history);
        Mockito.verify(historyRepository, Mockito.times(1)).save(history);
    }

    @Test
    void findAll() {
        final List<History> histories = new ArrayList<>();
        histories.add(history);
        Mockito.when(historyRepository.findAll()).thenReturn(histories);
        final List<History> foundHistories = historyService.findAll();
        Assertions.assertEquals(foundHistories, histories);
        Mockito.verify(historyRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteHistoryById() {
        historyService.deleteHistoryById(1L);
        Mockito.verify(historyRepository, Mockito.times(1)).deleteById(1L);
    }
}
