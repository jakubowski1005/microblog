package com.jakubowskiartur.authservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        if (!jwtUtil.validateToken(token)) {
            return Mono.empty();
        }

        Claims claims = jwtUtil.getAllClaimsFromToken(token);

        /////////////////
//        List<String> rolesMap = claims.get("role", List.class);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        rolesMap.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
//
//        for(String roleMap : rolesMap) {
//            authorities.add(new SimpleGrantedAuthority(roleMap));
//        }
        /////////////////


        List<GrantedAuthority> authorities = (List<GrantedAuthority>) claims.get("role", List.class)
                .stream().map(role -> new SimpleGrantedAuthority((String) role));



        return Mono.just(new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities));

    }
}
