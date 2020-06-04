package com.jakubowskiartur.postservice.repository;

import com.jakubowskiartur.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> getAllByOwner(@NotNull(message = "Post must have an owner.") String owner);
}
