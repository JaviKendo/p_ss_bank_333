package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditTable(value = "bank_details_aud", schema = "public")
@Entity
@Table(name = "bank_details")
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Fill in the field")
    @Min(1)
    @Column(name = "bik", unique = true, nullable = false)
    private Long bik;

    @NotEmpty(message = "Fill in the field")
    @Min(1)
    @Column(name = "inn", unique = true, nullable = false)
    private Long inn;

    @NotEmpty(message = "Fill in the field")
    @Min(1)
    @Column(name = "kpp", unique = true, nullable = false)
    private Long kpp;

    @NotEmpty(message = "Fill in the field")
    @Min(1)
    @Column(name = "cor_account", unique = true, nullable = false)
    private Integer corAccount;

    @NotBlank(message = "Fill in the field")
    @Size(max = 180, message = "Should not exceed 180 symbols")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Fill in the field")
    @Size(max = 15, message = "Should not exceed 15 symbols")
    @Column(name = "joint_stock_company", nullable = false)
    private String jointStockCompany;

    @NotBlank(message = "Fill in the field")
    @Size(max = 80, message = "Should not exceed 80 symbols")
    @Column(name = "name", nullable = false)
    private String name;
}
