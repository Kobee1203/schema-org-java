package com.weedow.schemaorg.serializer.deserialization;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.data.Example;
import com.weedow.schemaorg.serializer.data.ObjectDataTypeExample;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.*;
import org.schema.model.impl.*;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
    void deserialize_all_data_types(@GivenTextResource("/data/Example.json") String json) throws JsonLdException, MalformedURLException {
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
    void deserialize_thing(@GivenTextResource("/data/Thing.json") String json) throws JsonLdException, MalformedURLException {
        JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
        JsonLdNode result = jsonLdDeserializer.deserialize(json);

        Assertions.assertThat(result)
                .isInstanceOf(ThingImpl.class)
                .extracting("context", "id", "name.value", "description.value", "url.value", "image")
                .containsExactly("https://schema.org", "my_id", "My Thing", "This is my thing.", "https://github.com/Kobee1203/schema-org-java", null);
    }

    @Test
    void deserialize_complex_object(@GivenTextResource("/data/Hotel.json") String json) throws JsonLdException, MalformedURLException {
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
    void deserialize_complex_datatype_object(@GivenTextResource("/data/ObjectTypeExample.json") String json) throws JsonLdException {
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