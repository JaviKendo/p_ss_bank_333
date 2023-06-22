package com.bank.antifraud.service;

import com.bank.antifraud.dto.AuditDTO;

import java.util.List;

public interface AuditService {

    List<AuditDTO> getAllAudits();

    AuditDTO getAuditById(Long id);

    <U, T> void addAudit(Object[] objects, U timeCreatedOrUpdatedObject, T object);

}
