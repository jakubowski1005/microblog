package com.jakubowskiartur.authservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.*;

@Component
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private ReactiveAuthenticationManager authenticationManager;

    public SecurityContextRepository(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        log.warn("IM IN LOAD METHOD SWE");
        ServerHttpRequest request = exchange.getRequest();
        String header = request.getHeaders().getFirst(AUTHORIZATION);

        if (nonNull(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Authentication authentication = new UsernamePasswordAuthenticationToken(token, token);

            return this.authenticationManager.authenticate(authentication).map(SecurityContextImpl::new);
        }
        return Mono.empty();
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        log.warn("IM IN SAVE METHOD SWE");
        throw new UnsupportedOperationException("Not supported yet. " + this.getClass() + ".save()");
    }
}
