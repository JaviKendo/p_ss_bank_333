package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;

import java.util.List;

public interface SuspiciousCardTransferService {

    List<SuspiciousCardTransferDTO> getAllSuspiciousCardTransfers();

    SuspiciousCardTransferDTO getSuspiciousCardTransferById(Long id);

    SuspiciousCardTransferDTO addSuspiciousCardTransfer(SuspiciousCardTransferDTO suspiciousCardTransferDTO);

    SuspiciousCardTransferDTO updateSuspiciousCardTransfer(SuspiciousCardTransferDTO updatedSuspiciousCardTransferDTO);

    SuspiciousCardTransferDTO deleteSuspiciousCardTransferById(Long id);

}
