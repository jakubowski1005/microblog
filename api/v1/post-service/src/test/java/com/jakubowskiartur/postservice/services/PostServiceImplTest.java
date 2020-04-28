package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
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
    private TagFinder tagFinder;

    private Post post1;
    private Post post2;
    private Post post3;
    private List<Post> posts;
    private PostDto postDto1;
    private PostDto postDto2;
    private PostDto postDto3;

    @BeforeEach
    public void setup() {
        tagFinder = mock(TagFinder.class);
        repository = mock(PostRepository.class);
        service = new PostServiceImpl(repository, tagFinder);

        post1 = new Post(1L, "Hello #world #java", Set.of("world", "java"), "mock");
        postDto1 = new PostDto("Hello #world #java");
        post2 = new Post(2L, "I am #handsome", Set.of("handsome"), "dick");
        postDto2 = new PostDto("I am #handsome");
        post3 = new Post(3L, "#love #peace #sex", Set.of("love", "peace", "sex"), "mock");
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
        when(repository.findById(1L))
                .thenReturn(Optional.of(post1));

        //expect
        assertThat(service.receivePostById(1L))
                .isEqualTo(post1);
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

//    @Test
//    public void shouldThrowException_whenUserNotOwner() {
//        //when
////        var ctx = mock(SecurityContext.class);
////        when(ctx.getAuthentication().getPrincipal()).thenReturn("mock");
////        SecurityContextHolder.setContext(ctx);
//        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn("mock");
//
//        when(repository.findById(2L)).thenReturn(Optional.of(post2));
//
//        //then
//        assertThrows(ResourceAccessException.class, () -> service.deletePost(2L));
//    }

}