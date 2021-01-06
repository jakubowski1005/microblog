//package com.jakubowskiartur.postservice.services;
//
//import com.jakubowskiartur.postservice.model.Post;
//import com.jakubowskiartur.postservice.repository.PostRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class TagServiceImplTest {
//
//    private TagService service;
//    private PostRepository postRepository;
//    private List<Post> posts;
//    private List<String> tags;
//
//    @BeforeEach
//    public void setup() {
//        postRepository = mock(PostRepository.class);
//        service = new TagServiceImpl(postRepository);
//
//        posts = Arrays.asList(
//                new Post(1L, "content1", Set.of("hello", "error"), "mock"),
//                new Post(2L, "content2", Set.of("hello", "dev"), "mock"),
//                new Post(3L, "content3", Set.of("poland", "view"), "mock"),
//                new Post(4L, "content4", Set.of("dick", "pick"), "mock"),
//                new Post(5L, "content5", Set.of("error", "poland"), "mock")
//        );
//
//        tags = Arrays.asList("hello", "error", "dev", "poland", "view", "dick", "pick");
//    }
//
//    @Test
//    public void shouldReturnListOfTags() {
//        //when
//        when(postRepository.findAll()).thenReturn(posts);
//        var foundTags = service.receiveListOfTags();
//
//        //then
//        assertThat(foundTags)
//                .containsExactlyInAnyOrder(tags.toArray(new String[0]));
//    }
//
//    @Test
//    public void shouldReturnPostsByTag() {
//        //given
//        var correctPosts = Arrays.asList(
//                new Post(3L, "content3", Set.of("poland", "view"), "mock"),
//                new Post(5L, "content5", Set.of("error", "poland"), "mock")
//        );
//
//        //when
//        when(postRepository.findAll()).thenReturn(posts);
//        var postsByTag = service.receivePostByTag("poland");
//
//        //then
//        assertThat(postsByTag).isEqualTo(correctPosts);
//    }
//
//}