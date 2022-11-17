package com.weedow.schemaorg.serializer;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.data.Example;
import com.weedow.schemaorg.serializer.data.InvalidData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.schema.model.*;
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
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

@TestWithResources
class JsonLdSerializerImplTest {

    @Test
    void serialize(@GivenTextResource("/data/JsonLdNode.json") String expected) throws JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        JsonLdNode jsonLdNode = new JsonLdNodeImpl();
        String result = jsonLdSerializer.serialize(jsonLdNode);
        assertThatJson(result).isEqualTo(expected);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void serialize_with_pretty_print(@GivenTextResource("/data/JsonLdNode_pretty_print.json") String expected) throws JsonLdException {
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
    void serialize_thing(@GivenTextResource("/data/Thing.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Thing thing = new ThingImpl();
        thing.setId("my_id");
        thing.setName(Text.of("My Thing"));
        thing.setDescription(Text.of("This is my thing."));
        thing.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));

        String result = jsonLdSerializer.serialize(thing);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void serialize_complex_object(@GivenTextResource("/data/Hotel.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Hotel hotel = new HotelImpl();
        hotel.setName(Text.of("Sunny Hotel"));
        hotel.setDescription(Text.of("This is a hotel where every day is sunny"));

        Person employee = new PersonImpl();
        employee.setGivenName(Text.of("John"));
        employee.setFamilyName(Text.of("Doe"));
        employee.setBirthDate(Date.of(LocalDate.of(1981, Month.MARCH, 12)));
        employee.setEmail(Text.of("john.doe@sunnyhotel.com"));
        hotel.setEmployee(employee);

        PostalAddress postalAddress = new PostalAddressImpl();
        postalAddress.setStreetAddress(Text.of("21 jump street"));
        postalAddress.setAddressLocality(Text.of("Lost City"));
        Country country = new CountryImpl();
        country.setName(Text.of("United States Of America"));
        country.setMap(URL.of(new java.net.URL("https://goo.gl/maps/tvsDMhfk3yah43Mm6")));
        postalAddress.setAddressCountry(country);
        hotel.setAddress(postalAddress);

        Rating rating = new RatingImpl();
        rating.setRatingValue(Number.of(4.5));
        rating.setBestRating(Number.of(5));
        rating.setWorstRating(Number.of(3.5));
        hotel.setStarRating(rating);

        OpeningHoursSpecification openingHoursSpecification = new OpeningHoursSpecificationImpl();
        openingHoursSpecification.setOpens(Time.of(LocalTime.of(9, 0)));
        openingHoursSpecification.setCloses(Time.of(LocalTime.of(23, 0)));
        openingHoursSpecification.setDayOfWeek(DayOfWeekEnum.SATURDAY);
        openingHoursSpecification.setValidFrom(DateTime.of(LocalDateTime.of(2022, Month.DECEMBER, 1, 9, 0)));
        openingHoursSpecification.setValidThrough(Date.of(LocalDate.of(2024, Month.DECEMBER, 31)));
        hotel.setOpeningHoursSpecification(openingHoursSpecification);

        Event event = new EventImpl();
        event.setName(Text.of("Funny Party"));
        event.setEventAttendanceMode(EventAttendanceModeEnumerationEnum.MIXED_EVENT_ATTENDANCE_MODE);
        event.setIsAccessibleForFree(Boolean.of(true));
        event.setMaximumAttendeeCapacity(Integer.of(654));
        hotel.setEvent(event);

        String result = jsonLdSerializer.serialize(hotel);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void throws_exception_when_serialize_invalid_data() {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        InvalidData invalidData = new InvalidData();
        Assertions.assertThatThrownBy(() -> jsonLdSerializer.serialize(invalidData))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD serialize internal error for type schema:InvalidData.")
                .hasCauseInstanceOf(JsonMappingException.class)
                //.hasMessage("Unexpected error (through reference chain: com.weedow.schemaorg.serializer.data.InvalidData[\"text\"])")
                .hasRootCauseInstanceOf(RuntimeException.class)
                .hasRootCauseMessage("Unexpected error");
    }

    @Test
    void serialize_list(@GivenTextResource("/data/List.json") String expected) throws MalformedURLException, JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Thing thing = new ThingImpl();
        thing.setId("my_id");
        thing.setName(Text.of("My Thing"));
        thing.setDescription(Text.of("This is my thing."));
        thing.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));

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

        List<? extends JsonLdNode> objects = Arrays.asList(thing, example);
        String result = jsonLdSerializer.serialize(objects);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void throws_exception_when_serialize_invalid_data_list() {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        InvalidData invalidData = new InvalidData();
        Assertions.assertThatThrownBy(() -> jsonLdSerializer.serialize(Arrays.asList(invalidData, invalidData)))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD serialize internal error for type schema:InvalidData.")
                .hasCauseInstanceOf(JsonMappingException.class)
                //.hasMessage("Unexpected error (through reference chain: com.weedow.schemaorg.serializer.data.InvalidData[\"text\"])")
                .hasRootCauseInstanceOf(RuntimeException.class)
                .hasRootCauseMessage("Unexpected error");
    }
}