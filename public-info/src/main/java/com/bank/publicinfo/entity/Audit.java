package com.bank.publicinfo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill in the field")
    @Size(max = 40, message = "Should not exceed 40 symbols")
    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @NotBlank(message = "Fill in the field")
    @Size(max = 255, message = "Should not exceed 255 symbols")
    @Column(name = "operation_type", nullable = false)
    private String operationType;

    @NotBlank(message = "Fill in the field")
    @Size(max = 255, message = "Should not exceed 255 symbols")
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Size(max = 255, message = "Should not exceed 255 symbols")
    @Column(name = "modified_by")
    private String modifiedBy;

    @NotEmpty(message = "Fill in the field")
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @NotBlank(message = "Fill in the field")
    @Column(name = "entity_json", nullable = false)
    private String entityJson;
}
