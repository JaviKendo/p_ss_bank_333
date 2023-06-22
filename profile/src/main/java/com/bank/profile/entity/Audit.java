package com.bank.profile.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Audit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String entityType;

    @Column(name = "operation_type")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String operationType;

    @Column(name = "created_by")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    @NotBlank(message = "Поле не должно быть пустым")
    private String createdBy;

    @Column(name = "modified_by")
    @Size(max = 40, message = "Максимальная длина должна быть равна 40 знаков")
    private String modifiedBy;

    @Column(name = "created_at")
    @NotNull(message = "Поле не должно быть пустым")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @Column(name = "entity_json")
    @NotBlank(message = "Поле не должно быть пустым")
    private String entityJson;
}
