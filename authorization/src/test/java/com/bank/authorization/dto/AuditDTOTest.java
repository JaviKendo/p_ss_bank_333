package com.bank.authorization.dto;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditDTOTest {

    @Test
    void auditDTOTest() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuditDTO auditDTO = new AuditDTO(1L, "entity", "operation", "creator", "modifier",
                timestamp, timestamp, "newEntityJSON", "entityJSON");

        auditDTO.setId(2L);
        auditDTO.setEntityType("newEntity");
        auditDTO.setOperationType("newOperation");
        auditDTO.setCreatedBy("newCreator");
        auditDTO.setModifiedBy("newModifier");
        auditDTO.setCreatedAt(timestamp);
        auditDTO.setModifiedAt(timestamp);
        auditDTO.setNewEntityJSON("updatedNewEntityJSON");
        auditDTO.setEntityJSON("updatedEntityJSON");

        assertEquals(2L, auditDTO.getId());
        assertEquals("newEntity", auditDTO.getEntityType());
        assertEquals("newOperation", auditDTO.getOperationType());
        assertEquals("newCreator", auditDTO.getCreatedBy());
        assertEquals("newModifier", auditDTO.getModifiedBy());
        assertEquals(timestamp, auditDTO.getCreatedAt());
        assertEquals(timestamp, auditDTO.getModifiedAt());
        assertEquals("updatedNewEntityJSON", auditDTO.getNewEntityJSON());
        assertEquals("updatedEntityJSON", auditDTO.getEntityJSON());
    }
}
