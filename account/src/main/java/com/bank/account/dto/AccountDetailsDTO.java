package com.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
/**
 * DTO (Data Transfer Object) класс, представляющий информацию о деталях банковского счета.
 * Используется для передачи данных между клиентом и сервером.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDTO {
    private Long id;
    @NotNull
    @Positive
    private Long passportId;
    @NotNull
    @Positive
    private Long accountNumber;
    @NotNull
    @Positive
    private Long bankDetailsId;
    @NotNull
    @Positive
    private BigInteger money;
    @NotNull
    private Boolean negativeBalance;
    @NotNull
    @Positive
    private Long profileId;
}


