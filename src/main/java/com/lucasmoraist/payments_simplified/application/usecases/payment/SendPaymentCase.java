package com.lucasmoraist.payments_simplified.application.usecases.payment;

import com.lucasmoraist.payments_simplified.application.gateway.TokenGateway;
import com.lucasmoraist.payments_simplified.application.gateway.TransactionGateway;
import com.lucasmoraist.payments_simplified.application.usecases.customer.FindByCustomerIdCase;
import com.lucasmoraist.payments_simplified.application.usecases.payment_key.FindPaymentKeyCase;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import com.lucasmoraist.payments_simplified.domain.model.Payee;
import com.lucasmoraist.payments_simplified.domain.model.Payer;
import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;

import java.math.BigDecimal;
import java.util.UUID;

public class SendPaymentCase {

    private final FindPaymentKeyCase findPaymentKeyCase;
    private final FindByCustomerIdCase findByCustomerIdCase;
    private final TokenGateway tokenGateway;
    private final TransactionGateway transactionPersistence;

    public SendPaymentCase(
            FindPaymentKeyCase findPaymentKeyCase,
            FindByCustomerIdCase findByCustomerIdCase,
            TokenGateway tokenGateway,
            TransactionGateway transactionPersistence
    ) {
        this.findPaymentKeyCase = findPaymentKeyCase;
        this.findByCustomerIdCase = findByCustomerIdCase;
        this.tokenGateway = tokenGateway;
        this.transactionPersistence = transactionPersistence;
    }

    public void execute(String token, String paymentKeyValue, BigDecimal amount) {
        PaymentKey paymentKey = this.findPaymentKeyCase.execute(paymentKeyValue);
        Customer customerPayee = this.findByCustomerIdCase.execute(paymentKey.customer().id());

        UUID customerId = this.tokenGateway.getIdFromToken(token.replace("Bearer ", ""));
        Customer customerPayer = this.findByCustomerIdCase.execute(customerId);

        Payee payee = new Payee(customerPayee);
        Payer payer = new Payer(customerPayer);

        // TODO: Deve ser salvo no mongo a transação antes de registrar um transaction e finalizar a regras citadas no desafio

        this.transactionPersistence.registerTransaction(payer,  payee, amount);
    }

}
