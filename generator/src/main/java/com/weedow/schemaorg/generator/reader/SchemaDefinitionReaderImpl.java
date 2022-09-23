package com.weedow.schemaorg.generator.reader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SchemaDefinitionReaderImpl implements SchemaDefinitionReader {

    private final JsonMapper jsonMapper = jsonMapper();

    private static JsonMapper jsonMapper() {
        return JsonMapper.builder()
                // Register support for Java 8 date/time types (specified in JSR-310 specification)
                .findAndAddModules()
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
    }

    @Override
    public SchemaDefinition read(InputStream in) throws SchemaDefinitionReaderException {
        SchemaDefinition schemaDefinition;

        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String jsonLd = bufferedReader.lines().collect(Collectors.joining("\n"));
            schemaDefinition = jsonMapper.readValue(jsonLd, SchemaDefinition.class);
        } catch (IOException e) {
            throw new SchemaDefinitionReaderException("Could not read the JSON schema definition: " + e.getMessage(), e);
        }

        return schemaDefinition;
    }
}
