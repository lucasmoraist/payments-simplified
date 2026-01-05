package com.lucasmoraist.payments_simplified.infrastructure.api.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CustomerRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,
        @NotBlank(message = "Account number is required")
        @Size(min = 7, max = 8, message = "Account number must be between 7 and 8 characters long")
        String accountNumber,
        @NotEmpty(message = "At least one document is required")
        List<DocumentRequest> documents,
        List<PaymentKeyRequest> paymentKeys
) {

}
