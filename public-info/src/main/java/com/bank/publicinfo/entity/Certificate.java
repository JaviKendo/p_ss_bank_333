package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditTable(value = "certificate_aud", schema = "public")
@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Fill in the field")
    @Column(name = "photo", nullable = false)
    private Byte photo;

    @ManyToOne
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id", nullable = false)
    private BankDetails certificate;
}
