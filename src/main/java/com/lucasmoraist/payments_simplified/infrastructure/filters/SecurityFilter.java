package com.lucasmoraist.payments_simplified.infrastructure.filters;

import com.lucasmoraist.payments_simplified.application.gateway.TokenGateway;
import com.lucasmoraist.payments_simplified.infrastructure.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenGateway tokenGateway;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (token != null) {
            String email = this.tokenGateway.getEmailFromToken(token);

            if (email != null) {
                UserDetails user = this.userDetailsService.loadUserByUsername(email);

                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.warn("User not found for email: {}", email);
                }
            } else {
                log.error("Token invalid");
            }
        } else {
            log.warn("Token not found");
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }

}
