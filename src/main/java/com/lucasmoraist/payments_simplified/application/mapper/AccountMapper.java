package com.lucasmoraist.payments_simplified.application.mapper;

import com.lucasmoraist.payments_simplified.domain.model.Account;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.AccountEntity;

public final class AccountMapper {

    private AccountMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Account toDomain(AccountEntity account) {
        return new Account(
                account.getId(),
                account.getAgency(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getTransactions()
                        .stream()
                        .map(TransactionMapper::toDomain)
                        .toList()
        );
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                null,
                account.agency(),
                account.accountNumber(),
                account.accountType(),
                account.balance(),
                account.transactions()
                        .stream()
                        .map(TransactionMapper::toEntity)
                        .toList()
        );
    }

}
