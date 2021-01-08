package com.jakubowskiartur.postservice.repository;

import com.jakubowskiartur.postservice.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> getAllByOwner(@NotNull(message = "Post must have an owner.") String owner);
}
