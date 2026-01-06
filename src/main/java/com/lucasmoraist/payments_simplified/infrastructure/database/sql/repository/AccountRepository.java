package com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository;

import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

}
