package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<String, User> {
}
