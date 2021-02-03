package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

public interface PostService {

    Flux<Post> receivePosts();
    Mono<Post> receivePostById(String id);
    Flux<Post> receivePostsByUser(String username);
    Mono<ResponseEntity<Post>> addPost(String token, PostDto post);
    Mono<ResponseEntity<Post>> updatePost(String id, PostDto updated, Mono<Principal> principal);
    Mono<ResponseEntity<Void>> deletePost(String id, Mono<Principal> principal);
}
