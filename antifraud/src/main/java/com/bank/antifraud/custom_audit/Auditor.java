package com.bank.antifraud.custom_audit;

import com.bank.antifraud.entity.Audit;

public interface Auditor {

    <U, T> void doAudit(Object[] objects, U timeCreatedOrUpdatedObject, T object);

    <T> Audit getCompletedAudit(Object[] objects, T timeCreatedOrUpdatedObject);

}
