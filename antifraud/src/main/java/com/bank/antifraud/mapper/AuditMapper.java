package com.bank.antifraud.mapper;

import com.bank.antifraud.dto.AuditDTO;
import com.bank.antifraud.entity.Audit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditDTO toDTO(Audit audit);

    Audit toEntity(AuditDTO auditDTO);

    List<AuditDTO> toDTOList(List<Audit> audit);

}
