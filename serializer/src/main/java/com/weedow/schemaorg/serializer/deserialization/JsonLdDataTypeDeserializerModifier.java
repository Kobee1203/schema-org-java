package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.deserialization.datatype.EnumDeserializer;
import com.weedow.schemaorg.serializer.spec.DataTypeSpecificationService;
import com.weedow.schemaorg.serializer.utils.SerializerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JsonLdDataTypeDeserializerModifier extends BeanDeserializerModifier {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLdDataTypeDeserializerModifier.class);

    private final Map<Class<?>, JsonDeserializer<?>> cache = new HashMap<>();

    private Class<?> enumerationClass;

    @Override
    public JsonDeserializer<?> modifyCollectionDeserializer(DeserializationConfig config, CollectionType type, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        JsonDeserializer<?> singleValueDeserializer = getJsonDeserializer(config, deserializer, beanDesc.getType());
        return new JsonLdCollectionDeserializer(config, beanDesc, deserializer, singleValueDeserializer);
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        JsonDeserializer<?> jsonDeserializer = getJsonDeserializer(config, deserializer, beanDesc.getType());
        return jsonDeserializer != null ? jsonDeserializer : super.modifyDeserializer(config, beanDesc, deserializer);
    }

    private JsonDeserializer<?> getJsonDeserializer(DeserializationConfig config, JsonDeserializer<?> deserializer, JavaType type) {
        final Class<?> rawClass = type.getRawClass();
        if (JsonLdDataType.class.isAssignableFrom(rawClass)) {
            return cache.computeIfAbsent(rawClass, clazz -> {
                JsonLdDataTypeDeserializer jsonLdDataTypeDeserializer = deserializer.getClass().getAnnotation(JsonLdDataTypeDeserializer.class);
                if (jsonLdDataTypeDeserializer != null) {
                    return deserializer;
                }

                @SuppressWarnings("unchecked")
                Class<? extends JsonLdDataType<?>> dataTypeClass = (Class<? extends JsonLdDataType<?>>) rawClass;
                JsonDeserializer<?> des = DataTypeSpecificationService.getInstance().getDeserializer(dataTypeClass);
                if (des == null) {
                    LOG.warn("Could not find the Json-LD DataType Deserializer for class {}", rawClass);
                    des = deserializer;
                }
                return des;
            });
        } else if (getEnumerationClass(config).isAssignableFrom(rawClass)) {
            Class<?> enumClass = SerializerUtils.findClass(rawClass.getSimpleName(), config.getTypeFactory());
            if (enumClass != null) {
                return cache.computeIfAbsent(rawClass, clazz -> new EnumDeserializer(enumClass));
            } else {
                LOG.warn("Could not find the Class from class name {}", rawClass);
            }
        } else if (Collection.class.isAssignableFrom(rawClass)) {
            JavaType contentType = type.getContentType();
            if (contentType.hasRawClass(Object.class)) {
                return new UntypedObjectDeserializer(null, null);
            } else {
                return getJsonDeserializer(config, deserializer, contentType);
            }
        }
        return null;
    }

    private Class<?> getEnumerationClass(DeserializationConfig config) {
        if (this.enumerationClass == null) {
            this.enumerationClass = SerializerUtils.findClass("interface.Enumeration", config.getTypeFactory());
            if (this.enumerationClass == null) {
                LOG.debug("Enumeration interface is not found: it is assumed that there is no schema.org Enumerations available.");
                this.enumerationClass = NoClass.class;
            }
        }
        return this.enumerationClass;
    }
}
