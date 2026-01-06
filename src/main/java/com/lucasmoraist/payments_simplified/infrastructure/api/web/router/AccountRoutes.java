package com.lucasmoraist.payments_simplified.infrastructure.api.web.router;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.UUID;

@RequestMapping("/api/v1/accounts")
public interface AccountRoutes {

    @PatchMapping("/{accountId}/deposit")
    ResponseEntity<Void> depositAmount(@PathVariable UUID accountId, @RequestParam BigDecimal amount);

}
