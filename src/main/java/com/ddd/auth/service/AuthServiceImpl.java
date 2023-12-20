package com.ddd.auth.service;

import com.ddd.auth.dto.AuthenticationResponseDto;
import com.ddd.auth.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public final class AuthServiceImpl implements AuthService {

    private final WebClient webClient;
    private final JwtService jwtService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return webClient.get()
                .uri("/username/{username}", Collections.singletonMap("username", username))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                                .password(user.getPassword())
                                .authorities(user.getAuthorities())
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .disabled(false)
                                .build());
    }

    @Override
    public Mono<UserDetails> findByEmail(String email) {
        return webClient.get()
                .uri("/email/{email}", Collections.singletonMap("email", email))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities())
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build());
    }

    @Override
    public Mono<AuthenticationResponseDto> login(String username, String password) {
        return findByUsername(username)
                .map(user -> AuthenticationResponseDto.builder()
                        .accessToken(jwtService.generateToken(user))
                        .build());
    }


}
