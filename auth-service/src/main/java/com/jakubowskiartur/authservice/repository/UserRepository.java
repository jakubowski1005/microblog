package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.RegisterRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

   Mono<User> findByUsername(String username);
   Mono<User> findByUsernameOrEmail(String username, String email);

   //@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
   //boolean existsByUsernameOrEmail(String username, String email);
   boolean existsByUsernameOrEmail(String username, String email);

}
