package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suspiciousAccountTransfers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Validated
@Tag(name = "Suspicious account transfers", description = "Methods for handling suspicious account transfers")
public class SuspiciousAccountTransferRestController {

    private final SuspiciousAccountTransferService suspiciousAccountTransferService;

    @GetMapping
    @Operation(summary = "Information about suspicious account transfers")
    @ApiResponse(responseCode = "200", description = "Suspicious account transfers got successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousAccountTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<List<SuspiciousAccountTransferDTO>> getAllSuspiciousAccountTransfers() {
        log.info("Request received to get all suspicious transfers by account number");
        List<SuspiciousAccountTransferDTO> transfers =
                suspiciousAccountTransferService.getAllSuspiciousAccountTransfers();
        log.info("Returning {} suspicious transfers by account number.", transfers.size());

        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about suspicious account transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious account transfer got successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousAccountTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious account transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousAccountTransferDTO> getSuspiciousAccountTransferById(
            @Valid @PathVariable("id") Long id) {
        log.info("Request received to get by id suspicious transfers by account number");
        SuspiciousAccountTransferDTO transfer = suspiciousAccountTransferService.getSuspiciousAccountTransferById(id);
        log.info("The SuspiciousAccountTransferDTO has gotten successfully with ID = {}", id);

        return ResponseEntity.ok(transfer);
    }

    @PostMapping
    @Operation(summary = "Add new suspicious account transfer")
    @ApiResponse(responseCode = "201", description = "Entity \"SuspiciousAccountTransfer\" created successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousAccountTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<SuspiciousAccountTransferDTO> addSuspiciousAccountTransfer(
            @Valid @RequestBody SuspiciousAccountTransferDTO suspiciousAccountTransferDTO) {
        log.info("Request received to create a new entity: SuspiciousAccountTransfer");
        SuspiciousAccountTransferDTO createdSuspiciousAccountTransferDTO =
                suspiciousAccountTransferService.addSuspiciousAccountTransfer(suspiciousAccountTransferDTO);
        log.info("Entity \"SuspiciousAccountTransfer\" created successfully with ID = {}",
                createdSuspiciousAccountTransferDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuspiciousAccountTransferDTO);
    }

    @PutMapping
    @Operation(summary = "Update data suspicious account transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious account transfer updated successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousAccountTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious account transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousAccountTransferDTO> updateSuspiciousAccountTransfer(
            @Valid @RequestBody SuspiciousAccountTransferDTO suspiciousAccountTransferDTO) {
        log.info("Request received to create a new entity: SuspiciousAccountTransfer");
        SuspiciousAccountTransferDTO transfer =
                suspiciousAccountTransferService.updateSuspiciousAccountTransfer(suspiciousAccountTransferDTO);
        log.info("Entity \"SuspiciousAccountTransfer\" updated successfully with ID = {}", transfer.getId());

        return ResponseEntity.ok(transfer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete suspicious account transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious account transfer deleted successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious account transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<String> removeSuspiciousAccountTransfer(@Valid @PathVariable("id") Long id) {
        log.info("Request received to create a new entity: SuspiciousAccountTransfer");
        suspiciousAccountTransferService.deleteSuspiciousAccountTransferById(id);
        log.info("Entity \"SuspiciousAccountTransfer\" deleted successfully with ID = {}", id);

        return ResponseEntity.ok(String.format("SuspiciousAccountTransfer with ID = %d was delete!", id));
    }

}
