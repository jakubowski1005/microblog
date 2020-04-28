package com.jakubowskiartur.postservice.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StringSetConverterTest {

    StringSetConverter converter;
    Set<String> strings;
    String string;

    @BeforeEach
    public void setup() {
        converter = new StringSetConverter();
        strings = Set.of("hello", "happy", "summer", "peace");
        string = "hello;happy;summer;peace";
    }

    @Disabled
    @Test
    public void shouldConvertSetToSingleString() {
        assertThat(converter.convertToDatabaseColumn(strings))
                .isEqualTo(string);
    }

    @Test
    public void shouldConvertStringToSet() {
        assertThat(converter.convertToEntityAttribute(string))
                .isEqualTo(strings);
    }

}