package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {

    Mono<MongoUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
