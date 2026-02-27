package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.ConversionService;
import com.weedow.schemaorg.serializer.converter.ConversionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Abstract deserializer for Schema.org data types that uses conversion service.
 * Extracts raw values from JSON and converts them to the appropriate JsonLdDataType instances.
 *
 * @param <T> the JsonLdDataType subclass to deserialize
 */
public abstract class AbstractDataTypeDeserializer<T extends JsonLdDataType<?>> extends AbstractTypeDeserializer<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDataTypeDeserializer.class);

    private final transient ConversionService conversionService = new ConversionServiceImpl();

    /**
     * Constructs a deserializer for the specified JsonLdDataType class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    protected AbstractDataTypeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    /**
     * Extracts the raw value from the JSON parser.
     *
     * @param p the JSON parser
     * @param ctxt the deserialization context
     * @return the extracted raw value
     * @throws IOException if an I/O error occurs
     */
    protected abstract Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        T jsonLdDataType = null;
        try {
            jsonLdDataType = conversionService.convert(getValue(p, ctxt), handledType());
        } catch (Exception e) {
            LOG.warn("Could not convert value: {}", e.getMessage());
        }
        return jsonLdDataType;
    }
}
