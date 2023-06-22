package com.bank.account.controller;

import com.bank.account.entity.Audit;
import com.bank.account.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Контроллер для управления операциями аудита.
 * Обрабатывает HTTP-запросы, связанные с получением информации о записях аудита.
 */
@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
@Tag(name = "AuditController",
        description = "Этот микросервис позволяет просматривать результат аудирования")
public class AuditController {
    private final AuditService service;
    /**
     * Возвращает запись аудита по заданному идентификатору.
     *
     * @param id  идентификатор записи аудита
     * @return объект Audit, содержащий информацию о записи аудита
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение записи аудита по id")
    Audit getAudit(@PathVariable Long id) {
        return service.getAudit(id);
    }
    /**
     * Возвращает все записи аудита.
     *
     * @return список объектов Audit, содержащих информацию о записях аудита
     */
    @GetMapping
    @Operation(summary = "Получение всех записей аудита")
    List<Audit> getAllAudits() {
        return service.getAllAudits();
    }

}
