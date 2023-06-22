package com.bank.profile.mappers;

import com.bank.profile.dto.AuditDTO;
import com.bank.profile.entity.Audit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditDTO toDTO(Audit audit);
    Audit toEntity(AuditDTO auditDTO);
}
