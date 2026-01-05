package com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository;

import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByEmail(String email);
}
