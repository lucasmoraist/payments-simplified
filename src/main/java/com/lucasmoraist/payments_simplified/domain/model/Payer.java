package com.lucasmoraist.payments_simplified.domain.model;

import java.util.UUID;

public record Payer(
        UUID id,
        String name,
        String agency,
        String accountNumber,
        String accountType
) {

    public Payer(Customer customerPayer) {
        this(
                customerPayer.id(),
                customerPayer.name(),
                customerPayer.account().agency(),
                customerPayer.account().accountNumber(),
                customerPayer.account().accountType()
        );
    }

}
