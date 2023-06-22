package com.bank.history.util;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.exceptions.HistoryErrorResponse;
import com.bank.history.exceptions.HistoryNotFoundException;
import com.bank.history.models.History;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {HistoryErrorResponse.class}, componentModel = "spring")
public interface HistoryMapper {

    History toEntity(HistoryDTO historyDTO)throws HistoryNotFoundException;

    HistoryDTO toHistoryDTO(History history)throws HistoryNotFoundException;

    default void updateEntityFromDto(HistoryDTO historyDTO, History history) {
        history.setTransferAuditId(historyDTO.getTransferAuditId());
        history.setProfileAuditId(historyDTO.getProfileAuditId());
        history.setAccountAuditId(historyDTO.getAccountAuditId());
        history.setAntiFraudAuditId(historyDTO.getAntiFraudAuditId());
        history.setPublicBankInfoAuditId(historyDTO.getPublicBankInfoAuditId());
        history.setAuthorizationAuditId(historyDTO.getAuthorizationAuditId());
    }

    List<HistoryDTO> toHistoryListDTO(List<History> historyList);
}
