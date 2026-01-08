package com.lucasmoraist.payments_simplified.application.gateway;

import com.lucasmoraist.payments_simplified.domain.model.Customer;

import java.util.UUID;

public interface TokenGateway {
    String generateToken(Customer customer);
    String getEmailFromToken(String token);
    UUID getIdFromToken(String token);
}
