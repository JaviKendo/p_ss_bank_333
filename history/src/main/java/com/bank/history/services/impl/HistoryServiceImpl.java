package com.bank.history.services.impl;

import com.bank.history.models.History;
import com.bank.history.repositories.HistoryRepository;
import com.bank.history.services.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional(readOnly = true)
//@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {


    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional
    public History saveHistory(History history) {
        if (history != null) {
            historyRepository.save(history);
            log.info("Saving history: {}", history);
        }
        return history;
    }

    @Override
    @Transactional
    @Operation(summary = "Get history by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = History.class))
            }),
            @ApiResponse(responseCode = "404", description = "History not found", content = @Content)
    })
    public History findOne(Long id) {
        log.info("Finding history with id {}", id);
        final Optional<History> foundHistory = historyRepository.findById(id);
        return foundHistory.orElse(null);
    }

    @Override
    @Transactional
    public void update(History history) {
        if (history != null) {
            log.info("Updating history with id {}", history.getId());
            historyRepository.save(history);
        }
    }

    @Override
    @Transactional
    public List<History> findAll() {

        return historyRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteHistoryById(Long id) {
        if (id != 0) {
            historyRepository.deleteById(id);
        }
    }
}
