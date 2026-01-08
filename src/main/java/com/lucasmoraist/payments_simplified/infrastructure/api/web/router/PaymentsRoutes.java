package com.lucasmoraist.payments_simplified.infrastructure.api.web.router;

import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/payments")
public interface PaymentsRoutes {

    @PostMapping("/send")
    ResponseEntity<Void> sendPayment(@RequestHeader("Authorization") String authorization,
                                     @RequestBody PaymentRequest paymentRequest);

}
