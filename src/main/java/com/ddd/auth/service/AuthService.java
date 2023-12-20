package com.ddd.auth.service;

import com.ddd.auth.dto.AuthenticationResponseDto;
import com.ddd.auth.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserDetails> findByUsername(String username);
    Mono<UserDetails> findByEmail(String email);

    Mono<AuthenticationResponseDto> login(String username, String password);
}
