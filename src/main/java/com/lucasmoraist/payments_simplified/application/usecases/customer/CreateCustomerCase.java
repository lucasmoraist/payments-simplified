package com.lucasmoraist.payments_simplified.application.usecases.customer;

import com.lucasmoraist.payments_simplified.application.dto.CustomerDTO;
import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.application.mapper.CustomerMapper;
import com.lucasmoraist.payments_simplified.domain.model.Customer;

public class CreateCustomerCase {

    private final CustomerPersistence customerPersistence;

    public CreateCustomerCase(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    public Customer execute(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toDomain(customerDTO);
        return this.customerPersistence.save(customer);
    }

}
