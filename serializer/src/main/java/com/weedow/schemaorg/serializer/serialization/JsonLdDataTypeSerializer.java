package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class JsonLdDataTypeSerializer extends StdSerializer<JsonLdDataType<?>> {

    public JsonLdDataTypeSerializer() {
        super(JsonLdDataType.class, false);
    }

    @Override
    public void serialize(JsonLdDataType<?> jsonLdDataType, JsonGenerator gen, SerializerProvider provider) throws IOException {
        final Object dataTypeValue = jsonLdDataType.getValue();
        final Type dataType = getDataType(jsonLdDataType);
        if (Boolean.class.equals(dataType)) {
            gen.writeBoolean((Boolean) dataTypeValue);
        } else if (Number.class.isAssignableFrom((Class<?>) dataType)) {
            gen.writeNumber(dataTypeValue.toString());
        } else if (LocalDateTime.class.isAssignableFrom((Class<?>) dataType)) {
            gen.writeString(DateTimeFormatter.ISO_DATE_TIME.format((TemporalAccessor) dataTypeValue));
        } else if (LocalDate.class.isAssignableFrom((Class<?>) dataType)) {
            gen.writeString(DateTimeFormatter.ISO_DATE.format((TemporalAccessor) dataTypeValue));
        } else if (LocalTime.class.isAssignableFrom((Class<?>) dataType)) {
            gen.writeString(DateTimeFormatter.ISO_TIME.format((TemporalAccessor) dataTypeValue));
        } else { // Handle String type and unknown types as String value
            gen.writeString(dataTypeValue.toString());
        }
    }

    @SuppressWarnings("unchecked")
    private Type getDataType(JsonLdDataType<?> jsonLdDataType) {
        Type type = null;
        Class<? extends JsonLdDataType<?>> dataTypeClass = (Class<? extends JsonLdDataType<?>>) jsonLdDataType.getClass();
        Type genericSuperclass = dataTypeClass.getGenericSuperclass();
        while (genericSuperclass != null && !(genericSuperclass instanceof ParameterizedType)) {
            genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
        }
        if (genericSuperclass != null) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                type = actualTypeArguments[0];
            }
        }
        return type;
    }
}
