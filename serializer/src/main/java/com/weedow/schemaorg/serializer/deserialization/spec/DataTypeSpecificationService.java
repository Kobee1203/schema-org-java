package com.weedow.schemaorg.serializer.deserialization.spec;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;
import com.weedow.schemaorg.serializer.converter.impl.*;
import com.weedow.schemaorg.serializer.deserialization.datatype.*;

import java.util.*;

public class DataTypeSpecificationService {

    private static final List<DataTypeSpecification> DATA_TYPES = Arrays.asList(
            new DataTypeSpecification("Boolean", BooleanDeserializer::new, BooleanConverter.INSTANCE),
            new DataTypeSpecification("Integer", IntegerDeserializer::new, IntegerConverter.INSTANCE),
            new DataTypeSpecification("Float", FloatDeserializer::new, FloatConverter.INSTANCE),
            new DataTypeSpecification("Number", NumberDeserializer::new, NumberConverter.INSTANCE),
            new DataTypeSpecification("Time", TimeDeserializer::new, TimeConverter.INSTANCE),
            new DataTypeSpecification("Date", DateDeserializer::new, DateConverter.INSTANCE),
            new DataTypeSpecification("DateTime", DateTimeDeserializer::new, DateTimeConverter.INSTANCE),
            new DataTypeSpecification("URL", URLDeserializer::new, URLConverter.INSTANCE),
            new DataTypeSpecification("CssSelectorType", CssSelectorTypeDeserializer::new, CssSelectorTypeConverter.INSTANCE),
            new DataTypeSpecification("XPathType", XPathTypeDeserializer::new, XPathTypeConverter.INSTANCE),
            new DataTypeSpecification("PronounceableText", PronounceableTextDeserializer::new, PronounceableTextConverter.INSTANCE),
            new DataTypeSpecification("Text", TextDeserializer::new, TextConverter.INSTANCE)
    );

    private static final DataTypeSpecificationService INSTANCE = new DataTypeSpecificationService();

    private final List<String> dataTypeNames = new ArrayList<>();
    private final Map<String, DeserializerFunction> deserializers = new HashMap<>();
    private final List<Converter<Object, ? extends JsonLdDataType<?>>> converters = new ArrayList<>();

    private DataTypeSpecificationService() {
        DATA_TYPES.forEach(dataTypeSpecification -> {
            this.dataTypeNames.add(dataTypeSpecification.getDataTypeName());
            this.deserializers.put(dataTypeSpecification.getDataTypeName(), dataTypeSpecification.getDeserializerFunction());
            this.converters.add(dataTypeSpecification.getConverter());
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
