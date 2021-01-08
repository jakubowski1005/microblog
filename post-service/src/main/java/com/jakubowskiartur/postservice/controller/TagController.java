package com.jakubowskiartur.postservice.controller;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.services.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {

    //TODO Add integration tests

    TagService service;

    @GetMapping("/tags")
    public Flux<String> receiveListOfTags() {
        return service.receiveListOfTags();
    }

    @GetMapping("/tags/{tag}")
    public Flux<Post> receivePostByTag(@PathVariable String tag) {
        return service.receivePostByTag(tag);
    }
}
