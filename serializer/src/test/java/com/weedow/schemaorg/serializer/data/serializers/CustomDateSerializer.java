package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.DateSerializer;
import org.schema.model.datatype.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonLdDataTypeSerializer
public class CustomDateSerializer extends DateSerializer {

    public CustomDateSerializer() {
        super(Date.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<LocalDate> jsonLdDataType) {
        return jsonLdDataType.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}