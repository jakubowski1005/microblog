package com.jakubowskiartur.postservice.controller;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import com.jakubowskiartur.postservice.services.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    //TODO Add integration tests

    PostService service;

    @GetMapping("/posts")
    public List<Post> receivePosts() {
        return service.receivePosts();
    }

    @GetMapping("/posts/id/{id}")
    public Post receivePostById(@PathVariable Long id) {
        return service.receivePostById(id);
    }

    @GetMapping("/posts/{username}")
    public List<Post> receivePostsByUser(@PathVariable String username) {
        return service.receivePostsByUser(username);
    }

    @PostMapping("/posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addPost(@RequestBody @Valid PostDto post) {
        Post created = service.addPost(post);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/posts/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody @Valid PostDto updated) {
        Post updatedPost = service.updatePost(id, updated);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
