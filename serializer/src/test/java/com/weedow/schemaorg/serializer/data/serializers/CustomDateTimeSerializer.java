package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.DateTimeSerializer;
import org.schema.model.datatype.DateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonLdDataTypeSerializer
public class CustomDateTimeSerializer extends DateTimeSerializer {

    public CustomDateTimeSerializer() {
        super(DateTime.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<LocalDateTime> jsonLdDataType) {
        return jsonLdDataType.getValue()
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}