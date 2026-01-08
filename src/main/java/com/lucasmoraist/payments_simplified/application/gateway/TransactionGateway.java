package com.lucasmoraist.payments_simplified.application.gateway;

import com.lucasmoraist.payments_simplified.domain.model.Payee;
import com.lucasmoraist.payments_simplified.domain.model.Payer;

import java.math.BigDecimal;

public interface TransactionGateway {
    void registerTransaction(Payer payer, Payee payee, BigDecimal amount);
}
