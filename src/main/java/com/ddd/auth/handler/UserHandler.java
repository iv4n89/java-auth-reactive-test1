package com.ddd.auth.handler;

import com.ddd.auth.dto.AuthenticationRequestDto;
import com.ddd.auth.models.User;
import com.ddd.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class UserHandler {

    private final AuthService authService;

    public Mono<ServerResponse> findByUsername(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authService.findByUsername(serverRequest.pathVariable("username")), User.class);
    }

    public Mono<ServerResponse> findByEmail(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authService.findByEmail(serverRequest.pathVariable("email")), User.class);
    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AuthenticationRequestDto.class)
                .flatMap(auth -> authService.login(auth.getUsername(), auth.getPassword()))
                .flatMap(auth -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(auth));
    }

}
