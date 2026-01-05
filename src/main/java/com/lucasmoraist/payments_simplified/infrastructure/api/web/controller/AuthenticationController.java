package com.lucasmoraist.payments_simplified.infrastructure.api.web.controller;

import com.lucasmoraist.payments_simplified.application.dto.TokenDTO;
import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.application.gateway.TokenGateway;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.router.AuthenticationRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationRoutes {

    @Value("${spring.security.jwt.expires-in}")
    private Integer expiresIn;
    private final PasswordEncoder passwordEncoder;
    private final TokenGateway tokenGateway;
    private final CustomerPersistence customerPersistence;

    @Override
    public ResponseEntity<TokenDTO> authenticate(String email, String password) {
        Customer customer = this.customerPersistence.findByEmail(email);

        if(!validatePassword(password, customer.password())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = this.tokenGateway.generateToken(customer);
        TokenDTO dto = new TokenDTO(token, expiresIn);

        return ResponseEntity.ok().body(dto);
    }

    private boolean validatePassword(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
