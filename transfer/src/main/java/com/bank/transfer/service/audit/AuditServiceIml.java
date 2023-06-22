package com.bank.transfer.service.audit;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.repository.AuditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log(topic = "AuditService")
@AllArgsConstructor
public class AuditServiceIml implements AuditService {
    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;
    @Override
    @Transactional
    public void saveAudit(AuditDto transferDto) {
        auditRepository.save(auditMapper.toEntity(transferDto));
        log.info(transferDto + " сохранен/обновлен в базе данных");
    }

    @Override
    @Transactional
    public List<AuditDto> getAllAudit() {
        return auditMapper.toDtoList(auditRepository.findAll());
    }
}
