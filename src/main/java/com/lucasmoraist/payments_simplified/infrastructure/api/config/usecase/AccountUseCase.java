package com.lucasmoraist.payments_simplified.infrastructure.api.config.usecase;

import com.lucasmoraist.payments_simplified.application.gateway.AccountPersistence;
import com.lucasmoraist.payments_simplified.application.usecases.account.DepositAmountCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AccountUseCase {

    private final AccountPersistence accountPersistence;

    @Bean
    public DepositAmountCase depositAmountCase() {
        return new DepositAmountCase(accountPersistence);
    }

}
