package com.jakubowskiartur.postservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {

    @NotNull
    @NotBlank(message = "Post must have content.")
    @Size(min = 1, max = 2048, message = "Post must contain between 1 and 2048 characters.")
    String content;
}
