package com.weedow.schemaorg.serializer.serialization.datatype;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public abstract class AbstractTypeSerializer<T> extends StdSerializer<T> {

    protected AbstractTypeSerializer(Class<?> clazz) {
        super(clazz, false);
    }

    protected abstract Object getValue(T value);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object v = getValue(value);
        Class<?> type = v.getClass();
        if (Boolean.class.equals(type)) {
            gen.writeBoolean((Boolean) v);
        } else if (Number.class.isAssignableFrom(type)) {
            gen.writeNumber(v.toString());
        } else if (LocalDateTime.class.isAssignableFrom(type)) {
            gen.writeString(DateTimeFormatter.ISO_DATE_TIME.format((TemporalAccessor) v));
        } else if (LocalDate.class.isAssignableFrom(type)) {
            gen.writeString(DateTimeFormatter.ISO_DATE.format((TemporalAccessor) v));
        } else if (LocalTime.class.isAssignableFrom(type)) {
            gen.writeString(DateTimeFormatter.ISO_TIME.format((TemporalAccessor) v));
        } else { // Handle String type and unknown types as String value
            gen.writeString(v.toString());
        }
    }

    @Override
    public void serializeWithType(T value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        serialize(value, gen, provider);
    }
}
