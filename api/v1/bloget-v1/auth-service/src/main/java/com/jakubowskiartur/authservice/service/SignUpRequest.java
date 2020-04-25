package com.jakubowskiartur.authservice.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank(message = "Username is mandatory.")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters.")
    String login;

    @NotBlank(message = "E-mail is mandatory.")
    @Email(message = "E-mail must be valid.")
    String email;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 8, max = 40, message = "Password must contain at least 8 characters.")
    String password;
}