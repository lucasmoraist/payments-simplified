package com.lucasmoraist.payments_simplified.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record Account(
        UUID id,
        String agency,
        String accountNumber,
        String accountType,
        BigDecimal balance,
        List<Transaction> transactions
) {

}
