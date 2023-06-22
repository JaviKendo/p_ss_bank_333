package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
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
@RequestMapping("/suspiciousPhoneTransfers")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Tag(name = "Suspicious phone transfers", description = "Methods for handling suspicious phone transfers")
public class SuspiciousPhoneTransferRestController {

    private final SuspiciousPhoneTransferService suspiciousPhoneTransferService;

    @GetMapping
    @Operation(summary = "Information about suspicious phone transfers")
    @ApiResponse(responseCode = "200", description = "Suspicious phone transfers got successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousPhoneTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<List<SuspiciousPhoneTransferDTO>> getAllSuspiciousPhoneTransfers() {
        return ResponseEntity.ok(suspiciousPhoneTransferService.getAllSuspiciousPhoneTransfers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about suspicious phone transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious phone transfer got successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousPhoneTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious phone transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousPhoneTransferDTO> getSuspiciousPhoneTransferById(
            @Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(suspiciousPhoneTransferService.getSuspiciousPhoneTransferById(id));
    }

    @PostMapping
    @Operation(summary = "Add new suspicious phone transfer")
    @ApiResponse(responseCode = "201", description = "Entity \"SuspiciousPhoneTransfer\" created successfully",
            content = {@Content(schema = @Schema(implementation = SuspiciousPhoneTransferDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<SuspiciousPhoneTransferDTO> addSuspiciousPhoneTransfer(
            @Valid @RequestBody SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(suspiciousPhoneTransferService
                        .addSuspiciousPhoneTransfer(suspiciousPhoneTransferDTO));
    }

    @PutMapping
    @Operation(summary = "Update data suspicious phone transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious phone transfer updated successfully",
                    content = {@Content(schema = @Schema(implementation = SuspiciousPhoneTransferDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious phone transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<SuspiciousPhoneTransferDTO> updateSuspiciousPhoneTransfer(
            @Valid @RequestBody SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO) {
        return ResponseEntity.ok(suspiciousPhoneTransferService
                .updateSuspiciousPhoneTransfer(suspiciousPhoneTransferDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete suspicious phone transfer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suspicious phone transfer deleted successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Suspicious phone transfer not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<String> removeSuspiciousPhoneTransfer(@Valid @PathVariable("id") Long id) {
        suspiciousPhoneTransferService.deleteSuspiciousPhoneTransferById(id);

        return ResponseEntity.ok(String.format("SuspiciousPhoneTransfer with ID = %d was delete!", id));
    }

}
