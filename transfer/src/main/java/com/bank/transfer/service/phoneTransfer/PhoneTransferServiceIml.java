package com.bank.transfer.service.phoneTransfer;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.dto.transferDto.PhoneTransferDto;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Log(topic = "PhoneTransferService")
@AllArgsConstructor
public class PhoneTransferServiceIml implements PhoneTransferService {
    private final PhoneTransferRepository phoneTransferRepository;
    private final PhoneTransferMapper phoneTransferMapper;
    private final AuditTransferStorage auditTransferStorage;

    @Override
    @Transactional
    public void saveTransfer(PhoneTransferDto transferDto) {
        if (null != transferDto.getId()) {
            auditTransferStorage.setPhoneTransfer(
                    phoneTransferMapper.toEntity(getTransferById(transferDto.getId())));
        } else {
            auditTransferStorage.setPhoneTransfer(phoneTransferMapper.toEntity(transferDto));
        }

        phoneTransferRepository.save(phoneTransferMapper.toEntity(transferDto));
        log.info(transferDto + " сохранен/обновлен в базе данных");
    }

    @Override
    @Transactional
    public void deleteTransferById(Long id) {
        auditTransferStorage.setPhoneTransfer(phoneTransferMapper.toEntity(getTransferById(id)));
        phoneTransferRepository.deleteById(id);
        log.info("PhoneTransfer с id = " + id + " удален из базы данных");
    }

    @Override
    @Transactional
    public PhoneTransferDto getTransferById(Long id) {
        return phoneTransferMapper.toDto(phoneTransferRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public List<PhoneTransferDto> getAllTransfer() {
        return phoneTransferMapper.toDtoList(phoneTransferRepository.findAll());
    }
}
