package com.lucasmoraist.payments_simplified.infrastructure.api.web.controller;

import com.lucasmoraist.payments_simplified.application.dto.CustomerDTO;
import com.lucasmoraist.payments_simplified.application.mapper.CustomerMapper;
import com.lucasmoraist.payments_simplified.application.usecases.customer.CreateCustomerCase;
import com.lucasmoraist.payments_simplified.application.usecases.customer.FindByCustomerIdCase;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.CustomerRequest;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.router.CustomerRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerRoutes {

    private final CreateCustomerCase createCustomerCase;
    private final FindByCustomerIdCase findByCustomerIdCase;

    @Override
    public ResponseEntity<Void> registerCustomer(CustomerRequest request) {
        CustomerDTO dto = CustomerMapper.toDto(request);
        Customer customer = this.createCustomerCase.execute(dto);
        URI uri = URI.create("/api/v1/customer/" + customer.id());
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(UUID custId) {
        Customer customer = this.findByCustomerIdCase.execute(custId);
        return ResponseEntity.ok().body(customer);
    }

}
