package com.bank.account.service;

import com.bank.account.aspect.Auditable;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.account.util.AuditingActionType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Реализация сервиса для работы с деталями банковского счета.
 * Обеспечивает функциональность создания, получения, обновления и удаления деталей банковского счета.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountDetailsServiceImp implements AccountDetailsService {
    private final Logger logger = LoggerFactory.getLogger(AccountDetailsServiceImp.class);
    private final AccountRepository accountRepository;
    /**
     * Сохраняет детали банковского счета.
     *
     * @param detailsDTO Детали банковского счета в формате DTO.
     */
    @Transactional
    @Auditable(actionType = AuditingActionType.CREATE)
    @Override
    public void save(AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод save");
        accountRepository.save(AccountDetailsMapper.INSTANCE.toEntity(detailsDTO));
    }
    /**
     * Возвращает список всех деталей банковских счетов.
     *
     * @return Список деталей банковских счетов в формате DTO.
     */
    @Override
    public List<AccountDetailsDTO> getAllAccountDetails() {
        logger.info("запущен метод getAllAccountDetails");
        return accountRepository.findAll().stream().map(AccountDetailsMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
    /**
     * Получает детали банковского счета по указанному идентификатору.
     *
     * @param id Идентификатор деталей банковского счета.
     * @return Детали банковского счета в формате DTO.
     * @throws AccountDetailsNotFoundException если детали банковского счета не найдены.
     */
    @Override
    public AccountDetailsDTO getAccountDetails(Long id) {
        logger.info("запущен метод getAccountDetails");
        return AccountDetailsMapper.INSTANCE.toDTO(accountRepository.findById(id).orElseThrow(AccountDetailsNotFoundException::new));
    }
    /**
     * Удаляет детали банковского счета по указанному идентификатору.
     *
     * @param id Идентификатор деталей банковского счета.
     */
    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.DELETE)
    public void deleteById(Long id) {
        logger.info("запущен метод deleteById");
        accountRepository.deleteById(id);
    }
    /**
     * Обновляет детали банковского счета.
     *
     * @param detailsDTO Обновленные детали банковского счета в формате DTO.
     * @throws AccountDetailsNotFoundException если детали банковского счета не найдены.
     */
    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.UPDATE)
    public void updateAccountDetails(AccountDetailsDTO detailsDTO) {
        logger.info("Запущен метод updateAccountDetails");
        logger.info("Идентификатор: {}", detailsDTO.getId()); // Логирование идентификатора
        logger.info("Значение negativeBalance: {}", detailsDTO.getNegativeBalance()); // Логирование значения negativeBalance

        AccountDetails updateDetails = accountRepository
                .findAccountDetailsByAccountNumber(detailsDTO.getAccountNumber())
                .orElseThrow(AccountDetailsNotFoundException::new);
        updateDetails.setNegativeBalance(detailsDTO.getNegativeBalance());
        accountRepository.save(updateDetails);
    }
}
