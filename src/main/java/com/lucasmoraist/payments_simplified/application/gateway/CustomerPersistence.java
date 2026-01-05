package com.lucasmoraist.payments_simplified.application.gateway;

import com.lucasmoraist.payments_simplified.domain.model.Customer;

import java.util.UUID;

public interface CustomerPersistence {
    Customer save(Customer customer);
    Customer findByEmail(String email);
    Customer findById(UUID customerId);
}
