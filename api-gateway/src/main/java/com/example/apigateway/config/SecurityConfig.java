package com.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig {

    // Use a Base64-encoded secret key here
    private static final String SECRET_KEY = "bXktc3VwZXItc2VjcmV0LWp3dC1rZXktd2hpY2gtaXM...";

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**").permitAll() // Allow authentication endpoints
                .anyExchange().authenticated() // Secure all other endpoints
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))); // Fix: Correct reactive method

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        // Decode the Base64 string and create a SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        // Return a correctly configured JWT decoder for WebFlux
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }
}
