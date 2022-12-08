package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.io.IOException;

public abstract class AbstractTypeDeserializer<T> extends StdDelegatingDeserializer<T> {

    private final Class<T> clazz;

    protected AbstractTypeDeserializer(JavaType delegateType, JsonDeserializer<?> defaultDeserializer) {
        super(getConverter(), delegateType, defaultDeserializer);
        this.clazz = (Class<T>) delegateType.getRawClass();
    }

    private static <T> Converter<Object, T> getConverter() {
        return new StdConverter<>() {
            @Override
            public T convert(Object value) {
                return (T) value;
            }
        };
    }

    protected Class<T> getClazz() {
        return clazz;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        //String baseType = typeDeserializer.getTypeIdResolver().idFromBaseType();
        //TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize(p, ctxt);
    }
}
