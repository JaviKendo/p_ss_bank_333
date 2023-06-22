package com.bank.account.repository;

import com.bank.account.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Репозиторий для работы с сущностями AccountDetails.
 * Использует JpaRepository для выполнения операций CRUD.
 */

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long> {
    /**
     * Находит информацию о счете по номеру счета.
     * @param AccountNumber номер счета
     * @return Optional с информацией о счете (AccountDetails), если счет найден, или пустое значение, если счет не найден
     */
    Optional<AccountDetails> findAccountDetailsByAccountNumber(Long AccountNumber);

}
