package com.bank.transfer.audit.listeners;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.audit.BeanUtil;
import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.transfers.CardTransfer;
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
class CardTransferListenerTest {

    private CardTransferListener cardTransferListener;
    @MockBean
    private AuditService auditService;
    @MockBean
    private AuditTransferStorage auditTransferStorage;
    @MockBean
    private BeanUtil beanUtil;

    @BeforeEach
    void setTransferListener() {
        this.cardTransferListener = new CardTransferListener();
    }
    @Test
    void postCreate() {
        CardTransfer cardTransfer = CardTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .cardNumber(5)
                .build();
        AuditDto auditDto = AuditDto.builder()
                .entityType(cardTransfer.getClass().getSimpleName())
                .operationType("Создан")
                .entityJson(cardTransfer.toString())
                .build();
        cardTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        cardTransferListener.postCreate(cardTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }


    @Test
    void postUpdate() {
        CardTransfer newCardTransfer = CardTransfer.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2))
                .purpose("3")
                .accountDetailsId(4)
                .cardNumber(5)
                .build();

        CardTransfer oldCardTransfer = CardTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .cardNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldCardTransfer.toString())
                .operationType("Обновлен")
                .newEntityJson(newCardTransfer.toString())
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldCardTransfer.toString()).build());

        cardTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(cardTransferListener.getOldCardTransfer()).thenReturn(oldCardTransfer);

        cardTransferListener.postUpdate(newCardTransfer);
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void postRemove() {
        CardTransfer oldCardTransfer = CardTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .cardNumber(55)
                .build();

        AuditDto auditDto = AuditDto.builder()
                .id(2L)
                .entityJson(oldCardTransfer.toString())
                .operationType("Удален")
                .newEntityJson(null)
                .build();

        List<AuditDto> auditDtos = List.of(AuditDto.builder().id(1L).entityJson("21").build(),
                AuditDto.builder().id(2L).entityJson(oldCardTransfer.toString()).build());

        cardTransferListener.setBeanUtil(beanUtil);

        Mockito.when(beanUtil.getBeans(AuditService.class)).thenReturn(auditService);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);

        Mockito.when(auditService.getAllAudit()).thenReturn(auditDtos);

        Mockito.when(cardTransferListener.getOldCardTransfer()).thenReturn(oldCardTransfer);

        cardTransferListener.postRemove(new CardTransfer());
        Mockito.verify(auditService, Mockito.times(1)).saveAudit(auditDto);
    }

    @Test
    void getOldCardTransfer() {
        CardTransfer oldCardTransfer = CardTransfer.builder()
                .id(11L)
                .amount(BigDecimal.valueOf(22))
                .purpose("33")
                .accountDetailsId(44)
                .cardNumber(55)
                .build();
        cardTransferListener.setBeanUtil(beanUtil);
        Mockito.when(beanUtil.getBeans(AuditTransferStorage.class)).thenReturn(auditTransferStorage);
        Mockito.when(auditTransferStorage.getCardTransfer()).thenReturn(oldCardTransfer);
        Assertions.assertEquals(cardTransferListener.getOldCardTransfer(), oldCardTransfer);
    }
}