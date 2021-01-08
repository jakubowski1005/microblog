package com.jakubowskiartur.postservice.config;

import com.jakubowskiartur.postservice.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private JwtUtil jwtUtil;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        if (!jwtUtil.validateToken(token)) {
            return Mono.empty();
        }

        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) claims.get("role", List.class)
                .stream()
                .map(role -> new SimpleGrantedAuthority((String) role))
                .collect(Collectors.toList());


        return Mono.just(new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities));

    }
}
