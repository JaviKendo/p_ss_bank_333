package com.bank.antifraud.service;

import com.bank.antifraud.custom_audit.Auditable;
import com.bank.antifraud.custom_audit.AuditingActionType;
import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.exception_handling.exception.NoSuchTransferException;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousAccountTransferServiceImpl implements SuspiciousAccountTransferService {

    private final SuspiciousAccountTransferRepository suspiciousAccountTransferRepository;
    private final SuspiciousAccountTransferMapper suspiciousAccountTransferMapper;

    @Override
    public List<SuspiciousAccountTransferDTO> getAllSuspiciousAccountTransfers() {
        return suspiciousAccountTransferMapper.toDTOList(suspiciousAccountTransferRepository.findAll());
    }

    @Override
    public SuspiciousAccountTransferDTO getSuspiciousAccountTransferById(Long id) {
        return suspiciousAccountTransferMapper.toDTO(suspiciousAccountTransferRepository.findById(id)
                .orElseThrow(() -> {
                    final String exceptionMessage =
                            String.format("SuspiciousAccountTransfer not found with ID = %d", id);
                    final String logMessage = String.format("The request failed. " +
                            "NoSuchTransferException was thrown. %s", exceptionMessage);
                    final NoSuchTransferException exception = new NoSuchTransferException(exceptionMessage);
                    log.warn(logMessage);
                    log.debug(logMessage, exception);

                    return exception;
                }));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.ADD)
    public SuspiciousAccountTransferDTO addSuspiciousAccountTransfer(
            SuspiciousAccountTransferDTO suspiciousAccountTransferDTO) {
        return suspiciousAccountTransferMapper.toDTO(suspiciousAccountTransferRepository
                .save(suspiciousAccountTransferMapper.toEntity(suspiciousAccountTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.UPDATE)
    public SuspiciousAccountTransferDTO updateSuspiciousAccountTransfer(
            SuspiciousAccountTransferDTO updatedSuspiciousAccountTransferDTO) {
        this.getSuspiciousAccountTransferById(updatedSuspiciousAccountTransferDTO.getId());
        return suspiciousAccountTransferMapper.toDTO(suspiciousAccountTransferRepository
                .save(suspiciousAccountTransferMapper.toEntity(updatedSuspiciousAccountTransferDTO)));
    }

    @Override
    @Transactional
    @Auditable(actionType = AuditingActionType.DELETE)
    public SuspiciousAccountTransferDTO deleteSuspiciousAccountTransferById(Long id) {
        SuspiciousAccountTransferDTO suspiciousAccountTransferDTO = this.getSuspiciousAccountTransferById(id);
        suspiciousAccountTransferRepository.deleteById(id);
        return suspiciousAccountTransferDTO;
    }

}
