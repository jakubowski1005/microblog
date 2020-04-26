package com.jakubowskiartur.postservice.controller;

import com.jakubowskiartur.postservice.model.Post;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

//    PostService service;

    @GetMapping("/posts")
    public List<Post> receivePosts() {
        return null;
    }

    @GetMapping("/posts/id/{id}")
    public Post receivePostById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/posts/{username}")
    public List<Post> receivePostsByUser(@PathVariable String username) {
        return null;
    }

    @PostMapping("/posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        return null;
    }

    @PutMapping("/posts/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post updated) {
        return null;
    }

    @DeleteMapping("/posts/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return null;
    }
}
