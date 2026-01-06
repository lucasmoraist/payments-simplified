package com.lucasmoraist.payments_simplified.infrastructure.database.sql.persistence;

import com.lucasmoraist.payments_simplified.application.gateway.AccountPersistence;
import com.lucasmoraist.payments_simplified.domain.exceptions.NotFoundException;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.AccountEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class AccountPersistenceImpl implements AccountPersistence {

    private final AccountRepository repository;

    @Override
    public void updateBalance(UUID accountId, BigDecimal amount) {
        AccountEntity entity = getAccountEntityById(accountId);
        entity.setBalance(entity.getBalance().add(amount));
        this.repository.save(entity);
        log.debug("Account balance updated for account id: {}", accountId);
    }

    private AccountEntity getAccountEntityById(UUID accountId) {
        return this.repository.findById(accountId)
                .orElseThrow(() -> {
                    log.error("Account not found with id: {}", accountId);
                    return new NotFoundException("Account not found with id: " + accountId);
                });
    }

}
