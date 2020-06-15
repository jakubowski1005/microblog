package com.jakubowskiartur.authservice.utils;

import com.jakubowskiartur.authservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${secret-key}")
    private String secret;

    @Value("${expiration-time}")
    private String expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());

        long expiration = Long.parseLong(expirationTime);
        Date createdAt = new Date();
        Date expiredAt = new Date(createdAt.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(createdAt)
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationTimeFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationTimeFromToken(token).before(new Date());
    }
}
