package com.bank.profile.controller;

import com.bank.profile.dto.AuditDTO;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.AuditMapper;
import com.bank.profile.service.AuditService;
import com.bank.profile.util.ErrorsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audits")
@Tag(name = "Аудирование", description = "Методы для работы с аудитом")
public class AuditController {
    private final AuditService auditService;
    private final AuditMapper auditMapper;

    public AuditController(AuditService auditService, AuditMapper auditMapper) {
        this.auditService = auditService;
        this.auditMapper = auditMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех записях")
    public List<AuditDTO> showAllAudits() {
        return auditService.getAllAudits().stream().map(auditMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить запись по id")
    public AuditDTO showAuditById(@PathVariable("id") long id) {
        return auditMapper.toDTO(auditService.getAuditById(id));
    }

    @PostMapping("/")
    @Operation(summary = "Добаление записи")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> createAudit(@RequestBody AuditDTO auditDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        auditService.saveAudit(auditMapper.toEntity(auditDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение записи по id")
    public ResponseEntity<HttpStatus> updateAudit(@RequestBody AuditDTO auditDTO,
                                                     @PathVariable("id") long id,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        auditService.updateAudit(id, auditMapper.toEntity(auditDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление записи по id")
    public ResponseEntity<HttpStatus> deleteAudit(@PathVariable("id") long id) {
        auditService.deleteAudit(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
