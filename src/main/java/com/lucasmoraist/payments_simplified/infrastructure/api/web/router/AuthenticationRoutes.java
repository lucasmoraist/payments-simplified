package com.lucasmoraist.payments_simplified.infrastructure.api.web.router;

import com.lucasmoraist.payments_simplified.application.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/auth")
public interface AuthenticationRoutes {

    @PostMapping("/login")
    ResponseEntity<TokenDTO> authenticate(@RequestParam String email, @RequestParam String password);

}
