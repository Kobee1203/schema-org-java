package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.ConversionService;
import com.weedow.schemaorg.serializer.converter.ConversionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractDataTypeDeserializer extends AbstractTypeDeserializer<JsonLdDataType<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDataTypeDeserializer.class);

    private final transient ConversionService conversionService = new ConversionServiceImpl();

    protected AbstractDataTypeDeserializer(Class<?> clazz) {
        super(clazz);
    }

    protected abstract Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException;

    @Override
    public JsonLdDataType<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonLdDataType<?> jsonLdDataType = null;
        try {
            jsonLdDataType = conversionService.convert(getValue(p, ctxt), getClazz());
        } catch (Exception e) {
            LOG.warn("Could not convert value: {}", e.getMessage());
        }
        return jsonLdDataType;
    }
}
