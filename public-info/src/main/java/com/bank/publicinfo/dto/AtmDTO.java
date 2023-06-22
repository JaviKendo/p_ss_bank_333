package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtmDTO {
    @NotBlank(message = "Fill in the field")
    @Size(max = 370, message = "Should not exceed 370 symbols")
    private String address;

    private Time startOfWork;

    private Time endOfWork;

    @NotEmpty
    private Boolean allHours;

    private Branch atm;
}
