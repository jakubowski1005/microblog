package com.jakubowskiartur.postservice.model;

import com.jakubowskiartur.postservice.utils.StringSetConverter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Convert(converter = StringSetConverter.class)
    Set<String> tags;

    @NotNull(message = "Post must have an owner.")
    String owner;
}
