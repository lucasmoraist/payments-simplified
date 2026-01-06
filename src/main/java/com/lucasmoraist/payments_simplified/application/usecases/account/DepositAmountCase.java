package com.lucasmoraist.payments_simplified.application.usecases.account;

import com.lucasmoraist.payments_simplified.application.gateway.AccountPersistence;

import java.math.BigDecimal;
import java.util.UUID;

public class DepositAmountCase {

    private final AccountPersistence accountPersistence;

    public DepositAmountCase(AccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    public void execute(UUID accountId, BigDecimal amount) {
        accountPersistence.updateBalance(accountId, amount);
    }

}
