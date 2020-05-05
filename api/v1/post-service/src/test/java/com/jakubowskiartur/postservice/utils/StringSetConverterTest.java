package com.jakubowskiartur.postservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StringSetConverterTest {

    StringSetConverter converter;
    Set<String> strings;
    String string;
    String[] stringArr;

    @BeforeEach
    public void setup() {
        converter = new StringSetConverter();
        strings = Set.of("hello", "happy", "summer", "peace");
        string = "hello;happy;summer;peace";
        stringArr = new String[]{"hello", "happy", "summer", "peace"};
    }

    @Test
    public void shouldConvertSetToSingleString() {
        String found = converter.convertToDatabaseColumn(strings);
        String[] foundToArr = found.split(";");

        assertThat(foundToArr).containsExactlyInAnyOrder(stringArr);
    }

    @Test
    public void shouldConvertStringToSet() {
        assertThat(converter.convertToEntityAttribute(string))
                .isEqualTo(strings);
    }
}