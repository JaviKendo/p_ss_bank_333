package com.bank.profile.util;

import com.bank.profile.entity.Audit;
import com.bank.profile.entity.Registration;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;


import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class AuditListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        AuditQuery auditQuery = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(Registration.class, false, true);
        List<Object[]> resultList = auditQuery.getResultList();
        if (resultList.size() == 0) {
            RevEntity revEntity = (RevEntity) revisionEntity;
            revEntity.setUsername("Username");
            return;
        }
        Object[] resultArr = resultList.get(resultList.size() - 1);
        Registration registration = (Registration) resultArr[0];
        RevEntity revEntity = (RevEntity) resultArr[1];
        RevisionType revisionType = (RevisionType) resultArr[2];
        revEntity.setUsername("Username");

        Audit audit = new Audit();

        ZonedDateTime zonedDateTimeOfRevision = ZonedDateTime.ofInstant(Instant.ofEpochMilli(revEntity.getTimestamp()),
                ZoneId.systemDefault());

        audit.setEntityType(registration.getClass().getSimpleName());
        audit.setOperationType(revisionType.toString());
        if (revisionType.toString().equals("MOD")) {
            audit.setNewEntityJson(registration.toString());
            audit.setModifiedAt(zonedDateTimeOfRevision.toLocalDateTime());
            audit.setModifiedBy(revEntity.getUsername());
            audit.setEntityJson(resultList.get(resultList.size() - 2)[0].toString());
        } else {
            audit.setEntityJson(registration.toString());
        }
        audit.setCreatedBy(revEntity.getUsername());
        audit.setCreatedAt(zonedDateTimeOfRevision.toLocalDateTime());
        entityManager.persist(audit);
    }
}
