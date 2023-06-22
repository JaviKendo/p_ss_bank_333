package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import com.bank.transfer.service.audit.AuditService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class PhoneTransferListenerTest {

    private PhoneTransferListener phoneTransferListener;
    @MockBean
    private AuditService auditService;
    @MockBean
    private AuditTransferStorage auditTransferStorage;
    @MockBean
    private BeanUtil beanUtil;

    @BeforeEach
    void setTransferListener() {
        this.phoneTransferListener = new PhoneTransferListener();
    }
    @Test
    void postCreate() {
        PhoneTransfer phoneTransfer = PhoneTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .phoneNumber(5)
                .build();
        AuditDto auditDto = AuditDto.builder()
                .entityType(phoneTransfer.getClass().getSimpleName())
                .operationType("Создан")
                .entityJson(phoneTransfer.toString())
                .build();
        phoneTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        phoneTransferListener.postCreate(phoneTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }


    @Test
    void postUpdate() {
        PhoneTransfer newPhoneTransfer = PhoneTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .phoneNumber(5)
                .build();

        PhoneTransfer oldPhoneTransfer = PhoneTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .phoneNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldPhoneTransfer.toString())
                .operationType("Обновлен")
                .newEntityJson(newPhoneTransfer.toString())
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldPhoneTransfer.toString()).build());

        phoneTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(phoneTransferListener.getOldPhoneTransfer()).thenReturn(oldPhoneTransfer);

        phoneTransferListener.postUpdate(newPhoneTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void postRemove() {
        PhoneTransfer oldPhoneTransfer = PhoneTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .phoneNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldPhoneTransfer.toString())
                .operationType("Удален")
                .newEntityJson(null)
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldPhoneTransfer.toString()).build());

        phoneTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(phoneTransferListener.getOldPhoneTransfer()).thenReturn(oldPhoneTransfer);

        phoneTransferListener.postRemove(new PhoneTransfer());
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void getOldPhoneTransfer() {
        PhoneTransfer oldPhoneTransfer = PhoneTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .phoneNumber(55)
                .build();
        phoneTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);
        Mockito.when(auditTransferStorage.getPhoneTransfer()).thenReturn(oldPhoneTransfer);
        Assertions.assertEquals(phoneTransferListener.getOldPhoneTransfer(), oldPhoneTransfer);
    }
}