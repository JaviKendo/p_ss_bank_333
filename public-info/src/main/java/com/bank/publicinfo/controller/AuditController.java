package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/audit")
@Tag(name="Аудит", description="Работа с аудитом")
public class AuditController {
    private final AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех записей аудита", description = "Позволяет получить список всех записей аудита")
    public List<AuditDTO> getAllAudits() {
        log.info("Received request to get all Audits.");
        List<Audit> audits = auditService.getAllAudits();
        log.info("Returning {} Audits.", audits.size());
        return audits.stream().map(PublicInfoMapper.INSTANCE::convertToAuditDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одной записи аудита", description = "Позволяет получить одну запись аудита")
    public AuditDTO getAuditById(@PathVariable("id") Long id) {
        log.info("Received request to get Audit with id {}.", id);
        Audit audit = auditService.getAuditById(id);
        log.info("Returning Audit with id {}.", id);
        return PublicInfoMapper.INSTANCE.convertToAuditDTO(audit);
    }

    @PostMapping
    @Operation(summary = "Добавление новой записи аудита", description = "Позволяет добавить новую запись аудита")
    public Audit createAudit(@RequestBody AuditDTO auditDTO) {
        Audit audit = PublicInfoMapper.INSTANCE.convertToAudit(auditDTO);
        log.info("Received request to create new Audit");
        auditService.createAudit(audit);
        log.info("Audit created successfully");
        return audit;
    }
}
