package com.lucasmoraist.payments_simplified.infrastructure.security.service;

import com.lucasmoraist.payments_simplified.application.gateway.CustomerPersistence;
import com.lucasmoraist.payments_simplified.domain.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerPersistence customerPersistence;

    public UserDetailsServiceImpl(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerPersistence.findByEmail(username);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            }

            @Override
            public @Nullable String getPassword() {
                return customer.password();
            }

            @Override
            public String getUsername() {
                return customer.email();
            }
        };
    }

}
