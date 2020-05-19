package com.jakubowskiartur.authservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    PasswordValidator validator;

    @Test
    public void shouldValidatePasswordCorrectly() {
        //given
        validator = new PasswordValidator();
        String shortPass = "!1Aa";
        String noLowercase = "PASS1234";
        String noCapital = "pass1234";
        String correct = "Pass1234";


        //when
        boolean validation1 = validator.validate(shortPass);
        boolean validation2 = validator.validate(noLowercase);
        boolean validation3 = validator.validate(noCapital);
        boolean validation4 = validator.validate(correct);

        //then
        assertFalse(validation1);
        assertFalse(validation2);
        assertFalse(validation3);
        assertTrue(validation4);
    }

}