package com.lucasmoraist.payments_simplified.infrastructure.database.sql.persistence;

import com.lucasmoraist.payments_simplified.application.gateway.PaymentKeyPersistence;
import com.lucasmoraist.payments_simplified.application.mapper.PaymentKeyMapper;
import com.lucasmoraist.payments_simplified.domain.exceptions.NotFoundException;
import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository.PaymentKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class PaymentKeyPersistenceImpl implements PaymentKeyPersistence {

    private final PaymentKeyRepository repository;

    @Override
    public PaymentKey findByKeyValue(String keyValue) {
        return this.repository.findByKeyValue(keyValue)
                .map(PaymentKeyMapper::toDomain)
                .orElseThrow(() -> {
                    log.warn("Payment key not found: {}", keyValue);
                    return new NotFoundException("Payment key not found");
                });
    }

}
