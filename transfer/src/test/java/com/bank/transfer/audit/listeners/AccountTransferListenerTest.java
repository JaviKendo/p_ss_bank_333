package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.AccountTransfer;
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
class AccountTransferListenerTest {

    private AccountTransferListener accountTransferListener;
    @MockBean
    private AuditService auditService;
    @MockBean
    private AuditTransferStorage auditTransferStorage;
    @MockBean
    private BeanUtil beanUtil;

    @BeforeEach
    void setTransferListener() {
        this.accountTransferListener = new AccountTransferListener();
    }

    @Test
    void postCreate() {
        AccountTransfer accountTransfer = AccountTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .accountNumber(5)
                .build();
        AuditDto auditDto = AuditDto.builder()
                .entityType(accountTransfer.getClass().getSimpleName())
                .operationType("Создан")
                .entityJson(accountTransfer.toString())
                .build();
        accountTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        accountTransferListener.postCreate(accountTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }


    @Test
    void postUpdate() {
        AccountTransfer newAccountTransfer = AccountTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .accountNumber(5)
                .build();

        AccountTransfer oldAccountTransfer = AccountTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .accountNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldAccountTransfer.toString())
                .operationType("Обновлен")
                .newEntityJson(newAccountTransfer.toString())
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldAccountTransfer.toString()).build());

        accountTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(accountTransferListener.getOldAccountTransfer()).thenReturn(oldAccountTransfer);

        accountTransferListener.postUpdate(newAccountTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void postRemove() {
        AccountTransfer oldAccountTransfer = AccountTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .accountNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldAccountTransfer.toString())
                .operationType("Удален")
                .newEntityJson(null)
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldAccountTransfer.toString()).build());

        accountTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(accountTransferListener.getOldAccountTransfer()).thenReturn(oldAccountTransfer);

        accountTransferListener.postRemove(new AccountTransfer());
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void getOldAccountTransfer() {
        AccountTransfer oldAccountTransfer = AccountTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .accountNumber(55)
                .build();
        accountTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);
        Mockito.when(auditTransferStorage.getAccountTransfer()).thenReturn(oldAccountTransfer);
        Assertions.assertEquals(accountTransferListener.getOldAccountTransfer(), oldAccountTransfer);
    }
}