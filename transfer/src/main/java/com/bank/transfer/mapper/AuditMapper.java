package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.Audit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    Audit toEntity(AuditDto accountTransferDto);
    AuditDto toDto(Audit accountTransfer);
    List<AuditDto> toDtoList(List<Audit> cardTransfers);
}
