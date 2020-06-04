package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Mono<MongoUser> findByUsername(String username);
    Mono<Void> deleteByUsername(String username);
}
