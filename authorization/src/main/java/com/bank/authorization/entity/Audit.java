package com.bank.authorization.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "Audit")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(name = "Audit", description = "Сущность, объекты которой сохраняют в БД все CRUD операции с основными сущностями.")
public class Audit {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(com.bank.authorization.entity.Audit.class);
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "entity_type")
    @Size(min = 2, max = 40)
    private String entityType;
    @Column(name = "operation_type")
    @Size(min = 2, max = 255)
    private String operationType;
    @Column(name = "created_by")
    @Size(min = 2, max = 255)
    private String createdBy;
    @Column(name = "modified_by")
    @Size(min = 2, max = 255)
    private String modifiedBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Column(name = "new_entity_json")
    private String newEntityJson;
    @Column(name = "entity_json")
    private String entityJson;
    public Audit(long l, String s, String create, String s1, String system, Timestamp from) {
    }
}
