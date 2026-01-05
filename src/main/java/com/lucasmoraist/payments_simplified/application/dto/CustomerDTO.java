package com.lucasmoraist.payments_simplified.application.dto;

import com.lucasmoraist.payments_simplified.domain.model.Document;
import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;

import java.util.List;

public record CustomerDTO(
        String name,
        String email,
        String password,
        String accountNumber,
        List<Document> documents,
        List<PaymentKey> paymentKeys
) {

}
