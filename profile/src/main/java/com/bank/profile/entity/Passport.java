package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@Audited
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Integer series;

    @Column(name = "number", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Long number;

    @Column(name = "last_name", nullable = false)
    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String firstName;

    @Column(name = "middle_name")
    @Size(max = 255, message = "Максимальная длина должна быть равна 255 знаков")
    private String middleName;

    @Column(name = "gender", nullable = false)
    @Size(max = 3, message = "Максимальная длина должна быть равна 3 знака")
    @NotBlank(message = "Поле не должно быть пустым")
    private String gender;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Date birthDate;

    @Column(name = "birth_place", nullable = false)
    @Size(max = 480, message = "Максимальная длина должна быть равна 480 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String birthPlace;

    @Column(name = "issued_by", nullable = false)
    @NotBlank(message = "Поле не должно быть пустым")
    private String issuedBy;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "date_of_issue", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Date dateOfIssue;

    @Column(name = "division_code", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Integer divisionCode;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    @NotNull(message = "Поле не должно быть пустым")
    @JsonManagedReference
    private Registration registration;

    @OneToOne(mappedBy = "passport")
    @JsonBackReference
    private Profile profile;
}
