package com.jakubowskiartur.postservice.controller;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import com.jakubowskiartur.postservice.services.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService service;

    @GetMapping("/")
    public Flux<Post> receivePosts() {
        return service.receivePosts();
    }

    @GetMapping("/id/{id}")
    public Mono<Post> receivePostById(@PathVariable String id) {
        return service.receivePostById(id);
    }

    @GetMapping("/{username}")
    public Flux<Post> receivePostsByUser(@PathVariable String username) {
        return service.receivePostsByUser(username);
    }

    @PostMapping("/")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<Post>> addPost(@RequestHeader Map<String, String> headers,
    @RequestBody @Valid PostDto post, Mono<Principal> principal) {
        return service.addPost(headers.get("authorization").substring(7), post, principal);
    }

    @PutMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<Post>> updatePost(@PathVariable String id, @RequestBody @Valid PostDto updated,
                                                 Mono<Principal> principal) {
        return service.updatePost(id, updated, principal);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<Void>> deletePost(@PathVariable String id, Mono<Principal> principal) {
        return service.deletePost(id, principal);
    }
}
