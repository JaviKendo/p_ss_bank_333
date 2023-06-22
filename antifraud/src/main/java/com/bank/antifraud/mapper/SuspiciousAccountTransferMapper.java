package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.entity.transfer.SuspiciousAccountTransfer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SuspiciousAccountTransferMapper {

    SuspiciousAccountTransferDTO toDTO(SuspiciousAccountTransfer suspiciousAccountTransfer);
    SuspiciousAccountTransfer toEntity(SuspiciousAccountTransferDTO suspiciousAccountTransferDTO);
    List<SuspiciousAccountTransferDTO> toDTOList(List<SuspiciousAccountTransfer> suspiciousAccountTransferList);

}

