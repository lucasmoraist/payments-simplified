package com.lucasmoraist.payments_simplified.infrastructure.api.service;

import com.lucasmoraist.payments_simplified.application.gateway.TransactionGateway;
import com.lucasmoraist.payments_simplified.domain.enums.PaymentStatus;
import com.lucasmoraist.payments_simplified.domain.model.Payee;
import com.lucasmoraist.payments_simplified.domain.model.Payer;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.CustomerEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.TransactionEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository.CustomerRepository;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void registerTransaction(Payer payer, Payee payee, BigDecimal amount) {
        CustomerEntity payerEntity = findCustomerEntityById(payer.id());
        CustomerEntity payeeEntity = findCustomerEntityById(payee.id());

        payeeEntity.transfer(amount);
        payerEntity.withdraw(amount);

        customerRepository.save(payeeEntity);
        customerRepository.save(payerEntity);

        TransactionEntity transactionPayer = buildTransactionPayer(payerEntity, payee, amount);
        TransactionEntity transactionPayee = buildTransactionPayee(payeeEntity, payer, amount);

        transactionRepository.save(transactionPayer);
        transactionRepository.save(transactionPayee);
    }

    private CustomerEntity findCustomerEntityById(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.error("Customer with id {} not found", customerId);
                    return new IllegalArgumentException("Customer not found");
                });
    }

    private TransactionEntity buildTransactionPayer(CustomerEntity payer, Payee payee, BigDecimal amount) {
        return TransactionEntity.builder()
                .transactionId(UUID.randomUUID())
                .payerId(payer.getId())
                .payeeId(payee.id())
                .amount(amount)
                .transactionStatus(PaymentStatus.COMPLETED)
                .account(payer.getAccount())
                .build();
    }

    private TransactionEntity buildTransactionPayee(CustomerEntity payee, Payer payer, BigDecimal amount) {
        return TransactionEntity.builder()
                .transactionId(UUID.randomUUID())
                .payerId(payer.id())
                .payeeId(payee.getId())
                .amount(amount)
                .transactionStatus(PaymentStatus.COMPLETED)
                .account(payee.getAccount())
                .build();
    }

}
