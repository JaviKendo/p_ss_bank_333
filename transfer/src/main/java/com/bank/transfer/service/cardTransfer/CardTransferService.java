package com.bank.transfer.service.cardTransfer;

import com.bank.transfer.dto.transferDto.CardTransferDto;

import java.util.List;

public interface CardTransferService {
    void saveTransfer(CardTransferDto transferDto);
    void deleteTransferById(Long id);
    CardTransferDto getTransferById(Long id);
    List<CardTransferDto> getAllTransfer();
}
