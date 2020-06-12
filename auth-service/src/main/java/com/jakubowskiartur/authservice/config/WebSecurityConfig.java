package com.jakubowskiartur.authservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
@Slf4j
public class WebSecurityConfig {

    private ReactiveAuthenticationManager manager;
    private ServerSecurityContextRepository contextRepository;

    public WebSecurityConfig(ReactiveAuthenticationManager manager, ServerSecurityContextRepository contextRepository) {
        this.manager = manager;
        this.contextRepository = contextRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        log.warn("IM IN THE FILTER CHAIN METHOD");
        return http.exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(UNAUTHORIZED)))
                .accessDeniedHandler((swe, e) ->
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(FORBIDDEN)))
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(manager)
                .securityContextRepository(contextRepository)
                .authorizeExchange()
                .pathMatchers(OPTIONS).permitAll()
                .pathMatchers("/login", "/users", "/register").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
}
