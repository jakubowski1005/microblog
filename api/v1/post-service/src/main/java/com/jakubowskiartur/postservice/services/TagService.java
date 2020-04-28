package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;

import java.util.List;

public interface TagService {

    List<String> receiveListOfTags();
    List<Post> receivePostByTag(String tag);
}
