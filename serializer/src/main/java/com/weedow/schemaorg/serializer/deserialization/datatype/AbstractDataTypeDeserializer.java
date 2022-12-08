package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public abstract class AbstractDataTypeDeserializer extends AbstractTypeDeserializer<JsonLdDataType<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDataTypeDeserializer.class);

    private final transient Method instanceMethod;

    protected AbstractDataTypeDeserializer(JavaType delegateType, JsonDeserializer<?> defaultDeserializer) {
        super(delegateType, defaultDeserializer);
        this.instanceMethod = getInstanceMethod();
    }

    private Method getInstanceMethod() {
        return Arrays.stream(getClazz().getDeclaredMethods())
                .filter(method -> "of".equals(method.getName()) && Modifier.isStatic(method.getModifiers()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find the 'of' method"));
    }

    protected abstract Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException;

    @Override
    public JsonLdDataType<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonLdDataType<?> jsonLdDataType = null;
        try {
            jsonLdDataType = (JsonLdDataType<?>) instanceMethod.invoke(null, getValue(p, ctxt));
        } catch (Exception e) {
            LOG.warn("Could not correctly invoke the method {}: {}", instanceMethod, e.getMessage());
        }
        return jsonLdDataType != null ? jsonLdDataType : super.deserialize(p, ctxt);
    }
}
