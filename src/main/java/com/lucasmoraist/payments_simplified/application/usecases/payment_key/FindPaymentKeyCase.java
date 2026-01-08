package com.lucasmoraist.payments_simplified.application.usecases.payment_key;

import com.lucasmoraist.payments_simplified.application.gateway.PaymentKeyPersistence;
import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;

public class FindPaymentKeyCase {

    private final PaymentKeyPersistence paymentKeyPersistence;

    public FindPaymentKeyCase(PaymentKeyPersistence paymentKeyPersistence) {
        this.paymentKeyPersistence = paymentKeyPersistence;
    }

    public PaymentKey execute(String paymentKeyValue) {
        return this.paymentKeyPersistence.findByKeyValue(paymentKeyValue);
    }

}
