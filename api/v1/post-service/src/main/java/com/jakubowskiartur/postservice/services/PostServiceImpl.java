package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository repository;

    @Override
    public List<Post> receivePosts() {
        return null;
    }

    @Override
    public Post receivePostById(Long id) {
        return null;
    }

    @Override
    public List<Post> receivePostsByUser(String username) {
        return null;
    }

    @Override
    public Post addPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Long id, Post updated) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }
}
