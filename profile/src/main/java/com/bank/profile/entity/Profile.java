package com.bank.profile.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profile")
@Audited
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    @NotNull(message = "Поле не должно быть пустым")
    private Long phoneNumber;

    @Column(name = "email")
    @Email(message = "Не является электронной почтой")
    @Size(max = 264, message = "Максимальная длина должна быть равна 264 знака")
    private String email;

    @Column(name = "name_on_card")
    @Size(max = 370, message = "Максимальная длина должна быть равна 370 знаков")
    private String nameOnCard;

    @Column(name = "inn", unique = true)
    private Long inn;

    @Column(name = "snils", unique = true)
    private Long snils;

    @OneToOne
    @NotNull(message = "Поле не должно быть пустым")
    @JoinColumn(name = "passport_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Passport passport;

    @OneToOne
    @JoinColumn(name = "actual_registration_id", referencedColumnName = "id")
    @JsonManagedReference
    private ActualRegistration actualRegistration;

    @OneToMany(mappedBy = "profileId")
    @JsonManagedReference
    private List<AccountDetailsId> accountDetails;
}
