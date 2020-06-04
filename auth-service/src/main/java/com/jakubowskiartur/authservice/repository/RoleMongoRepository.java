package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.MongoRole;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RoleMongoRepository extends ReactiveMongoRepository<MongoRole, String> {
}
