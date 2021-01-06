package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import reactor.core.publisher.Flux;

import java.util.List;

public interface TagService {

    Flux<String> receiveListOfTags();
    Flux<Post> receivePostByTag(String tag);
}
