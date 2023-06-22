package com.bank.profile.util;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;

@Getter
@Setter
@Entity
@RevisionEntity(AuditListener.class)
@Table(name = "revinfo")
public class RevEntity {

    @Id
    @RevisionNumber
    @GeneratedValue
    private int id;

    @RevisionTimestamp
    private long timestamp;

    private String username;

}