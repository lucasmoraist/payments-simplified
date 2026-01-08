package com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository;

import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

}
