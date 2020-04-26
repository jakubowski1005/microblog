package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import com.jakubowskiartur.postservice.repository.PostRepository;
import com.jakubowskiartur.postservice.util.TagFinder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository repository;
    TagFinder tagFinder;

    @Override
    public List<Post> receivePosts() {
        return repository.findAll();
    }

    @Override
    public Post receivePostById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Post> receivePostsByUser(String username) {
        return repository.getAllByOwner(username);
    }

    @Override
    public Post addPost(PostDto post) {
        Post postObj = Post.builder()
                .content(post.getContent())
                .tags(tagFinder.find(post.getContent()))
                .owner(getLoggedInUser())
                .build();

        return repository.save(postObj);
    }

    @Override
    public Post updatePost(Long id, PostDto updated) {
        repository.findById(id).map(post -> {
            post.setContent(updated.getContent());
            post.setTags(tagFinder.find(updated.getContent()));
            return repository.save(post);
        });
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    private String getLoggedInUser() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
