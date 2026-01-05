package com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_customer")
@Table(name = "t_customer")
public class CustomerEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AccountEntity account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentEntity> documents = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentKeyEntity> paymentKeys = new ArrayList<>();

    public void transfer(BigDecimal amount) {
        this.account.setBalance(this.account.getBalance().add(amount));
    }

    public void withdraw(BigDecimal amount) {
        this.account.setBalance(this.account.getBalance().subtract(amount));
    }

}
