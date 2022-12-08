package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import com.weedow.schemaorg.commons.model.JsonLdSubTypes;
import com.weedow.schemaorg.serializer.deserialization.DeserializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends AbstractTypeDeserializer<Enum<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(EnumDeserializer.class);

    private final Class<?> enumType;

    public EnumDeserializer(Class<?> enumType, JavaType delegateType, JsonDeserializer<?> defaultDeserializer) {
        super(delegateType, defaultDeserializer);
        this.enumType = enumType;
    }

    @Override
    protected StdDelegatingDeserializer<Enum<?>> withDelegate(Converter<Object, Enum<?>> converter, JavaType delegateType, JsonDeserializer<?> delegateDeserializer) {
        return this;
    }

    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return getValue(enumType, p.getValueAsString(), ctxt);
    }

    private static <T extends Enum<T>> T getValue(Class<?> type, String v, DeserializationContext ctxt) {
        T value = getValue(type, v);
        return value != null ? value : getSubTypeValue(type, v, ctxt);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> T getValue(Class<?> type, String v) {
        T value;
        try {
            Method from = type.getDeclaredMethod("from", String.class);
            value = (T) from.invoke(null, v);
        } catch (Exception e1) {
            LOG.warn("Could not invoke method 'from': {}", e1.getMessage());
            try {
                LOG.info("Trying to invoke 'valueOf' method");
                value = Enum.valueOf((Class<T>) type, v);
            } catch (Exception e2) {
                LOG.warn("Could not invoke method 'valueOf': {}", e2.getMessage());
                value = null;
            }
        }
        return value;
    }

    private static <T extends Enum<T>> T getSubTypeValue(Class<?> type, String v, DeserializationContext ctxt) {
        JsonLdSubTypes jsonLdSubTypes = type.getDeclaredAnnotation(JsonLdSubTypes.class);
        if (jsonLdSubTypes != null) {
            String[] subTypes = jsonLdSubTypes.value();
            for (String subType : subTypes) {
                Class<?> subClass = DeserializationUtils.findClass(subType, ctxt.getTypeFactory());
                T value = subClass != null ? getValue(subClass, v) : null;
                if (value != null) {
                    return value;
                }
            }
        }

        return null;
    }
}
