package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.AuthRequest;
import com.jakubowskiartur.authservice.payload.AuthResponse;
import com.jakubowskiartur.authservice.payload.RegisterRequest;
import com.jakubowskiartur.authservice.repository.UserRepository;
import com.jakubowskiartur.authservice.utils.JwtUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

import static com.jakubowskiartur.authservice.model.Role.ROLE_USER;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthHandlerImpl implements AuthHandler {

    UserRepository repository;
    PasswordEncoder encoder;
    JwtUtil jwt;

    @Override
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(AuthRequest.class)
                .flatMap(req -> repository.findByUsername(req.getUsername())
                        .flatMap(found -> {
                            if (encoder.matches(req.getPassword(), found.getPassword())) {
                                return Mono.just(new AuthResponse(jwt.generateToken(found)));
                            }
                            return Mono.empty();
                        }))
                .flatMap(res -> ok().body(Mono.just(res), AuthResponse.class))
                .switchIfEmpty(status(401).build());
    }

    @Override
    public Mono<ServerResponse> register(ServerRequest request) {
        return request.bodyToMono(RegisterRequest.class)
                .map(this::buildFromRequest)
                .flatMap(repository::save)
                .flatMap(res -> status(201).build())
                .doOnError(res -> status(400).build());
    }

    private User buildFromRequest(RegisterRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .roles(Set.of(ROLE_USER))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }
}
