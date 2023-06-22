package com.bank.account.service;

import com.bank.account.aspect.Auditable;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.util.AuditingActionType;

import java.util.List;
/**
 * Сервис для работы с деталями банковского счета.
 * Предоставляет методы для создания, получения, обновления и удаления деталей банковского счета.
 */
public interface AccountDetailsService {
    /**
     * Сохраняет детали банковского счета.
     *
     * @param detailsDTO Детали банковского счета в формате DTO.
     */
    @Auditable(actionType = AuditingActionType.CREATE)
    void save(AccountDetailsDTO detailsDTO);
    /**
     * Возвращает список всех деталей банковских счетов.
     *
     * @return Список деталей банковских счетов в формате DTO.
     */
    List<AccountDetailsDTO> getAllAccountDetails();
    /**
     * Получает детали банковского счета по указанному идентификатору.
     *
     * @param id Идентификатор деталей банковского счета.
     * @return Детали банковского счета в формате DTO.
     */
    AccountDetailsDTO getAccountDetails(Long id);
    /**
     * Удаляет детали банковского счета по указанному идентификатору.
     *
     * @param id Идентификатор деталей банковского счета.
     */
    void deleteById(Long id);
    /**
     * Обновляет детали банковского счета.
     *
     * @param detailsDTO Обновленные детали банковского счета в формате DTO.
     */
    @Auditable(actionType = AuditingActionType.UPDATE)
    void updateAccountDetails(AccountDetailsDTO detailsDTO);
}
