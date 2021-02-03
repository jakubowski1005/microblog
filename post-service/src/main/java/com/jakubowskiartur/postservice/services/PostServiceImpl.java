package com.jakubowskiartur.postservice.services;

import com.jakubowskiartur.postservice.model.Post;
import com.jakubowskiartur.postservice.model.PostDto;
import com.jakubowskiartur.postservice.repository.PostRepository;
import com.jakubowskiartur.postservice.utils.JwtUtil;
import com.jakubowskiartur.postservice.utils.TagFinder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    //TODO Add unit tests

    PostRepository repository;
    TagFinder tagFinder;
    JwtUtil jwt;

    @Override
    public Flux<Post> receivePosts() {
        return repository.findAll();
    }

    @Override
    public Mono<Post> receivePostById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Post> receivePostsByUser(String username) {
        return repository.getAllByOwner(username);
    }

    @Override
    public Mono<ResponseEntity<Post>> addPost(String token, PostDto post) {
        return Mono.just(post)
                .log()
                .flatMap(content -> repository.save(
                        Post.builder()
                                .content(content.getContent())
                                .tags(tagFinder.find(content.getContent()))
                                .owner(jwt.getUsernameFromToken(token))
                                .createdAt(new Date())
                                .build()))
                .log()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .log();
//        return receivePrincipal(principal)
//                .log()
//                .flatMap(owner -> repository.save(
//                        Post.builder()
//                                .content(post.getContent())
//                                .tags(tagFinder.find(post.getContent()))
//                                .owner(jwt.getUsernameFromToken(token.substring(7)))
//                                .createdAt(new Date())
//                                .build()))
//                .log()
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build())
//                .log();
    }

    @Override
    public Mono<ResponseEntity<Post>> updatePost(String id, PostDto updated, Mono<Principal> principal) {
        return receivePrincipal(principal)
                .flatMapMany(repository::getAllByOwner)
                .filter(post -> post.getId().equals(id))
                .next()
                .flatMap(post -> {
                    post.setContent(updated.getContent());
                    post.setTags(tagFinder.find(updated.getContent()));
                    return repository.save(post);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .log();
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePost(String id, Mono<Principal> principal) {
        return receivePrincipal(principal)
                .flatMapMany(repository::getAllByOwner)
                .filter(post -> post.getId().equals(id))
                .next()
                .flatMap(repository::delete)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .log();
    }

    private Mono<String> receivePrincipal(Mono<Principal> principal) {
        return principal.log()
                .map(Principal::toString)
                .log();
    }
}
