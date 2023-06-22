package com.bank.transfer.mapper;

import com.bank.transfer.dto.transferDto.AccountTransferDto;
import com.bank.transfer.entity.transfers.AccountTransfer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {
    AccountTransfer toEntity(AccountTransferDto accountTransferDto);
    AccountTransferDto toDto(AccountTransfer accountTransfer);
    List<AccountTransferDto> toDtoList(List<AccountTransfer> accountTransfers);
}
