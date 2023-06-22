package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "actual_registration")
@Audited
public class ActualRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "country")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String country;

    @Column (name = "region")
    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String region;

    @Column (name = "city")
    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String city;

    @Column (name = "district")
    @Size(max = 160, message = "Максимальная длина должна быть равна 160 знаков")
    private String district;

    @Column (name = "locality")
    @Size(max = 230, message = "Максимальная длина должна быть равна 230 знаков")
    private String locality;

    @Column (name = "street")
    @Size(max = 230, message = "Максимальная длина должна быть равна 230 знаков")
    private String street;

    @Column (name = "house_number")
    @Size(max = 20, message = "Максимальная длина должна быть равна 20 знаков")
    private String houseNumber;

    @Column (name = "house_block")
    @Size(max = 20, message = "Максимальная длина должна быть равна 20 знаков")
    private String houseBlock;

    @Column (name = "flat_number")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    private String flatNumber;

    @Column (name = "index")
    @NotNull(message = "Поле не должно быть пустым")
    private Long index;

    @OneToOne(mappedBy = "actualRegistration")
    @JsonBackReference
    private Profile profile;
}
