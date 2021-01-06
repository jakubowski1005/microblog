package com.jakubowskiartur.postservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    String id;

    @NotBlank(message = "Post cannot be empty.")
    String content;

    Set<String> tags;

    @NotNull(message = "Post must have an owner.")
    String owner;
}
