package com.ddd.auth.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.ddd.auth.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(@Autowired UserHandler userHandler) {
        return RouterFunctions.route(GET("/username/{username}"), userHandler::findByUsername)
                .andRoute(GET("/email/{email}"), userHandler::findByEmail)
                .andRoute(POST("/login"), userHandler::login);
    }
}
