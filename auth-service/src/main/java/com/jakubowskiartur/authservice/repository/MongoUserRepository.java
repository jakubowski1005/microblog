package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {

    Mono<MongoUser> findByUsernameAndEmail(String username, String email);
    Mono<MongoUser> findByUsername(String username);
}
