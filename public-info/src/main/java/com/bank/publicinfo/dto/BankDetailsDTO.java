package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDTO {
    @NotEmpty(message = "Fill in the field")
    private Long bik;

    @NotEmpty(message = "Fill in the field")
    private Long inn;

    @NotEmpty(message = "Fill in the field")
    private Long kpp;

    @NotEmpty(message = "Fill in the field")
    private Integer corAccount;

    @NotBlank(message = "Fill in the field")
    @Size(max = 180, message = "Should not exceed 180 symbols")
    private String city;

    @NotBlank(message = "Fill in the field")
    @Size(max = 15, message = "Should not exceed 15 symbols")
    private String jointStockCompany;

    @NotBlank(message = "Fill in the field")
    @Size(max = 80, message = "Should not exceed 80 symbols")
    private String name;
}
