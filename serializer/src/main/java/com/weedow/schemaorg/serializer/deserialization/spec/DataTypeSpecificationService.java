package com.weedow.schemaorg.serializer.deserialization.spec;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;
import com.weedow.schemaorg.serializer.converter.impl.*;
import com.weedow.schemaorg.serializer.deserialization.datatype.*;

import java.util.*;

@SuppressWarnings("java:S6548")
public class DataTypeSpecificationService {

    private static final List<DataTypeSpecification> DATA_TYPES = Arrays.asList(
            new DataTypeSpecification(SchemaDataType.BOOLEAN, BooleanDeserializer::new, BooleanConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.INTEGER, IntegerDeserializer::new, IntegerConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.FLOAT, FloatDeserializer::new, FloatConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.NUMBER, NumberDeserializer::new, NumberConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.TIME, TimeDeserializer::new, TimeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.DATE, DateDeserializer::new, DateConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.DATE_TIME, DateTimeDeserializer::new, DateTimeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.URL, URLDeserializer::new, URLConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.CSS_SELECTOR_TYPE, CssSelectorTypeDeserializer::new, CssSelectorTypeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.XPATH_TYPE, XPathTypeDeserializer::new, XPathTypeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.PRONOUNCEABLE_TEXT, PronounceableTextDeserializer::new, PronounceableTextConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.TEXT, TextDeserializer::new, TextConverter.INSTANCE)
    );

    private static final DataTypeSpecificationService INSTANCE = new DataTypeSpecificationService();

    private final List<String> dataTypeNames = new ArrayList<>();
    private final Map<String, DeserializerFunction> deserializers = new HashMap<>();
    private final List<Converter<Object, ? extends JsonLdDataType<?>>> converters = new ArrayList<>();

    private DataTypeSpecificationService() {
        DATA_TYPES.forEach(dataTypeSpecification -> {
            final String dataTypeName = dataTypeSpecification.dataType().getName();

            this.dataTypeNames.add(dataTypeName);
            this.deserializers.put(dataTypeName, dataTypeSpecification.deserializerFunction());
            this.converters.add(dataTypeSpecification.converter());
        });
    }

    public static DataTypeSpecificationService getInstance() {
        return INSTANCE;
    }

    public List<Converter<Object, ? extends JsonLdDataType<?>>> getConverters() {
        return converters;
    }

    public Comparator<Class<JsonLdDataType<?>>> getDataTypeComparator() {
        return Comparator.comparingInt((Class<JsonLdDataType<?>> clazz) -> dataTypeNames.indexOf(clazz.getSimpleName()));
    }

    public JsonDeserializer<JsonLdDataType<?>> getDeserializer(Class<?> clazz) {
        return deserializers.get(clazz.getSimpleName()).apply(clazz);
    }
}
