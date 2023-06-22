package com.bank.transfer.service.accountTransfer;

import com.bank.transfer.dto.transferDto.AccountTransferDto;

import java.util.List;

public interface AccountTransferService {
    void saveTransfer(AccountTransferDto transferDto);
    void deleteTransferById(Long id);
    AccountTransferDto getTransferById(Long id);
    List<AccountTransferDto> getAllTransfer();
}
