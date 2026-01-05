package com.lucasmoraist.payments_simplified.application.mapper;

import com.lucasmoraist.payments_simplified.application.dto.CustomerDTO;
import com.lucasmoraist.payments_simplified.domain.model.Account;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.CustomerRequest;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.CustomerEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.DocumentEntity;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.PaymentKeyEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class CustomerMapper {

    private CustomerMapper() {
        throw new IllegalStateException("Utility class");
    }

    private static final String DEFAULT_AGENCY = "0001";
    private static final String DEFAULT_ACCOUNT_TYPE = "CACC";

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                AccountMapper.toDomain(entity.getAccount()),
                entity.getDocuments()
                        .stream()
                        .map(DocumentMapper::toDomain)
                        .toList(),
                entity.getPaymentKeys()
                        .stream()
                        .map(PaymentKeyMapper::toDomain)
                        .toList()
        );
    }

    public static Customer toDomain(CustomerDTO customerDTO) {
        return new Customer(
                null,
                customerDTO.name(),
                customerDTO.email(),
                customerDTO.password(),
                new Account(
                        null,
                        DEFAULT_AGENCY,
                        customerDTO.accountNumber(),
                        DEFAULT_ACCOUNT_TYPE,
                        BigDecimal.ZERO,
                        new ArrayList<>()
                ),
                customerDTO.documents(),
                customerDTO.paymentKeys()
        );
    }

    public static CustomerDTO toDto(CustomerRequest request) {
        return new CustomerDTO(
                request.name(),
                request.email(),
                request.password(),
                request.accountNumber(),
                request.documents()
                        .stream()
                        .map(DocumentMapper::toDto)
                        .toList(),
                request.paymentKeys()
                        .stream()
                        .map(PaymentKeyMapper::toDto)
                        .toList()
        );
    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity(
                null,
                customer.name(),
                customer.email(),
                customer.password(),
                AccountMapper.toEntity(customer.account()),
                new ArrayList<>(),
                new ArrayList<>()
        );

        if (customer.documents() != null) {
            customerEntity.setDocuments(customer.documents().stream().map(d -> {
                DocumentEntity de = DocumentMapper.toEntity(d);
                de.setCustomer(customerEntity);
                return de;
            }).toList());
        }

        if (customer.paymentKeys() != null) {
            customerEntity.setPaymentKeys(customer.paymentKeys().stream().map(p -> {
                PaymentKeyEntity pe = PaymentKeyMapper.toEntity(p);
                pe.setCustomer(customerEntity);
                return pe;
            }).toList());
        }

        return customerEntity;
    }

}
