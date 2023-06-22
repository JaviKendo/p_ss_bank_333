package com.bank.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transferAuditId;

    private Long profileAuditId;

    private Long accountAuditId;

    private Long antiFraudAuditId;

    private Long publicBankInfoAuditId;

    private Long authorizationAuditId;
}
