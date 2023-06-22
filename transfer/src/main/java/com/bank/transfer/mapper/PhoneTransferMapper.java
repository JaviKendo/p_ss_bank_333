package com.bank.transfer.mapper;

import com.bank.transfer.dto.transferDto.PhoneTransferDto;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {
    PhoneTransfer toEntity(PhoneTransferDto phoneTransferDto);
    PhoneTransferDto toDto(PhoneTransfer phoneTransfer);
    List<PhoneTransferDto> toDtoList(List<PhoneTransfer> phoneTransfers);
}
