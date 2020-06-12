package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.AuthRequest;
import com.jakubowskiartur.authservice.payload.AuthResponse;
import com.jakubowskiartur.authservice.payload.RegisterRequest;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

import static com.jakubowskiartur.authservice.model.Role.*;
import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class AuthHandlerImpl implements AuthHandler {

    private UserRepository repository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtil;

    @Override
    public Mono<ResponseEntity<?>> login(AuthRequest request) {
        return repository.findByUsername(request.getUsername())
                .map(userDetails -> {
                    if (encoder.matches(request.getPassword(), userDetails.getPassword())) {
                        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
                    }
                    return ResponseEntity.status(UNAUTHORIZED).build();
                    })
                .defaultIfEmpty(ResponseEntity.status(UNAUTHORIZED).build());
    }

    @Override
    public Mono<ResponseEntity<User>> register(RegisterRequest request) {

        var created = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .roles(Set.of(ROLE_USER))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        return repository.save(created)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
}
