package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {

    PostRepository repository;

    @Override
    public Flux<String> receiveListOfTags() {
        return repository.findAll()
                .map(Post::getTags)
                .flatMap(Flux::fromIterable)
                .distinct()
                .log();
    }

    @Override
    public Flux<Post> receivePostByTag(String tag) {
        return repository.findAll()
                .filter(post -> post.getTags().contains(tag))
                .log();
    }
}
