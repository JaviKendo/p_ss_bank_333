package com.bank.history.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history", schema = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "transfer_audit_id", nullable = true)
    private Long transferAuditId;
    @Column(name = "profile_audit_id", nullable = true)
    private Long profileAuditId;
    @Column(name = "account_audit_id", nullable = true)
    private Long accountAuditId;
    @Column(name = "anti_fraud_audit_id", nullable = true)
    private Long antiFraudAuditId;
    @Column(name = "public_bank_info_audit_id", nullable = true)
    private Long publicBankInfoAuditId;
    @Column(name = "authorization_audit_id", nullable = true)
    private Long authorizationAuditId;

}
