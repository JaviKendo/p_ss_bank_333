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
import javax.validation.constraints.Size;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditTable(value = "atm_aud", schema = "public")
@Entity
@Table(name = "atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill in the field")
    @Size(max = 370, message = "Should not exceed 370 symbols")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "start_of_work")
    private Time startOfWork;

    @Column(name = "end_of_work")
    private Time endOfWork;

    @NotEmpty
    @Column(name = "all_hours", nullable = false)
    private Boolean allHours;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch atm;
}
