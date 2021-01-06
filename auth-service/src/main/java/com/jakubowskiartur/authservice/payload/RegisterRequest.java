package com.jakubowskiartur.authservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{4,64})";

    @Size(min = 4, max = 64, message = "Username must be between 4 and 64 characters long.")
    private String username;

    @Email
    private String email;

    @Pattern(regexp = PASSWORD_PATTERN,
            message = "Password must have at least one uppercase, one lowercase and one digit")
    private String password;
}
