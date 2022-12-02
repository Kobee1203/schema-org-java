package com.weedow.schemaorg.serializer.deserialization;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.data.Example;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.schema.model.impl.ThingImpl;

import java.net.MalformedURLException;

@TestWithResources
class JsonLdDeserializerImplTest {

    @Test
    void deserialize(@GivenTextResource("/data/JsonLdNode.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        JsonLdNode jsonLdNode = new JsonLdNodeImpl();
        jsonLdNode.setContext("https://schema.org");

        Assertions.assertThat(result).isEqualTo(jsonLdNode);
    }

    @Test
    void deserialize_with_pretty_print(@GivenTextResource("/data/JsonLdNode_pretty_print.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        JsonLdNode jsonLdNode = new JsonLdNodeImpl();
        jsonLdNode.setContext("https://schema.org");

        Assertions.assertThat(result).isEqualTo(jsonLdNode);
    }

    @Test
    void deserialize_all_data_types(@GivenTextResource("/data/Example.json") String json) throws JsonLdException, MalformedURLException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        //Example result = jsonLdDeserializer.deserialize(json);
    }

    @Test
    void deserialize_thing(@GivenTextResource("/data/Thing.json") String json) throws JsonLdException, MalformedURLException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        /*JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result)
                .isInstanceOf(ThingImpl.class);*/
    }

    @Test
    void deserialize_complex_object(@GivenTextResource("/data/Hotel.json") String json) throws JsonLdException, MalformedURLException {
    }

    @Test
    void throws_exception_when_deserialize_invalid_data() {
    }

    @Test
    void deserialize_list(@GivenTextResource("/data/List.json") String json) throws MalformedURLException, JsonLdException {
    }

    @Test
    void deserialize_list_with_one_object(@GivenTextResource("/data/Thing.json") String json) throws JsonLdException, MalformedURLException {
    }

    @Test
    void throws_exception_when_deserialize_invalid_data_list() {
    }
}