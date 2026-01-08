package com.lucasmoraist.payments_simplified.infrastructure.api.web.controller;

import com.lucasmoraist.payments_simplified.application.usecases.payment.SendPaymentCase;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.PaymentRequest;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.router.PaymentsRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentsController implements PaymentsRoutes {

    private final SendPaymentCase sendPaymentCase;

    @Override
    public ResponseEntity<Void> sendPayment(String authorization, PaymentRequest paymentRequest) {
        this.sendPaymentCase.execute(authorization, paymentRequest.paymentKeyValue(), paymentRequest.amount());
        return ResponseEntity.ok().build();
    }

}
