package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    @NotBlank(message = "Fill in the field")
    @Size(max = 370, message = "Should not exceed 370 symbols")
    private String address;

    @NotEmpty(message = "Fill in the field")
    @Pattern(regexp = "^[0-9]+$")
    private Long phoneNumber;

    @NotBlank(message = "Fill in the field")
    @Size(max = 250, message = "Should not exceed 250 symbols")
    private String city;

    @NotEmpty(message = "Fill in the field")
    private Time startOfWork;

    @NotEmpty(message = "Fill in the field")
    private Time endOfWork;
}
