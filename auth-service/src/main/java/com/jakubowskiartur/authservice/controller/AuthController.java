//package com.jakubowskiartur.authservice.controller;
//
//import com.jakubowskiartur.authservice.model.MongoUser;
//import com.jakubowskiartur.authservice.service.AuthHandler;
//import com.jakubowskiartur.authservice.service.SignUpRequest;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//import javax.validation.Valid;
//
//@RestController
//@RequiredArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//public class AuthController {
//
//    AuthHandler handler;
//
//    @PostMapping("/register")
//    public Mono<ResponseEntity<MongoUser>> register(@Valid @RequestBody SignUpRequest request) {
//        return handler.register(request);
//    }
//}
