package com.bank.antifraud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Information about audits")
public class AuditDTO {

    @Nullable
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String entityType;

    @NotBlank
    private String operationType;

    @NotBlank
    private String createdBy;

    @Nullable
    private String modifiedBy;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date modifiedAt;

    @Nullable
    private String newEntityJson;

    @NotBlank
    private String entityJson;

}
