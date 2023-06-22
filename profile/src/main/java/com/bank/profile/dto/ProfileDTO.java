package com.bank.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {

    private Long id;

    @NotNull(message = "Поле не должно быть пустым")
    private Long phoneNumber;

    @Email(message = "Не является электронной почтой")
    @Size(max = 264, message = "Максимальная длина должна быть равна 264 знака")
    private String email;

    @Size(max = 370, message = "Максимальная длина должна быть равна 370 знаков")
    private String nameOnCard;

    private Long inn;

    private Long snils;

    @NotNull(message = "Поле не должно быть пустым")
    private PassportDTO passport;

    private ActualRegistrationDTO actualRegistration;
}
