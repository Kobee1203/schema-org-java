package com.weedow.schemaorg.serializer.serialization;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.JsonLdSerializerOptions;
import com.weedow.schemaorg.serializer.data.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
    void serialize_all_data_types(@GivenTextResource("/data/Example.json") String expected) throws JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Example example = ExampleUtils.createExample();

        String result = jsonLdSerializer.serialize(example);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void serialize_thing(@GivenTextResource("/data/Thing.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Thing thing = new ThingImpl();
        thing.setId("my_id");
        thing.addName(Text.of("My Thing"));
        thing.addDescription(Text.of("This is my thing."));
        thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));

        String result = jsonLdSerializer.serialize(thing);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void serialize_thing_with_multiple_values_by_field(@GivenTextResource("/data/Thing_mutliple_values_by_field.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Thing thing = new ThingImpl();
        thing.setId("my_id");
        thing.addName(Text.of("My Thing"));
        thing.addDescription(Text.of("This is my thing."));
        thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
        thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java/2")));

        PropertyValue propertyValue1 = new PropertyValueImpl();
        propertyValue1.addValue(Number.of(123456));
        propertyValue1.addValue(Number.of(789012));
        thing.addIdentifier(propertyValue1);

        PropertyValue propertyValue2 = new PropertyValueImpl();
        propertyValue2.addValue(Number.of(10203040));
        propertyValue2.addValue(Text.of("ABC-50607080"));
        StructuredValue structuredValue = new StructuredValueImpl();
        structuredValue.addName(Text.of("Special Identifier"));
        structuredValue.addIdentifier(URL.of("https://github.com/Kobee1203/schema-org-java"));
        propertyValue2.addValue(structuredValue);
        thing.addIdentifier(propertyValue2);

        CreativeWork subjectOf = new CreativeWorkImpl();
        subjectOf.addIsAccessibleForFree(Boolean.of(true));
        subjectOf.addIsFamilyFriendly(Boolean.of(false));
        thing.addSubjectOf(subjectOf);

        String result = jsonLdSerializer.serialize(thing);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void serialize_complex_object(@GivenTextResource("/data/Hotel.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Hotel hotel = new HotelImpl();
        hotel.addName(Text.of("Sunny Hotel"));
        hotel.addDescription(Text.of("This is a hotel where every day is sunny"));

        Person employee = new PersonImpl();
        employee.addGivenName(Text.of("John"));
        employee.addFamilyName(Text.of("Doe"));
        employee.addBirthDate(Date.of(LocalDate.of(1981, Month.MARCH, 12)));
        employee.addEmail(Text.of("john.doe@sunnyhotel.com"));
        hotel.addEmployee(employee);

        PostalAddress postalAddress = new PostalAddressImpl();
        postalAddress.addStreetAddress(Text.of("21 jump street"));
        postalAddress.addAddressLocality(Text.of("Lost City"));
        Country country = new CountryImpl();
        country.addName(Text.of("United States Of America"));
        country.addMap(URL.of(new java.net.URL("https://goo.gl/maps/tvsDMhfk3yah43Mm6")));
        postalAddress.addAddressCountry(country);
        hotel.addAddress(postalAddress);

        Rating rating = new RatingImpl();
        rating.addRatingValue(Number.of(4.5));
        rating.addBestRating(Number.of(5));
        rating.addWorstRating(Number.of(3.5));
        hotel.addStarRating(rating);

        OpeningHoursSpecification openingHoursSpecification = new OpeningHoursSpecificationImpl();
        openingHoursSpecification.addOpens(Time.of(LocalTime.of(9, 0)));
        openingHoursSpecification.addCloses(Time.of(LocalTime.of(23, 0)));
        openingHoursSpecification.addDayOfWeek(DayOfWeekEnum.SATURDAY);
        openingHoursSpecification.addValidFrom(DateTime.of(LocalDateTime.of(2022, Month.DECEMBER, 1, 9, 0)));
        openingHoursSpecification.addValidThrough(Date.of(LocalDate.of(2024, Month.DECEMBER, 31)));
        hotel.addOpeningHoursSpecification(openingHoursSpecification);

        Event event = new EventImpl();
        event.addName(Text.of("Funny Party"));
        event.addEventAttendanceMode(EventAttendanceModeEnumerationEnum.MIXED_EVENT_ATTENDANCE_MODE);
        event.addIsAccessibleForFree(Boolean.of(true));
        event.addMaximumAttendeeCapacity(Integer.of(654));
        hotel.addEvent(event);

        hotel.addNonprofitStatus(NLNonprofitTypeEnum.NONPROFIT_ANBI);

        String result = jsonLdSerializer.serialize(hotel);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    void serialize_complex_datatype_object(@GivenTextResource("/data/ObjectDataTypeExample.json") String expected) throws JsonLdException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        ObjectDataTypeExample example = new ObjectDataTypeExample();
        example.setObj(ExampleUtils.createExample());
        example.setBool(true);
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

        String result = jsonLdSerializer.serialize(example);

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    @Disabled("The Serializer calls diredctly the field to get the value, not the getter that throws an exception")
    void throws_exception_when_serialize_invalid_data() {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        InvalidData invalidData = new InvalidData();
        Assertions.assertThatThrownBy(() -> jsonLdSerializer.serialize(invalidData))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD serialization internal error for type InvalidData.")
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
        thing.addName(Text.of("My Thing"));
        thing.addDescription(Text.of("This is my thing."));
        thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
        PropertyValue propertyValue1 = new PropertyValueImpl();
        propertyValue1.addValue(Number.of(123456));
        propertyValue1.addValue(Number.of(789012));
        thing.addIdentifier(propertyValue1);

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
    void serialize_list_with_one_object(@GivenTextResource("/data/Thing.json") String expected) throws JsonLdException, MalformedURLException {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(JsonLdSerializerOptions.builder().prettyPrint(true).build());

        Thing thing = new ThingImpl();
        thing.setId("my_id");
        thing.addName(Text.of("My Thing"));
        thing.addDescription(Text.of("This is my thing."));
        thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));

        String result = jsonLdSerializer.serialize(List.of(thing));

        assertThatJson(result).isEqualTo(expected);
    }

    @Test
    @Disabled("The Serializer calls directly the field to get the value, not the getter that throws an exception")
    void throws_exception_when_serialize_invalid_data_list() {
        final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

        InvalidData invalidData = new InvalidData();
        Assertions.assertThatThrownBy(() -> jsonLdSerializer.serialize(Arrays.asList(invalidData, invalidData)))
                .isInstanceOf(JsonLdException.class)
                .hasMessage("JSON-LD serialization internal error for type InvalidData.")
                .hasCauseInstanceOf(JsonMappingException.class)
                //.hasMessage("Unexpected error (through reference chain: com.weedow.schemaorg.serializer.data.InvalidData[\"text\"])")
                .hasRootCauseInstanceOf(RuntimeException.class)
                .hasRootCauseMessage("Unexpected error");
    }
}