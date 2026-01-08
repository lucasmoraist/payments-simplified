package com.lucasmoraist.payments_simplified.domain.model;

import com.lucasmoraist.payments_simplified.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
        UUID transactionId,
        Payer payer,
        Payee payee,
        BigDecimal amount,
        PaymentStatus status,
        LocalDateTime executionDateTime
) {

}
