package com.lucasmoraist.payments_simplified.application.usecases.customer;

import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.domain.model.Customer;

import java.util.UUID;

public class FindByCustomerIdCase {

    private final CustomerPersistence customerPersistence;

    public FindByCustomerIdCase(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    public Customer execute(UUID customerId) {
        return this.customerPersistence.findById(customerId);
    }

}
