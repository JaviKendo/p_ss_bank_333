package com.bank.authorization.controller;

import com.bank.authorization.entity.Audit;
import com.bank.authorization.repository.AuditRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Аудит", description = "Операции, связанные с аудитом")
@RestController
@RequestMapping("audits")
public class AuditController {

    private final AuditRepository auditRepository;
    @Autowired
    public AuditController(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Operation(summary = "Получение списка всех аудитов")
    @GetMapping
    public ResponseEntity<List<Audit>> getAllAudits() {
        final List<Audit> audits = auditRepository.findAll();
        return new ResponseEntity<>(audits, HttpStatus.OK);
    }
}
