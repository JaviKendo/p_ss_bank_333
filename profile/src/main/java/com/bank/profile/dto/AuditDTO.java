package com.bank.profile.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AuditDTO {

    private Long id;

    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String entityType;

    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String operationType;

    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String createdBy;

    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    private String modifiedBy;

    @NotEmpty(message = "Поле не должно быть пустым")
    private Date createdAt;

    private Date modifiedAt;

    private String newEntityJson;

    @NotBlank(message = "Поле не должно быть пустым")
    private String entityJson;
}
