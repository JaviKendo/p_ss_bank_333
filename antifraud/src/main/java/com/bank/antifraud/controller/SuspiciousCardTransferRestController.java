package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/suspiciousCardTransfers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Tag(name = "Suspicious card transfers", description = "Methods for handling suspicious card transfers")
public class SuspiciousCardTransferRestController {

    private final SuspiciousCardTransferService suspiciousCardTransferService;

    @GetMapping
    @Operation(summary = "Information about suspicious card transfers")
    @ApiResponse(responseCode = "200", description = "Suspicious card transfers got successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousCardTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<List<SuspiciousCardTransferDTO>> getAllSuspiciousCardTransfers() {
        return ResponseEntity.ok(suspiciousCardTransferService.getAllSuspiciousCardTransfers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about suspicious card transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious card transfer got successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousCardTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious card transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousCardTransferDTO> getSuspiciousCardTransferById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(suspiciousCardTransferService.getSuspiciousCardTransferById(id));
    }

    @PostMapping
    @Operation(summary = "Add new suspicious card transfer")
    @ApiResponse(responseCode = "201", description = "Entity \"SuspiciousCardTransfer\" created successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousCardTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<SuspiciousCardTransferDTO> addSuspiciousCardTransfer(
            @Valid @RequestBody SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(suspiciousCardTransferService.addSuspiciousCardTransfer(suspiciousCardTransferDTO));
    }

    @PutMapping
    @Operation(summary = "Update data suspicious card transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious card transfer updated successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousCardTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious card transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousCardTransferDTO> updateSuspiciousCardTransfer(
            @Valid @RequestBody SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        return ResponseEntity.ok(suspiciousCardTransferService.updateSuspiciousCardTransfer(suspiciousCardTransferDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete suspicious card transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious card transfer deleted successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious card transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<String> removeSuspiciousCardTransfer(@Valid @PathVariable("id") Long id) {
        suspiciousCardTransferService.deleteSuspiciousCardTransferById(id);

        return ResponseEntity.ok(String.format("SuspiciousCardTransfer with ID = %d was delete!", id));
    }

}
