package com.bank.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO {

    private Long id;

    @Size(max = 166, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String country;

    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String region;

    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String city;

    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String district;

    @Size(max = 230, message = "Максимальная длина должна быть равна 230 знаков")
    private String locality;

    @Size(max = 230, message = "Максимальная длина должна быть равна 230 знаков")
    private String street;

    @Size(max = 20, message = "Максимальная длина должна быть равна 20 знаков")
    private String houseNumber;

    @Size(max = 20, message = "Максимальная длина должна быть равна 20 знаков")
    private String houseBlock;

    @Size(max = 40, message = "Максимальная длина должна быть равна 20 знаков")
    private String flatNumber;

    @NotNull(message = "Поле не должно быть пустым")
    private Long index;
}
