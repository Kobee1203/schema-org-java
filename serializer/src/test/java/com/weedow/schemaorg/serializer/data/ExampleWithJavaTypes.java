package com.weedow.schemaorg.serializer.data;

import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import lombok.Data;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.ZonedDateTime;

@Data
@JsonLdTypeName("ExampleWithJavaTypes")
public class ExampleWithJavaTypes extends JsonLdNodeImpl {

    private java.lang.Boolean bool;
    private java.lang.String text;
    private java.time.LocalDate date;
    private java.time.LocalDateTime dateTime;
    private java.lang.Float aFloat;
    private java.lang.Integer integer;
    private java.lang.Number number;
    private java.time.LocalTime time;
    private java.net.URL url;
    private BigDecimal bigDecimal;
    private ZonedDateTime zonedDateTime;
    private Path path;
}
