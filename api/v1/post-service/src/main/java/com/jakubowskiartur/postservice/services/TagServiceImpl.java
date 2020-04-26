package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {

    PostRepository repository;

    @Override
    public List<String> receiveListOfTags() {
        Set<String> tags = new HashSet<>();
        repository.findAll().forEach(
                post -> tags.addAll(post.getTags())
        );
        return new ArrayList<>(tags);
    }

    @Override
    public List<Post> receivePostByTag(String tag) {
        List<Post> posts = new ArrayList<>();
        repository.findAll().forEach(post -> {
            if (post.getTags().contains(tag)) {
                posts.add(post);
            }
        });
        return posts;
    }
}
