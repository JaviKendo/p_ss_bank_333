package com.bank.transfer.mapper;

import com.bank.transfer.dto.transferDto.CardTransferDto;
import com.bank.transfer.entity.transfers.CardTransfer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTransferMapper {
    CardTransfer toEntity(CardTransferDto cardTransferDto);
    CardTransferDto toDto(CardTransfer cardTransfer);
    List<CardTransferDto> toDtoList(List<CardTransfer> cardTransfers);
}
