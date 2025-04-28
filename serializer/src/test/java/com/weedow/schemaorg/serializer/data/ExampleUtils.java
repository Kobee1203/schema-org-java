package com.weedow.schemaorg.serializer.data;

import org.schema.model.datatype.*;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.*;

public final class ExampleUtils {

    private ExampleUtils() {
    }

    public static Example createExample() {
        Example example = new Example();
        example.setBool(Boolean.of(true));
        example.setCssSelectorType(CssSelectorType.of(".css-selector-type"));
        example.setDate(Date.of(LocalDate.of(2022, Month.MARCH, 12)));
        example.setDateTime(DateTime.of(LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30)));
        example.setAFloat(Float.of(12345.67f));
        example.setInteger(Integer.of(12345));
        example.setNumber(Number.of(12345.67d));
        example.setPronounceableText(PronounceableText.of("This is my thing."));
        example.setText(Text.of("My Thing"));
        example.setTime(Time.of(LocalTime.of(10, 36, 30)));
        try {
            example.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        example.setXPathType(XPathType.of("/xpath/example/title"));
        return example;
    }

    public static ExampleWithJavaTypes createExampleWithJavaTypes() {
        ExampleWithJavaTypes example = new ExampleWithJavaTypes();
        example.setBool(true);
        example.setText("My Thing");
        example.setDate(LocalDate.of(2022, Month.MARCH, 12));
        example.setDateTime(LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30));
        example.setAFloat(12345.67f);
        example.setInteger(12345);
        example.setNumber(12345.67d);
        example.setTime(LocalTime.of(10, 36, 30));
        try {
            example.setUrl(new java.net.URL("https://github.com/Kobee1203/schema-org-java"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        example.setBigDecimal(new BigDecimal("10.17"));
        example.setZonedDateTime(ZonedDateTime.of(2025, Month.MARCH.getValue(), 12, 10, 30, 0, 0, ZoneId.of("Z")));
        example.setPath(Paths.get("/my/path"));
        return example;
    }
}
