package com.weedow.schemaorg.generator.parser;

import com.weedow.schemaorg.commons.utils.ResourceUtils;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.ModelHandler;
import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemaModelParserImpl implements SchemaModelParser {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelParserImpl.class);

    private static final String LATEST_SCHEMA_URL = "https://schema.org/version/latest/schemaorg-current-https.jsonld";
    private static final String VERSIONED_SCHEMA_URL = "https://raw.githubusercontent.com/schemaorg/schemaorg/main/data/releases/%s/schemaorg-current-https.jsonld";
    private static final String SCHEMAORG_DEFINITION_LOCAL_RESOURCE = "classpath:schemaorg-current-https.jsonld";

    private final SchemaDefinitionReader schemaDefinitionReader;
    private final List<ModelHandler> modelHandlers;

    public SchemaModelParserImpl(SchemaDefinitionReader schemaDefinitionReader, List<ModelHandler> modelHandlers) {
        this.schemaDefinitionReader = schemaDefinitionReader;
        this.modelHandlers = modelHandlers;
    }

    @Override
    public Map<String, Type> parse(ParserOptions options) {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        final String schemaResource = options.getSchemaResource();
        final String schemaVersion = options.getSchemaVersion();
        try (InputStream in = getInputStream(schemaResource, schemaVersion)) {
            LOG.info("Parsing the schema definitions...");
            final SchemaDefinition schemaDefinition = schemaDefinitionReader.read(in);

            schemaDefinition.getGraph().forEach(graphItem -> {
                LOG.verbose("id={}, types={}, label={}", graphItem.getId(), graphItem.getTypes(), graphItem.getLabel());

                this.modelHandlers
                        .stream()
                        .filter(modelHandler -> modelHandler.supports(graphItem))
                        .forEach(modelHandler -> modelHandler.handle(schemaDefinitions, graphItem));
            });
            LOG.info("Parsing completed.");
        } catch (Exception e) {
            LOG.warn("Could not generate the schema models: " + e.getMessage(), e);
        }

        return schemaDefinitions;
    }

    private static InputStream getInputStream(String resourceLocation, String version) throws IOException {
        if (resourceLocation != null) {
            LOG.info("Loading resource '{}'", resourceLocation);
            return ResourceUtils.getURL(resourceLocation).openStream();
        } else if (version != null) {
            LOG.info("Downloading version '{}'", version);
            final String url = getUrl(version);
            return ResourceUtils.getURL(url).openStream();
        } else {
            LOG.info("Loading local default resource '{}'", SCHEMAORG_DEFINITION_LOCAL_RESOURCE);
            return ResourceUtils.getURL(SCHEMAORG_DEFINITION_LOCAL_RESOURCE).openStream();
        }
    }

    private static String getUrl(String version) {
        String url = LATEST_SCHEMA_URL;
        if (!version.equals("latest")) { // eg. 13.0 (see https://github.com/schemaorg/schemaorg/tree/main/data/releases)
            url = String.format(VERSIONED_SCHEMA_URL, version);
        }
        return url;
    }
}
