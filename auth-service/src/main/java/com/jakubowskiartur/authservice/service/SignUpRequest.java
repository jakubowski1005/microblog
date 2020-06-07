//package com.jakubowskiartur.authservice.service;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.experimental.FieldDefaults;
//
//import javax.validation.constraints.*;
//
//@Data
//@AllArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//public class SignUpRequest {
//
//    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
//
//    @NotBlank(message = "Username is mandatory.")
//    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters.")
//    String login;
//
//    @NotBlank(message = "E-mail is mandatory.")
//    @Email(message = "E-mail must be valid.")
//    String email;
//
//    @NotBlank(message = "Password is mandatory.")
//    @Size(min = 8, max = 40, message = "Password must contain at least 8 characters.")
//    @Pattern(regexp = PASSWORD_PATTERN,
//            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit and 8 - 40 characters."
//    )
//    String password;
//}
