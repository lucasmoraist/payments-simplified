package com.lucasmoraist.payments_simplified.domain.model;

import java.util.UUID;

public record PaymentKey(
        UUID id,
        String keyValue,
        Customer customer
) {

}
