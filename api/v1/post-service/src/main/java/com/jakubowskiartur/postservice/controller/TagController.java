package com.jakubowskiartur.postservice.controller;

import com.jakubowskiartur.postservice.model.Post;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {

    //TagService service;

    @GetMapping("/tags")
    public List<String> receiveListOfTags() {
        return null;
    }

    @GetMapping("/tags/{tag}")
    public List<Post> receivePostByTag(@PathVariable String tag) {
        return null;
    }
}
