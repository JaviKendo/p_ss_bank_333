package com.bank.antifraud.util;

import com.bank.antifraud.custom_audit.ObservableEntity;

public interface SuspiciousTransferTypeChecker {

    <T> ObservableEntity checkAndConvertEntityType(T objectForConvert, Class<?> uncheckedType);

}
