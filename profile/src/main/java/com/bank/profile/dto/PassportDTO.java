package com.bank.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PassportDTO {

    private Long id;

    @NotNull(message = "Поле не должно быть пустым")
    private Integer series;

    @NotNull(message = "Поле не должно быть пустым")
    private Long number;

    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String firstName;

    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    private String middleName;

    @Size(max = 3, message = "Максимальная длина должна быть равна 3 знака")
    @NotBlank(message = "Поле не должно быть пустым")
    private String gender;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @NotNull(message = "Поле не должно быть пустым")
    private Date birthDate;

    @Size(max = 480, message = "Максимальная длина должна быть равна 480 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String birthPlace;

    @NotBlank(message = "Поле не должно быть пустым")
    private String issuedBy;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @NotNull(message = "Поле не должно быть пустым")
    private Date dateOfIssue;

    @NotNull(message = "Поле не должно быть пустым")
    private Integer divisionCode;

    private Date expirationDate;

    @NotNull(message = "Поле не должно быть пустым")
    private RegistrationDTO registration;
}
