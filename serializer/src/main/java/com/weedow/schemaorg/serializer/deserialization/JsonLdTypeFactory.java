package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.weedow.schemaorg.commons.generator.GeneratorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JsonLdTypeFactory extends TypeFactory {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLdTypeFactory.class);

    private static final List<String> SCHEMA_ORG_PROPERTIES = List.of(
            GeneratorConstants.SCHEMA_ORG_PROP_COMMONS,
            GeneratorConstants.SCHEMA_ORG_PROP_DATA_TYPE,
            GeneratorConstants.SCHEMA_ORG_PROP_MODEL_IMPL
    );

    private final Map<String, Class<?>> types = new HashMap<>();

    public JsonLdTypeFactory() {
        super((LookupCache<Object, JavaType>) null);

        try (InputStream schemaOrgJavaProperties = Thread.currentThread().getContextClassLoader().getResourceAsStream(GeneratorConstants.SCHEMA_ORG_PROP_FILENAME)) {
            Properties properties = new Properties();
            properties.load(schemaOrgJavaProperties);

            SCHEMA_ORG_PROPERTIES.forEach(prop -> {
                final String packageName = properties.getProperty(prop);
                PackageScanner.getClassesIn(packageName).forEach(clazz -> types.put(clazz.getSimpleName(), clazz));
            });

            // Link each Model interface to Model implementation
            final String packageName = properties.getProperty(GeneratorConstants.SCHEMA_ORG_PROP_MODEL);
            PackageScanner.getClassesIn(packageName).forEach(clazz -> types.put(clazz.getSimpleName(), types.getOrDefault(clazz.getSimpleName() + "Impl", clazz)));
        } catch (IOException e) {
            LOG.warn("Could not load the properties file {}", GeneratorConstants.SCHEMA_ORG_PROP_FILENAME);
        }
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        final Class<?> clazz = types.get(className);
        return clazz != null ? clazz : super.findClass(className);
    }
}
