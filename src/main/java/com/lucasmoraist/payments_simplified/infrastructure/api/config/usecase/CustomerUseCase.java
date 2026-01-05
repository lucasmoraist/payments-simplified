package com.lucasmoraist.payments_simplified.infrastructure.api.config.usecase;

import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.application.usecases.customer.CreateCustomerCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerUseCase {

    private final CustomerPersistence customerPersistence;

    @Bean
    public CreateCustomerCase createCustomerCase() {
        return new CreateCustomerCase(customerPersistence);
    }

}
