package com.weedow.schemaorg.serializer.deserialization;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.data.Example;
import com.weedow.schemaorg.serializer.data.MyDataset;
import com.weedow.schemaorg.serializer.data.ObjectDataTypeExample;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.schema.model.Thing;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.*;
import org.schema.model.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

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
    void deserialize_all_data_types(@GivenTextResource("/data/Example.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl(Map.of("Example", Example.class));
        Example result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result)
                .extracting(
                        "context", "id", "bool.value",
                        "date.value", "dateTime.value", "time.value",
                        "number.value", "integer.value", "aFloat.value",
                        "text.value", "pronounceableText.value", "url.value", "xPathType.value", "cssSelectorType.value"
                )
                .containsExactly(
                        "https://schema.org", null, true,
                        LocalDate.of(2022, Month.MARCH, 12), LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30), LocalTime.of(10, 36, 30),
                        12345.67d, 12345, 12345.67f,
                        "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", "/xpath/example/title", ".css-selector-type"
                );
    }

    @Test
    void deserialize_thing(@GivenTextResource("/data/Thing.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result)
                .isInstanceOf(ThingImpl.class)
                .extracting("context", "id", "name.value", "description.value", "url.value", "image")
                .containsExactly("https://schema.org", "my_id", "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", null);
    }

    @Test
    void deserialize_thing_with_multiple_values_by_field(@GivenTextResource("/data/Thing_mutliple_values_by_field.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result).isInstanceOf(ThingImpl.class);

        ThingImpl thing = (ThingImpl) result;

        Assertions.assertThat(thing.getContext()).isEqualTo("https://schema.org");
        Assertions.assertThat(thing.getId()).isEqualTo("my_id");
        Assertions.assertThat(thing.getName()).extracting("value").isEqualTo("My Thing");
        Assertions.assertThat(thing.getNameList()).extracting("value").containsExactly("My Thing");
        Assertions.assertThat(thing.getDescription()).extracting("value").isEqualTo("This is my thing.");
        Assertions.assertThat(thing.getDescriptionList()).extracting("value").containsExactly("This is my thing.");
        Assertions.assertThat(thing.getUrlList()).extracting("value").containsExactly(
                "https://github.com/Kobee1203/schema-org-java",
                "https://github.com/Kobee1203/schema-org-java/2"
        );
        List<Object> identifiers = thing.getIdentifierList();
        Assertions.assertThat(identifiers).hasSize(2);
        Assertions.assertThat(identifiers.get(0))
                .isInstanceOf(PropertyValueImpl.class)
                .extracting("valueList", InstanceOfAssertFactories.list(List.class))
                .hasOnlyElementsOfType(Number.class)
                .extracting("value")
                .containsExactly(123456, 789012);

        Object identifier2 = identifiers.get(1);
        Assertions.assertThat(identifier2).isInstanceOf(PropertyValueImpl.class);
        PropertyValueImpl pv2 = (PropertyValueImpl) identifier2;

        List<Object> valueList = pv2.getValueList();
        Assertions.assertThat(valueList).hasSize(3);
        Assertions.assertThat(valueList.get(0)).isInstanceOf(Number.class).extracting("value").isEqualTo(10203040);
        Assertions.assertThat(valueList.get(1)).isInstanceOf(Text.class).extracting("value").isEqualTo("ABC-50607080");
        Object value3 = valueList.get(2);
        Assertions.assertThat(value3).isInstanceOf(StructuredValueImpl.class);

        StructuredValueImpl sv = (StructuredValueImpl) value3;
        Assertions.assertThat(sv.getName())
                .extracting("value")
                .isEqualTo("Special Identifier");
        Assertions.assertThat(sv.getIdentifierList()).hasSize(1);
        Assertions.assertThat(sv.getIdentifierList().get(0))
                .isInstanceOf(URL.class)
                .extracting("value")
                .isEqualTo("https://github.com/Kobee1203/schema-org-java");

        Object subjectOf = thing.getSubjectOf();
        Assertions.assertThat(subjectOf).isInstanceOf(CreativeWorkImpl.class)
                .extracting("isAccessibleForFree.value", "isFamilyFriendly.value")
                .containsExactly(true, false);
    }

    @Test
    void deserialize_complex_object(@GivenTextResource("/data/Hotel.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result)
                .isInstanceOf(HotelImpl.class)
                .extracting(
                        "name.value", "description.value",
                        "employee.givenName.value", "employee.familyName.value", "employee.birthDate.value", "employee.email.value",
                        "address.streetAddress.value", "address.addressLocality.value", "address.addressCountry.name.value", "address.addressCountry.map.value",
                        "starRating.ratingValue.value", "starRating.bestRating.value", "starRating.worstRating.value",
                        "openingHoursSpecification.opens.value", "openingHoursSpecification.closes.value", "openingHoursSpecification.dayOfWeek",
                        "openingHoursSpecification.validFrom.value", "openingHoursSpecification.validThrough.value",
                        "event.name.value", "event.eventAttendanceMode", "event.isAccessibleForFree.value", "event.maximumAttendeeCapacity.value",
                        "nonprofitStatus"
                )
                .containsExactly(
                        "Sunny Hotel", "This is a hotel where every day is sunny",
                        "John", "Doe", LocalDate.of(1981, Month.MARCH, 12), "john.doe@sunnyhotel.com",
                        "21 jump street", "Lost City", "United States Of America", "https://goo.gl/maps/tvsDMhfk3yah43Mm6",
                        4.5, 5, 3.5,
                        LocalTime.of(9, 0), LocalTime.of(23, 0), DayOfWeekEnum.SATURDAY,
                        LocalDateTime.of(2022, Month.DECEMBER, 1, 9, 0), LocalDate.of(2024, Month.DECEMBER, 31),
                        "Funny Party", EventAttendanceModeEnumerationEnum.MIXED_EVENT_ATTENDANCE_MODE, true, 654,
                        NLNonprofitTypeEnum.NONPROFIT_ANBI
                );
    }

    @Test
    void deserialize_complex_datatype_object(@GivenTextResource("/data/ObjectDataTypeExample.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl("com.weedow.schemaorg.serializer.data");
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result).isInstanceOf(ObjectDataTypeExample.class);

        ObjectDataTypeExample example = (ObjectDataTypeExample) result;

        Assertions.assertThat(example.getContext()).isEqualTo("https://schema.org");
        Assertions.assertThat(example.getId()).isNull();
        Assertions.assertThat(example.getBool()).isInstanceOf(Boolean.class).extracting("value").isEqualTo(true);
        Assertions.assertThat(example.getDate()).isInstanceOf(Date.class).extracting("value").isEqualTo(LocalDate.of(2022, Month.MARCH, 12));
        Assertions.assertThat(example.getDateTime()).isInstanceOf(DateTime.class).extracting("value").isEqualTo(LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30));
        Assertions.assertThat(example.getTime()).isInstanceOf(Time.class).extracting("value").isEqualTo(LocalTime.of(10, 36, 30));
        Assertions.assertThat(example.getNumber()).isInstanceOf(Number.class).extracting("value").isEqualTo(12345.67d);
        Assertions.assertThat(example.getInteger()).isInstanceOf(Integer.class).extracting("value").isEqualTo(12345);
        Assertions.assertThat(example.getAFloat()).isInstanceOf(Float.class).extracting("value").isEqualTo(12345.67f);
        Assertions.assertThat(example.getText()).isInstanceOf(Text.class).extracting("value").isEqualTo("My Thing");
        Assertions.assertThat(example.getPronounceableText()).isInstanceOf(PronounceableText.class).extracting("value").isEqualTo("This is my thing.");
        Assertions.assertThat(example.getUrl()).isInstanceOf(URL.class).extracting("value").isEqualTo("https://github.com/Kobee1203/schema-org-java");
        Assertions.assertThat(example.getXPathType()).isInstanceOf(XPathType.class).extracting("value").isEqualTo("/xpath/example/title");
        Assertions.assertThat(example.getCssSelectorType()).isInstanceOf(CssSelectorType.class).extracting("value").isEqualTo(".css-selector-type");
    }

    @Test
    void throws_exception_when_deserialize_invalid_data() {
        final JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();

        Assertions.assertThatThrownBy(() -> jsonLdDeserializer.deserialize("{}"))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD deserialization internal error: Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 2].")
                .hasCauseInstanceOf(JsonMappingException.class)
                .hasMessage("JSON-LD deserialization internal error: Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 2].")
                .hasRootCauseInstanceOf(InvalidTypeIdException.class)
                .hasRootCauseMessage("Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 2]");
    }

    @Test
    void deserialize_list(@GivenTextResource("/data/List.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl("com.weedow.schemaorg.serializer.data");
        List<JsonLdNode> result = jsonLdDeserializer.deserializeList(json);

        Assertions.assertThat(result).isNotNull().hasSize(2);

        Assertions.assertThat(result.get(0)).isInstanceOf(ThingImpl.class);

        Thing thing = (Thing) result.get(0);

        Assertions.assertThat(thing).isInstanceOf(ThingImpl.class)
                .extracting("context", "id", "name.value", "description.value", "url.value", "image")
                .containsExactly("https://schema.org", "my_id", "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", null);

        List<Object> identifiers = thing.getIdentifierList();
        Assertions.assertThat(identifiers).hasSize(1);
        Assertions.assertThat(identifiers.get(0))
                .isInstanceOf(PropertyValueImpl.class)
                .extracting("valueList", InstanceOfAssertFactories.list(List.class))
                .hasOnlyElementsOfType(Number.class)
                .extracting("value")
                .containsExactly(123456, 789012);

        Assertions.assertThat(result.get(1)).isInstanceOf(Example.class)
                .extracting(
                        "context", "id", "bool.value",
                        "date.value", "dateTime.value", "time.value",
                        "number.value", "integer.value", "aFloat.value",
                        "text.value", "pronounceableText.value", "url.value", "xPathType.value", "cssSelectorType.value"
                )
                .containsExactly(
                        "https://schema.org", null, true,
                        LocalDate.of(2022, Month.MARCH, 12), LocalDateTime.of(2022, Month.MARCH, 12, 10, 36, 30), LocalTime.of(10, 36, 30),
                        12345.67d, 12345, 12345.67f,
                        "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", "/xpath/example/title", ".css-selector-type"
                );
    }

    @Test
    void deserialize_list_with_one_object(@GivenTextResource("/data/Thing.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        List<JsonLdNode> result = jsonLdDeserializer.deserializeList(json);

        Assertions.assertThat(result).isNotNull().hasSize(1);

        Assertions.assertThat(result.get(0))
                .isInstanceOf(ThingImpl.class)
                .extracting("context", "id", "name.value", "description.value", "url.value", "image")
                .containsExactly("https://schema.org", "my_id", "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", null);
    }

    @Test
    void throws_exception_when_deserialize_invalid_data_list() {
        final JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();

        Assertions.assertThatThrownBy(() -> jsonLdDeserializer.deserializeList("[{}]"))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD deserialization internal error: Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: UNKNOWN; byte offset: #UNKNOWN].")
                .hasCauseInstanceOf(JsonMappingException.class)
                .hasMessage("JSON-LD deserialization internal error: Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: UNKNOWN; byte offset: #UNKNOWN].")
                .hasRootCauseInstanceOf(InvalidTypeIdException.class)
                .hasRootCauseMessage("Could not resolve subtype of [simple type, class com.weedow.schemaorg.commons.model.JsonLdNode]: missing type id property '@type'\n" +
                        " at [Source: UNKNOWN; byte offset: #UNKNOWN]");
    }

    @Test
    void deserialize_field_with_special_character_using_JsonProperty_annotation(@GivenTextResource("/data/MyDataset.json") String json) throws JsonLdException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl("com.weedow.schemaorg.serializer.data");
        MyDataset myDataset = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(myDataset.getContext()).isEqualTo("https://schema.org");
        Assertions.assertThat(myDataset.getType()).isEqualTo("MyDataset");
        Assertions.assertThat(myDataset.getProvWasDerivedFrom()).isNotNull()
                .extracting("type", "name.value")
                .containsExactly("MyDataset", "MyName");
    }
}