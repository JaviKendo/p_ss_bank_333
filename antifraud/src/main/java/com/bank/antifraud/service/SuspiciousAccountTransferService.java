package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;

import java.util.List;

public interface SuspiciousAccountTransferService {

    List<SuspiciousAccountTransferDTO> getAllSuspiciousAccountTransfers();

    SuspiciousAccountTransferDTO getSuspiciousAccountTransferById(Long id);

    SuspiciousAccountTransferDTO addSuspiciousAccountTransfer(
            SuspiciousAccountTransferDTO suspiciousAccountTransferDTO);

    SuspiciousAccountTransferDTO updateSuspiciousAccountTransfer(
            SuspiciousAccountTransferDTO updatedSuspiciousAccountTransferDTO);

    SuspiciousAccountTransferDTO deleteSuspiciousAccountTransferById(Long id);

}
