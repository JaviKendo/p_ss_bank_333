package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    @NotBlank(message = "Fill in the field")
    @Size(max = 40, message = "Should not exceed 40 symbols")
    private String entityType;

    @NotBlank(message = "Fill in the field")
    @Size(max = 255, message = "Should not exceed 255 symbols")
    private String operationType;

    @NotBlank(message = "Fill in the field")
    @Size(max = 255, message = "Should not exceed 255 symbols")
    private String createdBy;

    @Size(max = 255, message = "Should not exceed 255 symbols")
    private String modifiedBy;

    @NotEmpty(message = "Fill in the field")
    private Timestamp createdAt;

    private Timestamp modifiedAt;

    private String newEntityJson;

    @NotBlank(message = "Fill in the field")
    private String entityJson;
}
