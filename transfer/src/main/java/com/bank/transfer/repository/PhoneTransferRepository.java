package com.bank.transfer.repository;

import com.bank.transfer.entity.transfers.PhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneTransferRepository extends JpaRepository<PhoneTransfer, Long> {
}
