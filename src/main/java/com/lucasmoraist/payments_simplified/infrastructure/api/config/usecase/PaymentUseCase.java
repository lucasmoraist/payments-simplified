package com.lucasmoraist.payments_simplified.infrastructure.api.config.usecase;

import com.lucasmoraist.payments_simplified.application.gateway.TokenGateway;
import com.lucasmoraist.payments_simplified.application.gateway.TransactionGateway;
import com.lucasmoraist.payments_simplified.application.usecases.customer.FindByCustomerIdCase;
import com.lucasmoraist.payments_simplified.application.usecases.payment.SendPaymentCase;
import com.lucasmoraist.payments_simplified.application.usecases.payment_key.FindPaymentKeyCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentUseCase {

    private final FindPaymentKeyCase findPaymentKeyCase;
    private final FindByCustomerIdCase findByCustomerIdCase;
    private final TokenGateway tokenGateway;
    private final TransactionGateway transactionPersistence;

    @Bean
    public SendPaymentCase sendPaymentCase() {
        return new SendPaymentCase(findPaymentKeyCase, findByCustomerIdCase, tokenGateway, transactionPersistence);
    }

}
