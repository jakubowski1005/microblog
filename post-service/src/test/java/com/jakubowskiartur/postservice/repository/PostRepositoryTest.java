package com.jakubowskiartur.postservice.repository;

import com.jakubowskiartur.postservice.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration
class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    private List<Post> posts;

    @BeforeEach
    public void setup() {
        posts = repository.findAll();
    }

    @Test
    public void shouldFindPostsByOwner() {
        //when
        var found = repository.getAllByOwner("mock");

        //expect
        assertThat(found.size()).isEqualTo(3);
        assertThat(found).contains(posts.get(0), posts.get(1), posts.get(2));
    }
}