package com.weedow.schemaorg.generator.model;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.weedow.schemaorg.generator.model.field.Accessor;
import com.weedow.schemaorg.generator.model.field.Field;
import com.weedow.schemaorg.generator.model.field.Mutator;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.ScanOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

class ModelVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .forPackage(
                        getClass().getPackageName(),
                        ScanOption.except(Property.class, Type.class),
                        // Skip '*Builder' classes
                        ScanOption.except(clazz -> clazz.getSimpleName().endsWith("Builder")))
                .verify();
    }

    @Test
    void equalsContractProperty() {
        EqualsVerifier
                .forClass(Property.class)
                .withPrefabValues(List.class, List.of(mock(Type.class)), List.of(mock(Type.class)))
                .withPrefabValues(Type.class, mock(Type.class), mock(Type.class))
                .withIgnoredFields("types")
                .verify();
    }

    @Test
    void equalsContractType() {
        EqualsVerifier
                .simple()
                .forClass(Type.class)
                .withPrefabValues(List.class, List.of(mock(Type.class)), List.of(mock(Type.class)))
                .withPrefabValues(Type.class, mock(Type.class), mock(Type.class))
                .withIgnoredFields("allProperties", "subTypes")
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier
                .forPackage(getClass().getPackageName(), false)
                // Ignore fields that are not present in toString() method, or their toString representation is modified before being added (eg.Supplier fields)
                .withIgnoredFields(
                        "parents",
                        "subTypes",
                        "types"
                )
                .verify();
    }

    @Test
    void toStringContractProperty() {
        ToStringVerifier
                .forClass(Property.class)
                // Ignore 'types' field that is not present in toString() method, but just type names to prevent recursivity error
                .withIgnoredFields("types")
                .verify();
        Property property = newProperty("id", "name", "fieldName", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", List.of(new Type("id1").setName("type name 1"), new Type("id2").setName("type name 2")), List.of("partOf1", "partOf2"), List.of("source1", "source2"));
        Assertions.assertThat(property).hasToString(
                // @formatter:off
                "Property(" +
                        "id=id, " +
                        "field=Field(name=name, fieldName=name, fieldType=Object, fieldTypeAsList=List<Object>), " +
                        "accessor=Accessor(" +
                            "name=name, " +
                            "description=Lorem Ipsum is simply dummy text of the printing and typesetting industry., " +
                            "partOf=[partOf1, partOf2], " +
                            "source=[source1, source2], " +
                            "fieldTypeLinks={@link type name 1} or {@link type name 2}, " +
                            "returnFieldType=<T> T, " +
                            "returnFieldTypeAsList=<T> List<T>, " +
                            "cast=(T), " +
                            "castAsList=(List<T>), " +
                            "fieldName=name, " +
                            "getterMethod=getNameList, " +
                            "firstGetterMethod=getName, " +
                            "splitDescription=[Lorem Ipsum is simply dummy text of the printing and typesetting industry.]" +
                        "), " +
                        "mutators=[Mutator(" +
                            "name=name, " +
                            "description=Lorem Ipsum is simply dummy text of the printing and typesetting industry., " +
                            "partOf=[partOf1, partOf2], " +
                            "source=[source1, source2], " +
                            "paramType=Object, " +
                            "paramValue=fieldName, " +
                            "fieldName=name, " +
                            "setterMethod=setName, " +
                            "addMethod=addName, " +
                            "splitDescription=[Lorem Ipsum is simply dummy text of the printing and typesetting industry.])" +
                        "], " +
                        "types=[type name 1, type name 2])"
                // @formatter:on
        );

        Property nullProperty = newProperty(null, null, null, null, null, null, null);
        Assertions.assertThat(nullProperty).hasToString(
                // @formatter:off
                "Property(" +
                        "id=null, " +
                        "field=Field(name=null, fieldName=null, fieldType=null, fieldTypeAsList=null), " +
                        "accessor=Accessor(" +
                                "name=null, " +
                                "description=null, " +
                                "partOf=null, " +
                                "source=null, " +
                                "fieldTypeLinks=null, " +
                                "returnFieldType=null, " +
                                "returnFieldTypeAsList=null, " +
                                "cast=null, " +
                                "castAsList=null, " +
                                "fieldName=null, " +
                                "getterMethod=getnullList, " +
                                "firstGetterMethod=getnull, " +
                                "splitDescription=null" +
                        "), " +
                        "mutators=[Mutator(" +
                            "name=null, " +
                            "description=null, " +
                            "partOf=null, " +
                            "source=null, " +
                            "paramType=Object, " +
                            "paramValue=null, " +
                            "fieldName=null, " +
                            "setterMethod=setnull, " +
                            "addMethod=addnull, " +
                            "splitDescription=null)" +
                        "], " +
                "types=null)"
                // @formatter:on
        );
    }

    @Test
    void toFormattedStringContractType() {
        Type parent1 = new Type("parent1");
        Type type = new Type("id")
                .setName("name")
                .setJavaType("javaType")
                .setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .setPartOf(List.of("partOf1", "partOf2"))
                .setSource(List.of("source1", "source2"))
                .addEnumerationMember("enumMember1").addEnumerationMember("enumMember2")
                .addParent(parent1).addParent(new Type("parent2"))
                .addProperty(newProperty("id1",
                        "name1",
                        "fieldName1",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        List.of(new Type("id1").setName("type name 1"), new Type("id2").setName("type name 2")),
                        List.of("partOf1", "partOf2"),
                        List.of("source1", "source2")
                ))
                .addProperty(newProperty("id2",
                        "name2",
                        "fieldName2",
                        null,
                        List.of(new Type("id1").setName("type name 1")),
                        null,
                        null
                ));

        Assertions.assertThat(type.toFormattedString()).isEqualTo("""
                ---------- id ----------
                name          = name
                usedJavaType  = false
                description   = Lorem Ipsum is simply dummy text of the printing a
                parents       = [parent1, parent2]
                subTypes      = []
                partOf        = partOf1, partOf2
                source        = source1, source2
                properties    = [
                    Property(id='id1', name='name1', description='Lorem Ipsum is simply dummy text of the printing a', types=[type name 1, type name 2], partOf=[partOf1, partOf2], source=[source1, source2])
                    Property(id='id2', name='name2', description='null', types=[type name 1], partOf=null, source=null)
                ]
                enum members  = enumMember1, enumMember2
                """);

        Assertions.assertThat(parent1.toFormattedString()).isEqualTo("""
                ---------- parent1 ----------
                name          = null
                usedJavaType  = false
                description   = null
                parents       = []
                subTypes      = [id]
                partOf        =\s
                source        =\s
                properties    = []
                enum members  =\s
                """);

        Type nullType = new Type(null);

        Assertions.assertThat(nullType.toFormattedString()).isEqualTo("""
                ---------- null ----------
                name          = null
                usedJavaType  = false
                description   = null
                parents       = []
                subTypes      = []
                partOf        =\s
                source        =\s
                properties    = []
                enum members  =\s
                """);
    }

    private static Property newProperty(String id, String name, String fieldName, String description, List<Type> types, List<String> partOf, List<String> source) {
        return new Property(
                id,
                new Field(name, types),
                new Accessor(name, description, partOf, source, types),
                List.of(new Mutator(name, description, partOf, source, () -> "Object", () -> fieldName)),
                types
        );
    }

}