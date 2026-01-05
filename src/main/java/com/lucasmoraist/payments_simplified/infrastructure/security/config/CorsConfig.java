package com.lucasmoraist.payments_simplified.infrastructure.security.config;

import com.lucasmoraist.payments_simplified.infrastructure.security.config.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

    private final AppProperties appProperties;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        List<String> allowedOrigins = Optional.ofNullable(appProperties.getAllowedOrigins())
                .orElse(List.of());

        List<String> cleanOrigins = allowedOrigins.stream()
                .map(origin -> origin.endsWith("/") ? origin.substring(0, origin.length() - 1) : origin)
                .toList();

        configuration.setAllowedOrigins(cleanOrigins);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
