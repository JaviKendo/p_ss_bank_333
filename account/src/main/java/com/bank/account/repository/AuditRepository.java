package com.bank.account.repository;

import com.bank.account.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностями Audit.
 * Использует JpaRepository для выполнения операций CRUD.
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}
