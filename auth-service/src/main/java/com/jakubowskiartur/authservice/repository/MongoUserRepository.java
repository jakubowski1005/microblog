package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {
}
