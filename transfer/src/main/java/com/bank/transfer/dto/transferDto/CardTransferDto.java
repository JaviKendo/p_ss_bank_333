package com.bank.transfer.dto.transferDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@Schema(title = "Поля CardTransferDto для заполнения")
public class CardTransferDto {
    @Schema(description = "Первичный ключ")
    private Long id;
    @NotNull
    @Min(value = 1, message = "amount должно быть больше 0")
    @Schema(description = "Сумма перевода")
    private BigDecimal amount;
    @Schema(description = "Цель перевода")
    private  String purpose;
    @NotNull
    @Min(value = 1, message = "accountDetailsId должно быть больше 0")
    @Schema(description = "Идентификатор учетной записи")
    private long accountDetailsId;
    @NotNull
    @Min(value = 1, message = "accountNumber должно быть больше 0")
    @Schema(description = "Номер банковской карты")
    private long cardNumber;

    public void setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
}
