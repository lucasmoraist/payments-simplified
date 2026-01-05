package com.lucasmoraist.payments_simplified.infrastructure.database.sql.persistence;

import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.application.mapper.CustomerMapper;
import com.lucasmoraist.payments_simplified.domain.exceptions.EmailException;
import com.lucasmoraist.payments_simplified.domain.exceptions.NotFoundException;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.CustomerEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerPersistenceImpl implements CustomerPersistence {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        log.debug("Saving customer: {}", customer);
        CustomerEntity entity = CustomerMapper.toEntity(customer);
        entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));

        this.emailIsExists(customer.email());

        CustomerEntity entitySaved = this.repository.save(entity);
        log.debug("Customer saved with id: {}", entity.getId());
        return CustomerMapper.toDomain(entitySaved);
    }

    @Override
    public Customer findByEmail(String email) {
        return this.repository.findByEmail(email)
                .stream()
                .map(CustomerMapper::toDomain)
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Customer not found with email: {}", email);
                    return new NotFoundException("Customer not found with email: " + email);
                });
    }

    private void emailIsExists(String email) {
        this.repository.findByEmail(email)
                .ifPresent(c -> {
                    log.error("Email already exists: {}", c.getEmail());
                    throw new EmailException("Email already exists");
                });
    }

}
