package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;

import java.util.List;

public interface PostService {

    List<Post> receivePosts();
    Post receivePostById(Long id);
    List<Post> receivePostsByUser(String username);
    Post addPost(Post post);
    Post updatePost(Long id, Post updated);
    void deletePost(Long id);
}
