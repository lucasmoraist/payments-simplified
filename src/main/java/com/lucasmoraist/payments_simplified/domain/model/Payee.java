package com.lucasmoraist.payments_simplified.domain.model;

import java.util.UUID;

public record Payee(
        UUID id,
        String name,
        String agency,
        String accountNumber,
        String accountType
) {

}
