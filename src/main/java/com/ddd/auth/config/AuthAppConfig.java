package com.ddd.auth.config;

import com.ddd.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

@RequiredArgsConstructor
@Configuration
public class AuthAppConfig {

    private final AuthService authService;

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return authService::findByUsername;
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());
        return authenticationManager;
    }
}
