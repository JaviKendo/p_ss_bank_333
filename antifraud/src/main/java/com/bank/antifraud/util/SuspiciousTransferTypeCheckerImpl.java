package com.bank.antifraud.util;
import com.bank.antifraud.custom_audit.ObservableEntity;
import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapper;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SuspiciousTransferTypeCheckerImpl implements SuspiciousTransferTypeChecker {

    private final SuspiciousAccountTransferMapper suspiciousAccountTransferMapper;
    private final SuspiciousCardTransferMapper suspiciousCardTransferMapper;
    private final SuspiciousPhoneTransferMapper suspiciousPhoneTransferMapper;

    @Override
    @Nullable
    public <T> ObservableEntity checkAndConvertEntityType(T objectForConvertToEntity, Class<?> uncheckedType) {
        ObservableEntity checkedType = null;

        if (uncheckedType == SuspiciousAccountTransferDTO.class) {
            checkedType = suspiciousAccountTransferMapper
                    .toEntity((SuspiciousAccountTransferDTO) objectForConvertToEntity);
        }
        if (uncheckedType == SuspiciousCardTransferDTO.class) {
            checkedType = suspiciousCardTransferMapper
                    .toEntity((SuspiciousCardTransferDTO) objectForConvertToEntity);
        }
        if (uncheckedType == SuspiciousPhoneTransferDTO.class) {
            checkedType = suspiciousPhoneTransferMapper
                    .toEntity((SuspiciousPhoneTransferDTO) objectForConvertToEntity);
        }

        return checkedType;
    }

}
