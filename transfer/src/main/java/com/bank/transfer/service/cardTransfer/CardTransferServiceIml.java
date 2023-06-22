package com.bank.transfer.service.cardTransfer;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.dto.transferDto.CardTransferDto;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Log(topic = "CardTransferService")
@AllArgsConstructor
public class CardTransferServiceIml implements CardTransferService {
    private final CardTransferRepository cardTransferRepository;
    private final CardTransferMapper cardTransferMapper;
    private final AuditTransferStorage auditTransferStorage;

    @Override
    @Transactional
    public void saveTransfer(CardTransferDto transferDto) {
        if (null != transferDto.getId()) {
            auditTransferStorage.setCardTransfer(
                    cardTransferMapper.toEntity(getTransferById(transferDto.getId())));
        } else {
            auditTransferStorage.setCardTransfer(cardTransferMapper.toEntity(transferDto));
        }
        cardTransferRepository.save(cardTransferMapper.toEntity(transferDto));
        log.info(transferDto + " сохранен/обновлен в базе данных");
    }

    @Override
    @Transactional
    public void deleteTransferById(Long id) {
        auditTransferStorage.setCardTransfer(cardTransferMapper.toEntity(getTransferById(id)));
        cardTransferRepository.deleteById(id);
        log.info("CardTransfer с id = " + id + " удален из базы данных");
    }

    @Override
    @Transactional
    public CardTransferDto getTransferById(Long id) {
        return cardTransferMapper.toDto(cardTransferRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public List<CardTransferDto> getAllTransfer() {
        return cardTransferMapper.toDtoList(cardTransferRepository.findAll());
    }
}
