//package com.jakubowskiartur.authservice.service;
//
//import com.jakubowskiartur.authservice.model.MongoUser;
//import com.jakubowskiartur.authservice.repository.MongoUserRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//public class AuthHandler implements ReactiveAuthService {
//
//    MongoUserRepository repository;
//    PasswordEncoder encoder;
//
//    @Override
//    public Mono<ResponseEntity<MongoUser>> register(SignUpRequest request) {
//        return Mono.just(request)
//                .filter(this::isValid)
//                .map(this::buildFromRequest)
//                .map(created -> ResponseEntity.status(201).body(created))
//                .defaultIfEmpty(ResponseEntity.badRequest().build());
//    }
//
//    private MongoUser buildFromRequest(SignUpRequest request) {
//        return  MongoUser.builder()
//                .username(request.getLogin())
//                .email(request.getEmail())
//                .password(encoder.encode(request.getPassword()))
//                .roles(Collections.singletonList("ROLE_USER"))
//                .enabled(true)
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .build();
//    }
//
//    private boolean isValid(SignUpRequest request) {
//        String login = request.getLogin();
//        String email = request.getEmail();
//        //return !(repository.existsByUsername(login) || repository.existsByEmail(email));
//        return repository.findByUsernameAndEmail(login, email)
//                .equals(Mono.empty());
//
//    }
//}
