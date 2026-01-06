package com.lucasmoraist.payments_simplified.infrastructure.api.web.controller;

import com.lucasmoraist.payments_simplified.application.usecases.account.DepositAmountCase;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.router.AccountRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountRoutes {

    private final DepositAmountCase depositAmountCase;

    @Override
    public ResponseEntity<Void> depositAmount(UUID accountId, BigDecimal amount) {
        this.depositAmountCase.execute(accountId, amount);
        return ResponseEntity.noContent().build();
    }

}
