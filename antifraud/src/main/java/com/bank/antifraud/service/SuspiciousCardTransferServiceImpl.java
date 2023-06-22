package com.bank.antifraud.service;

import com.bank.antifraud.custom_audit.Auditable;
import com.bank.antifraud.custom_audit.AuditingActionType;
import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.exception_handling.exception.NoSuchTransferException;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService {

    private final SuspiciousCardTransferRepository suspiciousCardTransferRepository;
    private final SuspiciousCardTransferMapper suspiciousCardTransferMapper;

    @Override
    public List<SuspiciousCardTransferDTO> getAllSuspiciousCardTransfers() {
        return suspiciousCardTransferMapper.toDTOList(suspiciousCardTransferRepository.findAll());
    }

    @Override
    public SuspiciousCardTransferDTO getSuspiciousCardTransferById(Long id) {
        return suspiciousCardTransferMapper.toDTO(suspiciousCardTransferRepository.findById(id)
                .orElseThrow(() -> new NoSuchTransferException(
                        String.format("SuspiciousCardTransfer not found with ID = %d", id))));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.ADD)
    public SuspiciousCardTransferDTO addSuspiciousCardTransfer(SuspiciousCardTransferDTO suspiciousCardTransferDTO) {
        return suspiciousCardTransferMapper.toDTO(suspiciousCardTransferRepository
                .save(suspiciousCardTransferMapper.toEntity(suspiciousCardTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.UPDATE)
    public SuspiciousCardTransferDTO updateSuspiciousCardTransfer(
            SuspiciousCardTransferDTO updatedSuspiciousCardTransferDTO) {
        this.getSuspiciousCardTransferById(updatedSuspiciousCardTransferDTO.getId());
        return suspiciousCardTransferMapper.toDTO(suspiciousCardTransferRepository
                .save(suspiciousCardTransferMapper.toEntity(updatedSuspiciousCardTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.DELETE)
    public SuspiciousCardTransferDTO deleteSuspiciousCardTransferById(Long id) {
        SuspiciousCardTransferDTO suspiciousCardTransferDTO = this.getSuspiciousCardTransferById(id);
        suspiciousCardTransferRepository.deleteById(id);
        return suspiciousCardTransferDTO;
    }

}
