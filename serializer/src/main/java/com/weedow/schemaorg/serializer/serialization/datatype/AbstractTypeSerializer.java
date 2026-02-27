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

/**
 * Abstract base serializer for Schema.org data types to JSON-LD.
 * Handles serialization of various Java types to their JSON-LD representations.
 *
 * @param <T> the type to serialize
 */
public abstract class AbstractTypeSerializer<T> extends StdSerializer<T> {

    /**
     * Constructs a serializer for the specified class.
     *
     * @param clazz the class to serialize
     */
    protected AbstractTypeSerializer(Class<?> clazz) {
        super(clazz, false);
    }

    /**
     * Extracts the value to be serialized from the given object.
     *
     * @param value the object to extract the value from
     * @return the extracted value
     */
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
