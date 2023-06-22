package com.bank.history.controllers;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.models.History;
import com.bank.history.services.HistoryService;
import com.bank.history.util.HistoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HistoryRestController {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    public HistoryRestController(HistoryService historyService, HistoryMapper historyMapper) {
        this.historyService = historyService;
        this.historyMapper = historyMapper;
    }


    @PostMapping("/save")
    public ResponseEntity<HistoryDTO> saveHistory(@RequestBody HistoryDTO historyDto) {
        History history = historyService.saveHistory(historyMapper.toEntity(historyDto));
        return new ResponseEntity<>(historyMapper.toHistoryDTO(history), HttpStatus.CREATED);
    }

    @Operation(summary = "Get history by id", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = History.class))
            }),
            @ApiResponse(responseCode = "404", description = "History not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistoryDTO> getHistoryById(@PathVariable Long id) {
        History history = historyService.findOne(id);
        if (history == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(historyMapper.toHistoryDTO(history), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHistory(@PathVariable Long id, @RequestBody HistoryDTO historyDTO) {
        final History history = historyService.findOne(id);
        if (history == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        historyMapper.updateEntityFromDto(historyDTO, history);
        historyService.update(history);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HistoryDTO>> getAllHistories() {
        final List<History> histories = historyService.findAll();
        final List<HistoryDTO> historyDtos = histories.stream().
                map(historyMapper::toHistoryDTO).collect(Collectors.toList());
        return new ResponseEntity<>(historyDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoryById(@PathVariable Long id) {
        final History history = historyService.findOne(id);
        if (history == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        historyService.deleteHistoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
