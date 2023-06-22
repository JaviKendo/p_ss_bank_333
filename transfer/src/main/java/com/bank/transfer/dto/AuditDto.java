package com.bank.transfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@Schema(title = "Поля AuditDto для заполнения")
public class AuditDto {
    @Schema(description = "Первичный ключ")
    private Long id;
    @NotEmpty
    @NotNull
    @Size(max = 40, message = "Максимальная длина строки entityType 40 символов")
    @Schema(description = "Вид трансфера")
    private String entityType;
    @NotEmpty
    @NotNull
    @Size(max = 255, message = "Максимальная длина строки operationType 255 символов")
    @Schema(description = "Вид операции")
    private String operationType;
    @NotEmpty
    @NotNull
    @Size(max = 255, message = "Максимальная длина строки createdBy 255 символов")
    @Schema(description = "Создатель операции")
    private String createdBy;
    @Size(max = 255, message = "Максимальная длина строки modifiedBy 255 символов")
    @Schema(description = "Кем операция изменена")
    private String modifiedBy;
    @Schema(description = "Дата создания операции")
    private Date createdAt;
    @Schema(description = "Дата модификации операции")
    private Date modifiedAt;
    @NotEmpty
    @NotNull
    @Schema(description = "Операция в виде JSON при создании")
    private String entityJson;
    @Schema(description = "Операция в виде JSON после модификации")
    private String newEntityJson;
}
