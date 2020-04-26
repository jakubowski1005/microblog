package com.jakubowskiartur.postservice.util;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TagFinder {

    public Set<String> find(String content) {
        Set<String> tags = new HashSet<>();
        Pattern HASH_PATTERN = Pattern.compile("#(\\w+)");
        Matcher matcher = HASH_PATTERN.matcher(content);

        while (matcher.find()) {
            tags.add(matcher.group(1));
        }

        return tags;
    }
}
