package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;

import java.util.List;

public interface SuspiciousPhoneTransferService {

    List<SuspiciousPhoneTransferDTO> getAllSuspiciousPhoneTransfers();

    SuspiciousPhoneTransferDTO getSuspiciousPhoneTransferById(Long id);

    SuspiciousPhoneTransferDTO addSuspiciousPhoneTransfer(SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO);

    SuspiciousPhoneTransferDTO updateSuspiciousPhoneTransfer(
            SuspiciousPhoneTransferDTO updatedSuspiciousPhoneTransferDTO);

    SuspiciousPhoneTransferDTO deleteSuspiciousPhoneTransferById(Long id);

}
