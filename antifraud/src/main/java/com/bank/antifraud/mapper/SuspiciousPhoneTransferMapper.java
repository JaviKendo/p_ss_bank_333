package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.entity.transfer.SuspiciousPhoneTransfer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SuspiciousPhoneTransferMapper {
    SuspiciousPhoneTransferDTO toDTO(SuspiciousPhoneTransfer suspiciousPhoneTransfer);
    SuspiciousPhoneTransfer toEntity(SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO);
    List<SuspiciousPhoneTransferDTO> toDTOList(List<SuspiciousPhoneTransfer> suspiciousPhoneTransferList);
}
