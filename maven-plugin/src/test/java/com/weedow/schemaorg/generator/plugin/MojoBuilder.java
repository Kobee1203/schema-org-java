package com.weedow.schemaorg.generator.plugin;

import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import org.apache.maven.plugin.logging.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MojoBuilder {

    private Log log;
    private SchemaModelGeneratorBuilder schemaModelGeneratorBuilder;
    private final Map<String, Object> params = new HashMap<>();

    public MojoBuilder log(Log log) {
        this.log = log;
        return this;
    }

    public MojoBuilder schemaModelGeneratorBuilder(SchemaModelGeneratorBuilder schemaModelGeneratorBuilder) {
        this.schemaModelGeneratorBuilder = schemaModelGeneratorBuilder;
        return this;
    }

    public MojoBuilder param(String paramName, Object paramValue) {
        params.put(paramName, paramValue);
        return this;
    }

    public SchemaModelGeneratorMojo build() {
        SchemaModelGeneratorMojo mojo = new SchemaModelGeneratorMojo() {
            @Override
            SchemaModelGeneratorBuilder schemaModelGeneratorBuilder() {
                return schemaModelGeneratorBuilder;
            }
        };

        mojo.setLog(log);

        params.forEach((s, o) -> writeField(mojo, s, o));

        return mojo;
    }

    private static void writeField(Object target, String fieldName, Object value) {
        try {
            Class<?> cls = target.getClass();
            Field field = getField(cls, fieldName);
            if (field != null) {
                field.set(target, value);
            } else {
                throw new NullPointerException("Field '" + fieldName + "' of object '" + target + "' is not found.");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getField(final Class<?> cls, final String fieldName) {
        for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
            try {
                final Field field = acls.getDeclaredField(fieldName);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                return field;
            } catch (final NoSuchFieldException ex) { // NOPMD
                // ignore
            }
        }
        return null;
    }
}
