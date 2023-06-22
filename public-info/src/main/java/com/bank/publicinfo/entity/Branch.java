package com.bank.publicinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditTable(value = "branch_aud", schema = "public")
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill in the field")
    @Size(max = 370, message = "Should not exceed 370 symbols")
    @Column(name = "address", nullable = false)
    private String address;

    @NotEmpty(message = "Fill in the field")
    @Pattern(regexp = "^[0-9]+$")
    @Column(name = "phone_number", unique = true, nullable = false)
    private Long phoneNumber;

    @NotBlank(message = "Fill in the field")
    @Size(max = 250, message = "Should not exceed 250 symbols")
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "Fill in the field")
    @Column(name = "start_of_work", nullable = false)
    private Time startOfWork;

    @NotEmpty(message = "Fill in the field")
    @Column(name = "end_of_work", nullable = false)
    private Time endOfWork;
}
