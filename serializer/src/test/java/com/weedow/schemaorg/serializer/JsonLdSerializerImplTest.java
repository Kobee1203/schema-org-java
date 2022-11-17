package com.weedow.schemaorg.serializer;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.data.Example;
import org.junit.jupiter.api.Test;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.*;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;
import org.schema.model.impl.ThingImpl;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

@TestWithResources
class JsonLdSerializerImplTest {

    @Test
    void serialize(@GivenTextResource("/data/JsonLdNodeImpl.json") String expected) throws JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        JsonLdNode jsonLdNode = new JsonLdNodeImpl();
        String result = jsonLdSerializer.serialize(jsonLdNode);
        assertThatJson(result).isEqualTo(expected);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void serialize_with_pretty_print(@GivenTextResource("/data/JsonLdNodeImpl_pretty_print.json") String expected) throws JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        JsonLdNode jsonLdNode = new JsonLdNodeImpl();
        String result = jsonLdSerializer.serialize(jsonLdNode);
        assertThatJson(result).isEqualTo(expected);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void serialize_all_data_types(@GivenTextResource("/data/Example.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

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
        example.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
        example.setXPathType(XPathType.of("/xpath/example/title"));

        String result = jsonLdSerializer.serialize(example);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void testSerialize(@GivenTextResource("/data/ThingImpl.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        ThingImpl thing = new ThingImpl();
        thing.setId("my_id");
        thing.setName(Text.of("My Thing"));
        thing.setDescription(Text.of("This is my thing."));
        thing.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));

        String result = jsonLdSerializer.serialize(thing);

        assertThatJson(result).isEqualTo(expected);
    }
}