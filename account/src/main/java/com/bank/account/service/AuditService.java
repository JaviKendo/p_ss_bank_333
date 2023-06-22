package com.bank.account.service;

import com.bank.account.entity.Audit;

import java.util.List;
/**
 * Сервис для работы с аудитом операций.
 * Предоставляет функциональность получения аудита по идентификатору и получения списка всех аудитов.
 */
public interface AuditService {
    /**
     * Получает аудит операции по указанному идентификатору.
     *
     * @param id Идентификатор аудита.
     * @return Аудит операции.
     */
    Audit getAudit(Long id);
    /**
     * Возвращает список всех аудитов операций.
     *
     * @return Список аудитов операций.
     */
    List<Audit> getAllAudits();
}
