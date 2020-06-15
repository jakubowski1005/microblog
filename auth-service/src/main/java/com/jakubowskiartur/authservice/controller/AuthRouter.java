package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.service.AuthHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AuthRouter {

    @Bean
    @SuppressWarnings("NullableProblems")
    public RouterFunction<ServerResponse> route(AuthHandlerImpl handler) {
        return RouterFunctions.route(POST("/register").and(accept(APPLICATION_JSON)), handler::register)
                .andRoute(POST("/login").and(accept(APPLICATION_JSON)), handler::login);
    }
}
