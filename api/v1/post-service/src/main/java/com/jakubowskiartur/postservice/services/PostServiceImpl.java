package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import com.jakubowskiartur.postservice.repository.PostRepository;
import com.jakubowskiartur.postservice.utils.TagFinder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    //TODO Add unit tests

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

        log.info("User {} created new post. [{}]", getLoggedInUser(), new Date());
        return repository.save(postObj);
    }

    @Override
    public Post updatePost(Long id, PostDto updated) {
        checkOwnership(id);
        repository.findById(id).map(post -> {
            post.setContent(updated.getContent());
            post.setTags(tagFinder.find(updated.getContent()));
            return repository.save(post);
        });

        log.info("User {} updated post {}. [{}]", getLoggedInUser(), id, new Date());
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletePost(Long id) {
        checkOwnership(id);
        log.info("User {} deleted post {}. [{}]", getLoggedInUser(), id, new Date());
        repository.deleteById(id);
    }

    private void checkOwnership(Long id) {
        if (!isOwner(id)) {
            throw new ResourceAccessException("You are not allowed to manage the resources.");
        }
    }

    private boolean isOwner(Long id) {
        var post = repository.findById(id);
        if (post.isEmpty()) return false;
        return post
                .get()
                .getOwner()
                .equals(getLoggedInUser());
    }

    private String getLoggedInUser() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
