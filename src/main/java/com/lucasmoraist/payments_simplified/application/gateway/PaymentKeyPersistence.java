package com.lucasmoraist.payments_simplified.application.gateway;

import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;

public interface PaymentKeyPersistence {
    PaymentKey findByKeyValue(String keyValue);
}
