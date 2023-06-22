package com.bank.account.service;

import com.bank.account.entity.Audit;
import com.bank.account.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
/**
 * Сервис для работы с аудитом операций.
 * Предоставляет функциональность получения аудита по идентификатору и получения списка всех аудитов.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditServiceImp implements AuditService {
    private final AuditRepository repository;
    /**
     * Получает аудит операции по указанному идентификатору.
     *
     * @param id Идентификатор аудита.
     * @return Аудит операции.
     * @throws EntityNotFoundException если аудит не найден.
     */
    @Override
    public Audit getAudit(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    /**
     * Возвращает список всех аудитов операций.
     *
     * @return Список аудитов операций.
     */
    @Override
    public List<Audit> getAllAudits() {
        return repository.findAll();
    }
}
