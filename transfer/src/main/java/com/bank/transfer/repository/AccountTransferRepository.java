package com.bank.transfer.repository;

import com.bank.transfer.entity.transfers.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
}
