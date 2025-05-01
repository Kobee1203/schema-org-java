package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.spec.DataTypeSpecificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JsonLdDataTypeSerializerModifier extends BeanSerializerModifier {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLdDataTypeSerializerModifier.class);

    private final Map<Class<?>, JsonSerializer<?>> cache = new HashMap<>();

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        JsonSerializer<?> jsonSerializer = getJsonSerializer(serializer, beanDesc.getType());
        return jsonSerializer != null ? jsonSerializer : super.modifySerializer(config, beanDesc, serializer);
    }

    private JsonSerializer<?> getJsonSerializer(JsonSerializer<?> serializer, JavaType type) {
        final Class<?> rawClass = type.getRawClass();

        if (JsonLdDataType.class.isAssignableFrom(rawClass)) {
            return cache.computeIfAbsent(rawClass, clazz -> {
                JsonLdDataTypeSerializer jsonLdDataTypeSerializer = serializer.getClass().getAnnotation(JsonLdDataTypeSerializer.class);
                if (jsonLdDataTypeSerializer != null && (!jsonLdDataTypeSerializer.exactType() || serializer.handledType() == rawClass)) {
                    return serializer;
                }

                @SuppressWarnings("unchecked")
                Class<? extends JsonLdDataType<?>> dataTypeClass = (Class<? extends JsonLdDataType<?>>) rawClass;
                JsonSerializer<?> ser = DataTypeSpecificationService.getInstance().getSerializer(dataTypeClass);
                if (ser == null) {
                    LOG.warn("Could not find the Json-LD DataType Serializer for class {}", rawClass);
                    ser = serializer;
                }
                return ser;
            });
        }
        return null;
    }
}
