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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account_details_id")
@Audited
public class AccountDetailsId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    @NotNull(message = "Поле не должно быть пустым")
    private Long accountId;

    @ManyToOne
    @NotNull(message = "Поле не должно быть пустым")
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonBackReference
    private Profile profileId;
}
