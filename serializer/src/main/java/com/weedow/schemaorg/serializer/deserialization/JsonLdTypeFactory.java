package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.weedow.schemaorg.commons.generator.GeneratorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public class JsonLdTypeFactory extends TypeFactory {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLdTypeFactory.class);

    private final Map<String, Class<?>> types = new HashMap<>();

    public JsonLdTypeFactory(Map<String, Class<?>> otherTypes) {
        super(new LRUMap<>(16, DEFAULT_MAX_CACHE_SIZE));

        try (InputStream schemaOrgJavaProperties = Thread.currentThread().getContextClassLoader().getResourceAsStream(GeneratorConstants.SCHEMA_ORG_PROP_FILENAME)) {
            Properties properties = new Properties();
            properties.load(schemaOrgJavaProperties);

            consumeProperty(properties, GeneratorConstants.SCHEMA_ORG_PROP_COMMONS, clazz -> types.put(clazz.getSimpleName(), clazz));
            consumeProperty(properties, GeneratorConstants.SCHEMA_ORG_PROP_DATA_TYPE, clazz -> types.put(clazz.getName(), clazz));
            consumeProperty(properties, GeneratorConstants.SCHEMA_ORG_PROP_MODEL, clazz -> types.put("interface." + clazz.getSimpleName(), clazz));
            consumeProperty(properties, GeneratorConstants.SCHEMA_ORG_PROP_MODEL_IMPL, clazz -> types.put(clazz.getSimpleName(), clazz));

            // Link each Model/Enum interface to Model/Enum implementation
            consumeProperty(properties, GeneratorConstants.SCHEMA_ORG_PROP_MODEL, clazz -> {
                final String simpleName = clazz.getSimpleName();

                final Class<?> enumerationClass = types.get("interface.Enumeration");
                String key = enumerationClass != null && enumerationClass.isAssignableFrom(clazz) ? simpleName + "Enum" : simpleName + "Impl";
                types.put(simpleName, types.getOrDefault(key, clazz));
            });
        } catch (IOException e) {
            LOG.warn("Could not load the properties file {}", GeneratorConstants.SCHEMA_ORG_PROP_FILENAME);
        }

        if (otherTypes != null) {
            types.putAll(otherTypes);
        }
    }

    private static void consumeProperty(Properties properties, String key, Consumer<Class<?>> consumer) {
        final String packageName = properties.getProperty(key);
        PackageScanner.getClassesIn(packageName).forEach(consumer);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        final Class<?> clazz = types.get(className);
        return clazz != null ? clazz : super.findClass(className);
    }
}
