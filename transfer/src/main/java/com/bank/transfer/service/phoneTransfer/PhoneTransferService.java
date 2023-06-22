package com.bank.transfer.service.phoneTransfer;

import com.bank.transfer.dto.transferDto.PhoneTransferDto;

import java.util.List;

public interface PhoneTransferService {
    void saveTransfer(PhoneTransferDto transferDto);
    void deleteTransferById(Long id);
    PhoneTransferDto getTransferById(Long id);
    List<PhoneTransferDto> getAllTransfer();
}
