package com.lucasmoraist.payments_simplified.application.mapper;

import com.lucasmoraist.payments_simplified.domain.model.Transaction;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.TransactionEntity;

public final class TransactionMapper {

    private TransactionMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Transaction toDomain(TransactionEntity transactionEntity) {
        return new Transaction(
                transactionEntity.getId(),
                transactionEntity.getTransactionId(),
                transactionEntity.getPayerId(),
                transactionEntity.getPayeeId(),
                transactionEntity.getAmount(),
                transactionEntity.getTransactionStatus(),
                null
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                null,
                transaction.transactionId(),
                transaction.payerId(),
                transaction.payeeId(),
                transaction.amount(),
                transaction.transactionStatus(),
                null
        );
    }

}
