package com.weedow.schemaorg.serializer.spec;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.serializer.converter.Converter;
import com.weedow.schemaorg.serializer.converter.impl.*;
import com.weedow.schemaorg.serializer.deserialization.datatype.*;
import com.weedow.schemaorg.serializer.serialization.datatype.*;

import java.util.*;

@SuppressWarnings("java:S6548")
public class DataTypeSpecificationService {

    private static final List<DataTypeSpecification> DATA_TYPES = Arrays.asList(
            new DataTypeSpecification(SchemaDataType.BOOLEAN, BooleanSerializer::new, BooleanDeserializer::new, BooleanConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.INTEGER, IntegerSerializer::new, IntegerDeserializer::new, IntegerConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.FLOAT, FloatSerializer::new, FloatDeserializer::new, FloatConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.NUMBER, NumberSerializer::new, NumberDeserializer::new, NumberConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.TIME, TimeSerializer::new, TimeDeserializer::new, TimeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.DATE, DateSerializer::new, DateDeserializer::new, DateConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.DATE_TIME, DateTimeSerializer::new, DateTimeDeserializer::new, DateTimeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.URL, URLSerializer::new, URLDeserializer::new, URLConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.CSS_SELECTOR_TYPE, CssSelectorTypeSerializer::new, CssSelectorTypeDeserializer::new, CssSelectorTypeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.XPATH_TYPE, XPathTypeSerializer::new, XPathTypeDeserializer::new, XPathTypeConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.PRONOUNCEABLE_TEXT, PronounceableTextSerializer::new, PronounceableTextDeserializer::new, PronounceableTextConverter.INSTANCE),
            new DataTypeSpecification(SchemaDataType.TEXT, TextSerializer::new, TextDeserializer::new, TextConverter.INSTANCE)
    );

    private static final DataTypeSpecificationService INSTANCE = new DataTypeSpecificationService();

    private final List<String> dataTypeNames = new ArrayList<>();
    private final Map<String, SerializerFunction> serializers = new HashMap<>();
    private final Map<String, DeserializerFunction> deserializers = new HashMap<>();
    private final List<Converter<Object, ? extends JsonLdDataType<?>>> converters = new ArrayList<>();

    private DataTypeSpecificationService() {
        DATA_TYPES.forEach(dataTypeSpecification -> {
            final String dataTypeName = dataTypeSpecification.dataType().getName();

            this.dataTypeNames.add(dataTypeName);
            this.serializers.put(dataTypeName, dataTypeSpecification.serializerFunction());
            this.deserializers.put(dataTypeName, dataTypeSpecification.deserializerFunction());
            this.converters.add(dataTypeSpecification.converter());
        });
    }

    public static DataTypeSpecificationService getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("java:S1452")
    public List<Converter<Object, ? extends JsonLdDataType<?>>> getConverters() {
        return converters;
    }

    @SuppressWarnings("java:S1452")
    public Comparator<Class<JsonLdDataType<?>>> getDataTypeComparator() {
        return Comparator.comparingInt((Class<JsonLdDataType<?>> clazz) -> dataTypeNames.indexOf(clazz.getSimpleName()));
    }

    @SuppressWarnings("java:S1452")
    public JsonSerializer<? extends JsonLdDataType<?>> getSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        return serializers.get(clazz.getSimpleName()).apply(clazz);
    }

    @SuppressWarnings("java:S1452")
    public JsonDeserializer<? extends JsonLdDataType<?>> getDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        return deserializers.get(clazz.getSimpleName()).apply(clazz);
    }
}
