package com.bank.antifraud.dto;

import com.bank.antifraud.custom_audit.ObservableDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Information about suspicious phones transfers")
public class SuspiciousPhoneTransferDTO implements ObservableDTO {

    @Nullable
    private Long id;

    @NotNull
    @Min(value = 1)
    private Long phoneTransferId;

    @NotNull
    private Boolean isBlocked;

    @NotNull
    private Boolean isSuspicious;

    @Nullable
    private String blockedReason;

    @NotBlank
    private String suspiciousReason;

}
