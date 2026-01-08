package com.lucasmoraist.payments_simplified.infrastructure.api.web.request;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotBlank(message = "Payment key value must not be blank")
        String paymentKeyValue,
        @NotNull(message = "Amount must not be null")
        @NegativeOrZero(message = "Amount must be negative or zero")
        BigDecimal amount
) {

}
