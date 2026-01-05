package com.lucasmoraist.payments_simplified.infrastructure.api.web.router;

import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/customer")
public interface CustomerRoutes {

    @PostMapping("/register")
    ResponseEntity<Void> registerCustomer(@RequestBody @Valid CustomerRequest request);

}
