package com.bank.transfer.audit;

import com.bank.transfer.entity.transfers.AccountTransfer;
import com.bank.transfer.entity.transfers.CardTransfer;
import com.bank.transfer.entity.transfers.PhoneTransfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditTransferStorage {
    private AccountTransfer accountTransfer;
    private CardTransfer cardTransfer;
    private PhoneTransfer phoneTransfer;

}
