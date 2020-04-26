package com.jakubowskiartur.postservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank(message = "Post cannot be empty.")
    String content;

    @OneToMany
    Set<String> tags;

    @NotNull(message = "Post must have an owner.")
    String owner;
}
