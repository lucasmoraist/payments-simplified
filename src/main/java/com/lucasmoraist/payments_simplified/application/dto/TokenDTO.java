package com.lucasmoraist.payments_simplified.application.dto;

public record TokenDTO(
        String token,
        String type,
        Integer expiresIn
) {

    public TokenDTO(String token, Integer expiresIn) {
        this(token, "Bearer", expiresIn);
    }

}
