package com.jakubowskiartur.postservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TagFinderTest {

    TagFinder tagFinder;

    @BeforeEach
    public void setup() {
        tagFinder = new TagFinder();
    }

    @Test
    public void shouldFindTagsCorrectly() {
        //given
        var str1 = "Hello in my #world boy.";
        var set1 = Set.of("world");
        var str2 = "I like #dogs more than #cats";
        var set2 = Set.of("dogs", "cats");
        var str3 = "You are very #handsome and #handsome and #pretty";
        var set3 = Set.of("handsome", "pretty");

        //when
        var result1 = tagFinder.find(str1);
        var result2 = tagFinder.find(str2);
        var result3 = tagFinder.find(str3);

        //expect
        assertThat(result1).isEqualTo(set1);
        assertThat(result2).isEqualTo(set2);
        assertThat(result3).isEqualTo(set3);
    }
}