package com.bank.antifraud.entity.transfer;

import com.bank.antifraud.custom_audit.ObservableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "suspicious_phone_transfers", schema = "anti_fraud")
public class SuspiciousPhoneTransfer implements ObservableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_transfer_id", unique = true)
    @NotNull
    @Min(value = 1)
    private Long phoneTransferId;

    @Column(name = "is_blocked")
    @NotNull
    private Boolean isBlocked;

    @Column(name = "is_suspicious")
    @NotNull
    private Boolean isSuspicious;

    @Column(name = "blocked_reason", columnDefinition = "text")
    @Nullable
    private String blockedReason;

    @Column(name = "suspicious_reason", columnDefinition = "text")
    @NotBlank
    private String suspiciousReason;

}
