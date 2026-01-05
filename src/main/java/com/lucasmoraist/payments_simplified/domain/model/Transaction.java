package com.lucasmoraist.payments_simplified.domain.model;

import com.lucasmoraist.payments_simplified.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record Transaction(
        UUID id,
        UUID transactionId,
        UUID payerId,
        UUID payeeId,
        BigDecimal amount,
        PaymentStatus transactionStatus,
        Account account
) {

}
