package com.weedow.schemaorg.generator.plugin;

import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelGeneratorMojoTest {

    @Test
    void execute() throws MojoExecutionException, MojoFailureException {
        boolean verbose = true;
        String schemaVersion = "13.0";
        File output = new File("${project.build.directory}/generated-sources/schemaorg");
        String modelPackage = "org.schema.model";
        String modelImplPackage = "org.schema.model.impl";
        String dataTypePackage = "org.schema.model.datatype";
        List<String> models = List.of("Hotel", "Thing");
        boolean skip = false;
        boolean addCompileSourceRoot = true;
        MavenProject project = mock(MavenProject.class);

        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(schemaVersion);

        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(output.toPath())
                .setModels(models)
                .setModelPackage(modelPackage)
                .setModelImplPackage(modelImplPackage)
                .setDataTypePackage(dataTypePackage);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mock(SchemaModelGeneratorBuilder.class);
        when(schemaModelGeneratorBuilder.parserOptions(parserOptions)).thenReturn(schemaModelGeneratorBuilder);
        when(schemaModelGeneratorBuilder.generatorOptions(generatorOptions)).thenReturn(schemaModelGeneratorBuilder);
        when(schemaModelGeneratorBuilder.verbose(verbose)).thenReturn(schemaModelGeneratorBuilder);

        SchemaModelGenerator generator = mock(SchemaModelGenerator.class);
        when(schemaModelGeneratorBuilder.build()).thenReturn(generator);

        final Log log = mock(Log.class);

        SchemaModelGeneratorMojo plugin = new SchemaModelGeneratorMojo() {
            @Override
            SchemaModelGeneratorBuilder schemaModelGeneratorBuilder() {
                return schemaModelGeneratorBuilder;
            }
        };
        plugin.setLog(log);
        writeField(plugin, "verbose", verbose);
        writeField(plugin, "schemaVersion", schemaVersion);
        writeField(plugin, "output", output);
        writeField(plugin, "modelPackage", modelPackage);
        writeField(plugin, "modelImplPackage", modelImplPackage);
        writeField(plugin, "dataTypePackage", dataTypePackage);
        writeField(plugin, "models", models);
        writeField(plugin, "skip", skip);
        writeField(plugin, "addCompileSourceRoot", addCompileSourceRoot);
        writeField(plugin, "project", project);

        plugin.execute();

        verify(generator).generate();
        verify(project).addCompileSourceRoot(output.toString());
        verify(log).info(String.format("Finished: %s s", TimeUnit.SECONDS.convert(0 /* too fast to reach one second */, TimeUnit.MILLISECONDS)));
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