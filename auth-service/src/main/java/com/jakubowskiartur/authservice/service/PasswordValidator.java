//package com.jakubowskiartur.authservice.service;
//
//import org.springframework.stereotype.Service;
//
//import java.util.regex.Pattern;
//
//@Deprecated
//@Service
//public class PasswordValidator {
//
//    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
//
//    private Pattern pattern;
//
//    public PasswordValidator() {
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//    }
//
//    public boolean validate(final String password) {
//        return pattern
//                .matcher(password)
//                .matches();
//    }
//}
