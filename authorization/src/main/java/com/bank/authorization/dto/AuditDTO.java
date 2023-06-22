package com.bank.authorization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuditDTO", description = "DTO объект для Audit")
public class AuditDTO {
    private Long id;
    private String entityType;
    private String operationType;
    private String createdBy;
    private String modifiedBy;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private String newEntityJSON;
    private String entityJSON;
}
