package com.lucasmoraist.payments_simplified.application.gateway;

import com.lucasmoraist.payments_simplified.domain.model.Customer;

public interface CustomerPersistence {
    Customer save(Customer customer);
    Customer findByEmail(String email);
}
