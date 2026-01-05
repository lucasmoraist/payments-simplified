package com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity;

import com.lucasmoraist.payments_simplified.domain.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_transaction")
@Table(name = "t_transaction")
public class TransactionEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID transactionId;
    private UUID payerId;
    private UUID payeeId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

}
