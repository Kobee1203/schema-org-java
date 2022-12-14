package com.weedow.schemaorg.serializer.data;

import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.*;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public final class ExampleUtils {

    private ExampleUtils() {
    }

    public static Example createExample() {
        Example example = new Example();
        example.setBool(Boolean.of(true));
        example.setCssSelectorType(CssSelectorType.of(".css-selector-type"));
        example.setDate(Date.of(LocalDate.of(2022, Month.MARCH, 12)));
        example.setDateTime(DateTime.of(LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30)));
        example.setAFloat(org.schema.model.datatype.Float.of(12345.67f));
        example.setInteger(org.schema.model.datatype.Integer.of(12345));
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
}
