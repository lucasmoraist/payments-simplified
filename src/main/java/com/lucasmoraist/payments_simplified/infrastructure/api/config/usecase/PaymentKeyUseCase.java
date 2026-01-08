package com.lucasmoraist.payments_simplified.infrastructure.api.config.usecase;

import com.lucasmoraist.payments_simplified.application.gateway.PaymentKeyPersistence;
import com.lucasmoraist.payments_simplified.application.usecases.payment_key.FindPaymentKeyCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentKeyUseCase {

    private final PaymentKeyPersistence paymentKeyPersistence;

    @Bean
    public FindPaymentKeyCase findPaymentKeyCase() {
        return new FindPaymentKeyCase(paymentKeyPersistence);
    }

}
