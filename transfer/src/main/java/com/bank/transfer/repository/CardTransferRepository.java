package com.bank.transfer.repository;

import com.bank.transfer.entity.transfers.CardTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransferRepository extends JpaRepository<CardTransfer, Long> {

}
