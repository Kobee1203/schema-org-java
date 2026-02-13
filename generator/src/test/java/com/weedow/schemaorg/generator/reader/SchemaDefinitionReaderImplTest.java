package com.weedow.schemaorg.generator.reader;

import com.weedow.schemaorg.generator.model.jsonld.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static com.weedow.schemaorg.generator.model.handler.ModelHandlerTestUtils.*;
import static java.util.Map.entry;

class SchemaDefinitionReaderImplTest {

    @Test
    void read() throws SchemaDefinitionReaderException {
        final SchemaDefinitionReader schemaDefinitionReader = new SchemaDefinitionReaderImpl();
        SchemaDefinition schemaDefinition = schemaDefinitionReader.read(getClass().getResourceAsStream("/schemaorg-current-https.jsonld"));

        Assertions.assertThat(schemaDefinition).isNotNull();
        Assertions.assertThat(schemaDefinition.getContext()).isNotEmpty()
                .containsExactly(
                        entry("bibo", "http://purl.org/ontology/bibo/"),
                        entry("brick", "https://brickschema.org/schema/Brick#"),
                        entry("cmns-cls", "https://www.omg.org/spec/Commons/Classifiers/"),
                        entry("cmns-col", "https://www.omg.org/spec/Commons/Collections/"),
                        entry("cmns-dt", "https://www.omg.org/spec/Commons/DatesAndTimes/"),
                        entry("cmns-ge", "https://www.omg.org/spec/Commons/GeopoliticalEntities/"),
                        entry("cmns-id", "https://www.omg.org/spec/Commons/Identifiers/"),
                        entry("cmns-loc", "https://www.omg.org/spec/Commons/Locations/"),
                        entry("cmns-q", "https://www.omg.org/spec/Commons/Quantities/"),
                        entry("cmns-txt", "https://www.omg.org/spec/Commons/Text/"),
                        entry("csvw", "http://www.w3.org/ns/csvw#"),
                        entry("dc", "http://purl.org/dc/elements/1.1/"),
                        entry("dcam", "http://purl.org/dc/dcam/"),
                        entry("dcat", "http://www.w3.org/ns/dcat#"),
                        entry("dcmitype", "http://purl.org/dc/dcmitype/"),
                        entry("dct", "http://purl.org/dc/terms/"),
                        entry("dcterms", "http://purl.org/dc/terms/"),
                        entry("dctype", "http://purl.org/dc/dcmitype/"),
                        entry("doap", "http://usefulinc.com/ns/doap#"),
                        entry("eli", "http://data.europa.eu/eli/ontology#"),
                        entry("fibo-be-corp-corp", "https://spec.edmcouncil.org/fibo/ontology/BE/Corporations/Corporations/"),
                        entry("fibo-be-ge-ge", "https://spec.edmcouncil.org/fibo/ontology/BE/GovernmentEntities/GovernmentEntities/"),
                        entry("fibo-be-le-cb", "https://spec.edmcouncil.org/fibo/ontology/BE/LegalEntities/CorporateBodies/"),
                        entry("fibo-be-le-lp", "https://spec.edmcouncil.org/fibo/ontology/BE/LegalEntities/LegalPersons/"),
                        entry("fibo-be-nfp-nfp", "https://spec.edmcouncil.org/fibo/ontology/BE/NotForProfitOrganizations/NotForProfitOrganizations/"),
                        entry("fibo-be-oac-cctl", "https://spec.edmcouncil.org/fibo/ontology/BE/OwnershipAndControl/CorporateControl/"),
                        entry("fibo-fbc-dae-dbt", "https://spec.edmcouncil.org/fibo/ontology/FBC/DebtAndEquities/Debt/"),
                        entry("fibo-fbc-pas-fpas", "https://spec.edmcouncil.org/fibo/ontology/FBC/ProductsAndServices/FinancialProductsAndServices/"),
                        entry("fibo-fnd-acc-cur", "https://spec.edmcouncil.org/fibo/ontology/FND/Accounting/CurrencyAmount/"),
                        entry("fibo-fnd-agr-ctr", "https://spec.edmcouncil.org/fibo/ontology/FND/Agreements/Contracts/"),
                        entry("fibo-fnd-arr-doc", "https://spec.edmcouncil.org/fibo/ontology/FND/Arrangements/Documents/"),
                        entry("fibo-fnd-arr-lif", "https://spec.edmcouncil.org/fibo/ontology/FND/Arrangements/Lifecycles/"),
                        entry("fibo-fnd-dt-oc", "https://spec.edmcouncil.org/fibo/ontology/FND/DatesAndTimes/Occurrences/"),
                        entry("fibo-fnd-org-org", "https://spec.edmcouncil.org/fibo/ontology/FND/Organizations/Organizations/"),
                        entry("fibo-fnd-pas-pas", "https://spec.edmcouncil.org/fibo/ontology/FND/ProductsAndServices/ProductsAndServices/"),
                        entry("fibo-fnd-plc-adr", "https://spec.edmcouncil.org/fibo/ontology/FND/Places/Addresses/"),
                        entry("fibo-fnd-plc-fac", "https://spec.edmcouncil.org/fibo/ontology/FND/Places/Facilities/"),
                        entry("fibo-fnd-plc-loc", "https://spec.edmcouncil.org/fibo/ontology/FND/Places/Locations/"),
                        entry("fibo-fnd-pty-pty", "https://spec.edmcouncil.org/fibo/ontology/FND/Parties/Parties/"),
                        entry("fibo-fnd-rel-rel", "https://spec.edmcouncil.org/fibo/ontology/FND/Relations/Relations/"),
                        entry("fibo-pay-ps-ps", "https://spec.edmcouncil.org/fibo/ontology/PAY/PaymentServices/PaymentServices/"),
                        entry("foaf", "http://xmlns.com/foaf/0.1/"),
                        entry("gleif-L1", "https://www.gleif.org/ontology/L1/"),
                        entry("gs1", "https://ref.gs1.org/voc/"),
                        entry("hydra", "http://www.w3.org/ns/hydra/core#"),
                        entry("lcc-3166-1", "https://www.omg.org/spec/LCC/Countries/ISO3166-1-CountryCodes/"),
                        entry("lcc-4217", "https://www.omg.org/spec/LCC/Countries/ISO4217-CurrencyCodes/"),
                        entry("lcc-cr", "https://www.omg.org/spec/LCC/Countries/CountryRepresentation/"),
                        entry("lcc-lr", "https://www.omg.org/spec/LCC/Languages/LanguageRepresentation/"),
                        entry("lrmoo", "http://iflastandards.info/ns/lrm/lrmoo/"),
                        entry("mo", "http://purl.org/ontology/mo/"),
                        entry("odrl", "http://www.w3.org/ns/odrl/2/"),
                        entry("org", "http://www.w3.org/ns/org#"),
                        entry("owl", "http://www.w3.org/2002/07/owl#"),
                        entry("prof", "http://www.w3.org/ns/dx/prof/"),
                        entry("prov", "http://www.w3.org/ns/prov#"),
                        entry("qb", "http://purl.org/linked-data/cube#"),
                        entry("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
                        entry("rdfs", "http://www.w3.org/2000/01/rdf-schema#"),
                        entry("sarif", "http://sarif.info/"),
                        entry("schema", "https://schema.org/"),
                        entry("sh", "http://www.w3.org/ns/shacl#"),
                        entry("skos", "http://www.w3.org/2004/02/skos/core#"),
                        entry("snomed", "http://purl.bioontology.org/ontology/SNOMEDCT/"),
                        entry("sosa", "http://www.w3.org/ns/sosa/"),
                        entry("ssn", "http://www.w3.org/ns/ssn/"),
                        entry("time", "http://www.w3.org/2006/time#"),
                        entry("unece", "http://unece.org/vocab#"),
                        entry("vann", "http://purl.org/vocab/vann/"),
                        entry("vcard", "http://www.w3.org/2006/vcard/ns#"),
                        entry("void", "http://rdfs.org/ns/void#"),
                        entry("xsd", "http://www.w3.org/2001/XMLSchema#")
                );
        Assertions.assertThat(schemaDefinition.getGraph()).hasSize(3187);

        Assertions.assertThat(filter(schemaDefinition, DefaultItem.class::isInstance)).hasSize(526);
        // Example with one DefaultItem
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:OriginalMediaContent".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:OriginalMediaContent",
                                List.of("schema:MediaManipulationRatingEnumeration"),
                                comment("en",
                                        """
                                                Content coded 'as original media content' in a [[MediaReview]], considered in the context of how it was published or shared.
                                                
                                                For a [[VideoObject]] to be 'original': No evidence the footage has been misleadingly altered or manipulated, though it may contain false or misleading claims.
                                                
                                                For an [[ImageObject]] to be 'original': No evidence the image has been misleadingly altered or manipulated, though it may still contain false or misleading claims.
                                                
                                                For an [[ImageObject]] with embedded text to be 'original': No evidence the image has been misleadingly altered or manipulated, though it may still contain false or misleading claims.
                                                
                                                For an [[AudioObject]] to be 'original': No evidence the audio has been misleadingly altered or manipulated, though it may contain false or misleading claims.
                                                """
                                ),
                                label("en", "OriginalMediaContent"),
                                null, null, null, null, List.of(partOf("https://pending.schema.org")), List.of(source("https://github.com/schemaorg/schemaorg/issues/2450")), null
                        )
                );

        Assertions.assertThat(filter(schemaDefinition, ClassItem.class::isInstance)).hasSize(1003);
        // Example with ClassItems
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:StatusEnumeration".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                ClassItem.class,
                                "schema:StatusEnumeration",
                                List.of("rdfs:Class"),
                                comment("en", "Lists or enumerations dealing with status types."),
                                label("en", "StatusEnumeration"),
                                null, null, null, List.of(subClassOf("schema:Enumeration")), null, List.of(source("https://github.com/schemaorg/schemaorg/issues/2604")), null
                        )
                );
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:URL".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                ClassItem.class,
                                "schema:URL",
                                List.of("rdfs:Class"),
                                comment("en", "Data type: URL."),
                                label("en", "URL"),
                                null, null, null, List.of(subClassOf("schema:Text")), null, null, null
                        )
                );
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:Person".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                ClassItem.class,
                                "schema:Person",
                                List.of("rdfs:Class"),
                                comment("en", "A person (alive, dead, undead, or fictional)."),
                                label("en", "Person"),
                                null, null, null, List.of(subClassOf("schema:Thing")), null, null, List.of(contributor("https://schema.org/docs/collab/rNews"))
                        )
                );

        Assertions.assertThat(filter(schemaDefinition, graphItem -> {
                    List<String> types = graphItem.getTypes();
                    return types.size() == 2 && types.contains("rdfs:Class") && types.contains("schema:DataType");
                }))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:Text",
                                List.of("rdfs:Class", "schema:DataType"),
                                comment("en", "Data type: Text."),
                                label("en", "Text"),
                                null, null, null, null, null, null, null
                        ),
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:Boolean",
                                List.of("schema:DataType", "rdfs:Class"),
                                comment("en", "Boolean: True or False."),
                                label("en", "Boolean"),
                                null, null, null, null, null, null, null
                        ),
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:Number",
                                List.of("rdfs:Class", "schema:DataType"),
                                comment("en",
                                        "Data type: Number.\\n\\n" +
                                                "Usage guidelines:\\n\\n" +
                                                "* Use values from 0123456789 (Unicode 'DIGIT ZERO' (U+0030) to 'DIGIT NINE' (U+0039)) rather than superficially similar Unicode symbols.\\n" +
                                                "* Use '.' (Unicode 'FULL STOP' (U+002E)) rather than ',' to indicate a decimal point. Avoid using these symbols as a readability separator."
                                ),
                                label("en", "Number"),
                                null, null, null, null, null, null, null
                        ),
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:Date",
                                List.of("rdfs:Class", "schema:DataType"),
                                comment("en", "A date value in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)."),
                                label("en", "Date"),
                                null, null, null, null, null, null, null
                        ),
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:Time",
                                List.of("rdfs:Class", "schema:DataType"),
                                comment("en", "A point in time recurring on multiple days in the form hh:mm:ss[Z|(+|-)hh:mm] (see [XML schema for details](http://www.w3.org/TR/xmlschema-2/#time))."),
                                label("en", "Time"),
                                null, null, null, null, null, null, null
                        ),
                        Tuple.tuple(
                                DefaultItem.class,
                                "schema:DateTime",
                                List.of("rdfs:Class", "schema:DataType"),
                                comment("en", "A combination of date and time of day in the form [-]CCYY-MM-DDThh:mm:ss[Z|(+|-)hh:mm] (see Chapter 5.4 of ISO 8601)."),
                                label("en", "DateTime"),
                                null, null, null, null, null, null, null
                        )
                );

        Assertions.assertThat(filter(schemaDefinition, PropertyItem.class::isInstance)).hasSize(1658);
        // Example with PropertyItems
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:season".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                PropertyItem.class,
                                "schema:season",
                                List.of("rdf:Property"),
                                comment("en", "A season in a media series."),
                                label("en", "season"),
                                List.of(domainInclude("schema:TVSeries"), domainInclude("schema:RadioSeries"), domainInclude("schema:VideoGameSeries")),
                                List.of(rangeInclude("schema:CreativeWorkSeason"), rangeInclude("schema:URL")),
                                supersededBy("schema:containsSeason"),
                                null, null, null, null
                        )
                );
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:telephone".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                PropertyItem.class,
                                "schema:telephone",
                                List.of("rdf:Property"),
                                comment("en", "The telephone number."),
                                label("en", "telephone"),
                                List.of(domainInclude("schema:Place"), domainInclude("schema:ContactPoint"), domainInclude("schema:Person"), domainInclude("schema:Organization")),
                                List.of(rangeInclude("schema:Text")),
                                null, null, null, null, null
                        )
                );
        Assertions.assertThat(filter(schemaDefinition, graphItem -> "schema:governmentBenefitsInfo".equals(graphItem.getId())))
                .extracting("class", "id", "types", "comment", "label", "domainIncludes", "rangeIncludes", "supersededBy", "subClassOf", "partOf", "source", "contributor")
                .containsExactly(
                        Tuple.tuple(
                                PropertyItem.class,
                                "schema:governmentBenefitsInfo",
                                List.of("rdf:Property"),
                                comment("en", "governmentBenefitsInfo provides information about government benefits associated with a SpecialAnnouncement."),
                                label("en", "governmentBenefitsInfo"),
                                List.of(domainInclude("schema:SpecialAnnouncement")),
                                List.of(rangeInclude("schema:GovernmentService")),
                                null,
                                null,
                                List.of(partOf("https://pending.schema.org")), List.of(source("https://github.com/schemaorg/schemaorg/issues/2534")), null
                        )
                );
    }

    @Test
    void throw_exception_when_read_invalid_jsonld() {
        final SchemaDefinitionReader schemaDefinitionReader = new SchemaDefinitionReaderImpl();
        Assertions.assertThatThrownBy(() -> schemaDefinitionReader.read(getClass().getResourceAsStream("/data/invalid-schemaorg.jsonld")))
                .isInstanceOf(SchemaDefinitionReaderException.class)
                .hasMessage("Could not read the JSON schema definition: Unrecognized token 'invalid': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n" +
                        " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 8]");
    }

    private static List<GraphItem> filter(SchemaDefinition schemaDefinition, Predicate<GraphItem> predicate) {
        return schemaDefinition.getGraph().stream().filter(predicate).toList();
    }
}