package com.bank.transfer.entity.transfers;

import com.bank.transfer.audit.listeners.CardTransferListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "card_transfer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners(CardTransferListener.class)
public class CardTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "purpose")
    private  String purpose;
    @Column(name = "account_details_id", nullable = false)
    private long accountDetailsId;
    @Column(unique = true, nullable = false)
    private long cardNumber;
    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"amount\":" + amount + ",\"purpose\":\"" + purpose +
                "\",\"accountDetailsId\":" + accountDetailsId + ",\"cardNumber\":" + cardNumber + "}";
    }
}
