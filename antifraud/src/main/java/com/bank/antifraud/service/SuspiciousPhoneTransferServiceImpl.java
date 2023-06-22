package com.bank.antifraud.service;

import com.bank.antifraud.custom_audit.Auditable;
import com.bank.antifraud.custom_audit.AuditingActionType;
import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.exception_handling.exception.NoSuchTransferException;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousPhoneTransferServiceImpl implements SuspiciousPhoneTransferService {

    private final SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository;
    private final SuspiciousPhoneTransferMapper suspiciousPhoneTransferMapper;

    @Override
    public List<SuspiciousPhoneTransferDTO> getAllSuspiciousPhoneTransfers() {
        return suspiciousPhoneTransferMapper.toDTOList(suspiciousPhoneTransferRepository.findAll());
    }

    @Override
    public SuspiciousPhoneTransferDTO getSuspiciousPhoneTransferById(Long id) {
        return suspiciousPhoneTransferMapper.toDTO(suspiciousPhoneTransferRepository.findById(id)
                .orElseThrow(() -> new NoSuchTransferException(
                        String.format("SuspiciousPhoneTransfer not found with ID = %d", id))));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.ADD)
    public SuspiciousPhoneTransferDTO addSuspiciousPhoneTransfer(
            SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO) {
        return suspiciousPhoneTransferMapper.toDTO(suspiciousPhoneTransferRepository
                .save(suspiciousPhoneTransferMapper.toEntity(suspiciousPhoneTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.UPDATE)
    public SuspiciousPhoneTransferDTO updateSuspiciousPhoneTransfer(
            SuspiciousPhoneTransferDTO updatedSuspiciousPhoneTransferDTO) {
        this.getSuspiciousPhoneTransferById(updatedSuspiciousPhoneTransferDTO.getId());
        return suspiciousPhoneTransferMapper.toDTO(suspiciousPhoneTransferRepository
                .save(suspiciousPhoneTransferMapper.toEntity(updatedSuspiciousPhoneTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.DELETE)
    public SuspiciousPhoneTransferDTO deleteSuspiciousPhoneTransferById(Long id) {
        SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO = this.getSuspiciousPhoneTransferById(id);
        suspiciousPhoneTransferRepository.deleteById(id);

        return suspiciousPhoneTransferDTO;
    }

}
