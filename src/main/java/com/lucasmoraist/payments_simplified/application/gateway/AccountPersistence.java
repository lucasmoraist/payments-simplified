package com.lucasmoraist.payments_simplified.application.gateway;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountPersistence {
    void updateBalance(UUID accountId, BigDecimal amount);
}
