package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;

import java.util.List;

public interface PostService {

    List<Post> receivePosts();
    Post receivePostById(Long id);
    List<Post> receivePostsByUser(String username);
    Post addPost(PostDto post);
    Post updatePost(Long id, PostDto updated);
    void deletePost(Long id);
}
