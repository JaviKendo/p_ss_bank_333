package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseDTO {
    @NotEmpty(message = "Fill in the field")
    private Byte photo;

    private BankDetails license;
}
