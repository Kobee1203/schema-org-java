package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.utils.SerializerUtils;

import java.io.IOException;
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
        final Type dataType = SerializerUtils.getJavaType(jsonLdDataType);
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

    @Override
    public void serializeWithType(JsonLdDataType<?> value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        serialize(value, gen, provider);
    }

}
