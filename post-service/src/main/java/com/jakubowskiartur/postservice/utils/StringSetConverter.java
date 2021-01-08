package com.jakubowskiartur.postservice.utils;

import java.util.Set;

public class StringSetConverter {

    private static final String SEPARATOR = ";";

    public static String convertToDatabaseColumn(Set<String> attribute) {
        return String.join(SEPARATOR, attribute);
    }

    public static Set<String> convertToEntityAttribute(String dbData) {
        return Set.of(dbData.split(SEPARATOR));
    }
}
