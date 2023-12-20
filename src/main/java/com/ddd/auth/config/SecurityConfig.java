package com.ddd.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final ReactiveAuthenticationManager authenticationManager;

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
    return http
            .authenticationManager(authenticationManager)
            .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                    .pathMatchers("/username/**").permitAll()
                    .pathMatchers("/email/**").permitAll()
                    .pathMatchers("/register").permitAll()
                    .pathMatchers("/login").permitAll()
                    .anyExchange().authenticated())
            .cors(ServerHttpSecurity.CorsSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .build();
  }
}
