package com.lucasmoraist.payments_simplified.domain.model;

import java.util.UUID;

public record Payee(
        UUID id,
        String name,
        String agency,
        String accountNumber,
        String accountType
) {

    public Payee(Customer customerPayee) {
        this(
                customerPayee.id(),
                customerPayee.name(),
                customerPayee.account().agency(),
                customerPayee.account().accountNumber(),
                customerPayee.account().accountType()
        );
    }

}
