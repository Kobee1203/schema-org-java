package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.deserialization.datatype.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class JsonLdDataTypeDeserializerModifier extends BeanDeserializerModifier {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLdDataTypeDeserializerModifier.class);

    private static final Map<String, BiFunction<JavaType, JsonDeserializer<?>, JsonDeserializer<JsonLdDataType<?>>>> JSON_LD_DATATYPE_DESERIALIZERS = Map.ofEntries(
            Map.entry("Boolean", BooleanDeserializer::new),
            Map.entry("Text", TextDeserializer::new),
            Map.entry("URL", URLDeserializer::new),
            Map.entry("CssSelectorType", CssSelectorTypeDeserializer::new),
            Map.entry("PronounceableText", PronounceableTextDeserializer::new),
            Map.entry("XPathType", XPathTypeDeserializer::new),
            Map.entry("Number", NumberDeserializer::new),
            Map.entry("Float", FloatDeserializer::new),
            Map.entry("Integer", IntegerDeserializer::new),
            Map.entry("Date", DateDeserializer::new),
            Map.entry("Time", TimeDeserializer::new),
            Map.entry("DateTime", DateTimeDeserializer::new)
    );

    private final Map<Class<?>, JsonDeserializer<?>> cache = new HashMap<>();

    private Class<?> enumerationClass;

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        final JavaType type = beanDesc.getType();
        final Class<?> rawClass = type.getRawClass();
        if (JsonLdDataType.class.isAssignableFrom(rawClass)) {
            return cache.computeIfAbsent(rawClass, clazz -> {
                JsonDeserializer<?> des = JSON_LD_DATATYPE_DESERIALIZERS.get(rawClass.getSimpleName()).apply(type, deserializer);
                if (des == null) {
                    LOG.warn("Could not find the Json-LD DataType Deserializer for class {}", rawClass);
                    des = deserializer;
                }
                return des;
            });
        } else if (getEnumerationClass(config).isAssignableFrom(rawClass)) {
            Class<?> enumClass = DeserializationUtils.findClass(rawClass.getSimpleName(), config.getTypeFactory());
            if (enumClass != null) {
                return cache.computeIfAbsent(rawClass, clazz -> new EnumDeserializer(enumClass, type, deserializer));
            } else {
                LOG.warn("Could not find the Class from class name {}", rawClass);
            }
        }
        return super.modifyDeserializer(config, beanDesc, deserializer);
    }

    private Class<?> getEnumerationClass(DeserializationConfig config) {
        if (this.enumerationClass == null) {
            this.enumerationClass = DeserializationUtils.findClass("interface.Enumeration", config.getTypeFactory());
            if (this.enumerationClass == null) {
                LOG.debug("Enumeration interface is not found: it is assumed that there is no schema.org Enumerations available.");
                this.enumerationClass = NoClass.class;
            }
        }
        return this.enumerationClass;
    }
}
