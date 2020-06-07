//package com.jakubowskiartur.authservice.service;
//
//import com.jakubowskiartur.authservice.model.MongoUser;
//import org.springframework.http.ResponseEntity;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface ReactiveUserService {
//
//    Flux<MongoUser> receiveUsers();
//    Mono<MongoUser> receiveUserById(String id);
//    Mono<MongoUser> receiveByUsername(String username);
//    Mono<ResponseEntity<MongoUser>> changePassword(String newPassword);
//    Mono<ResponseEntity<MongoUser>> addRole(String id, String role);
//    Mono<ResponseEntity<Void>> deletePrincipalAccount();
//    Mono<ResponseEntity<Void>> deleteUserById(String id);
//}
