package com.lucasmoraist.payments_simplified.domain.model;


import com.lucasmoraist.payments_simplified.domain.enums.DocumentType;

import java.util.UUID;

public record Document(
        UUID id,
        DocumentType type,
        String number,
        Customer customer
) {

}
