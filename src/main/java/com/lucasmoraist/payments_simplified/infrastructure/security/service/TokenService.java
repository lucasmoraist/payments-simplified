package com.lucasmoraist.payments_simplified.infrastructure.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lucasmoraist.payments_simplified.application.gateway.TokenGateway;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Log4j2
@Service
public class TokenService implements TokenGateway {

    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
    @Value("${spring.security.jwt.expires-in}")
    private int expiresIn;

    @Override
    public String generateToken(Customer customer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject("payments-simplified")
                    .withClaim("email", customer.email())
                    .withClaim("id", customer.id().toString())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            log.debug("Token generated for customer id {}: {}", customer.id(), token);

            return token;
        } catch (Exception e) {
            log.error("Error generating token", e);
            throw new IllegalArgumentException("Error generating token", e);
        }
    }

    @Override
    public String getEmailFromToken(String token) {
        try {
            DecodedJWT decodedJWT = verifyToken(token);
            String email = decodedJWT.getClaim("email").asString();
            log.debug("Email extracted from token: {}", email);
            return email;
        } catch (Exception e) {
            log.error("Error verifying token", e);
            throw new IllegalArgumentException("Error verifying token", e);
        }
    }

    @Override
    public UUID getIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = verifyToken(token);
            String id = decodedJWT.getClaim("id").asString();
            log.debug("ID extracted from token: {}", id);
            return UUID.fromString(id);
        } catch (Exception e) {
            log.error("Error verifying token", e);
            throw new IllegalArgumentException("Error verifying token", e);
        }
    }

    private DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .withIssuer("auth0")
                .build()
                .verify(token);
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusSeconds(expiresIn).toInstant(ZoneOffset.of("-03:00"));
    }

}
