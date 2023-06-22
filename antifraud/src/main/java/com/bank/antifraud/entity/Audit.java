package com.bank.antifraud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "audit", schema = "anti_fraud")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entity_type")
    @NotBlank
    @Size(max = 40)
    private String entityType;

    @Column(name = "operation_type")
    @NotBlank
    private String operationType;

    @Column(name = "created_by", updatable = false)
    @NotBlank
    private String createdBy;

    @Column(name = "modified_by")
    @Nullable
    private String modifiedBy;

    @Column(name = "created_at", updatable = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @Column(name = "modified_at")
    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date modifiedAt;

    @Column(name = "new_entity_json", columnDefinition = "text")
    @Nullable
    private String newEntityJson;

    @Column(name = "entity_json", columnDefinition = "text")
    @NotBlank
    private String entityJson;

}
