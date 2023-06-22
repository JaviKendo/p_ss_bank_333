package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDTO;
import com.bank.antifraud.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/audits")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Tag(name = "Audits", description = "Methods for viewing audits")
public class AuditRestController {

    private final AuditService auditService;

    @GetMapping
    @Operation(summary = "Information about all audits")
    @ApiResponse(responseCode = "200", description = "Audits got successfully",
            content = {@Content(schema = @Schema(implementation = AuditDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})
    public ResponseEntity<List<AuditDTO>> getAllAudits() {
        return ResponseEntity.ok(auditService.getAllAudits());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about audit by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audit got successfully",
                    content = {@Content(schema = @Schema(implementation = AuditDTO.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Incorrect request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Audit not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<AuditDTO> getAuditById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(auditService.getAuditById(id));
    }

}
