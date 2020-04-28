package com.jakubowskiartur.postservice.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Set;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    private static final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return String.join(SEPARATOR, attribute);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return Set.of(dbData.split(SEPARATOR));
    }
}
