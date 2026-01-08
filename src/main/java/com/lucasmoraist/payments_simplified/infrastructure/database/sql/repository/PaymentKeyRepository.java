package com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository;

import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.PaymentKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentKeyRepository extends JpaRepository<PaymentKeyEntity, UUID> {
    Optional<PaymentKeyEntity> findByKeyValue(String keyValue);
}
