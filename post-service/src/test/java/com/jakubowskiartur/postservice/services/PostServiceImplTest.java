package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.repository.PostRepository;
import com.jakubowskiartur.postservice.utils.TagFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceImplTest {

    private PostService service;
    private PostRepository repository;

    private Post post1;
    private Post post2;
    private Post post3;
    private List<Post> posts;

    @BeforeEach
    public void setup() {
        repository = mock(PostRepository.class);
        service = new PostServiceImpl(repository, mock(TagFinder.class));

        post1 = new Post(1L, "Hello #world #java", Set.of("world", "java"), "mock");
        post2 = new Post(2L, "I am #handsome", Set.of("handsome"), "dick");
        post3 = new Post(3L, "#love #peace #sex", Set.of("love", "peace", "sex"), "mock");
        posts = Arrays.asList(post1, post2, post3);
    }

    @Test
    public void shouldReturnListOfPosts() {
        //when
        when(repository.findAll())
                .thenReturn(posts);

        //expect
        assertThat(service.receivePosts())
                .containsExactlyInAnyOrder(posts.toArray(new Post[0]));
    }

    @Test
    public void shouldReturnPostById() {
        //when
        when(repository.findById(2L))
                .thenReturn(Optional.of(post2));

        //expect
        assertThat(service.receivePostById(2L))
                .isEqualTo(post2);
    }

    @Test
    public void shouldReturnPostsByUser() {
        //when
        when(repository.getAllByOwner("mock"))
                .thenReturn(Arrays.asList(post1, post3));

        //expect
        assertThat(service.receivePostsByUser("mock"))
                .containsExactlyInAnyOrder(post1, post3);
    }
}