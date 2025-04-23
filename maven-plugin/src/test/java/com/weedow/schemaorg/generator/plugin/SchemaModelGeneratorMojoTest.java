package com.weedow.schemaorg.generator.plugin;

import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelGeneratorMojoTest {

    private static final boolean VERBOSE = true;
    private static final String SCHEMA_VERSION = "13.0";
    private static final File OUTPUT = new File("${project.build.directory}/generated-sources/schemaorg");
    private static final String MODEL_PACKAGE = "org.schema.model";
    private static final String MODEL_IMPL_PACKAGE = "org.schema.model.impl";
    private static final String DATA_TYPE_PACKAGE = "org.schema.model.datatype";
    private static final List<String> MODELS = List.of("Hotel", "Thing");
    private static final boolean JAVA_TYPES = false;
    private static final boolean SKIP = false;
    private static final SourcesAndResourcesProcessing SOURCES_AND_RESOURCES_PROCESSING = SourcesAndResourcesProcessing.SOURCES_AND_RESOURCES;

    @Test
    void execute() throws MojoExecutionException, MojoFailureException {
        final Log log = mock(Log.class);
        MavenProject project = mock(MavenProject.class);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mockSchemaModelGeneratorBuilder(true);

        SchemaModelGenerator generator = mock(SchemaModelGenerator.class);
        when(schemaModelGeneratorBuilder.build()).thenReturn(generator);

        SchemaModelGeneratorMojo mojo = new MojoBuilder()
                .log(log)
                .schemaModelGeneratorBuilder(schemaModelGeneratorBuilder)
                .param("verbose", VERBOSE)
                .param("schemaVersion", SCHEMA_VERSION)
                .param("output", OUTPUT)
                .param("modelPackage", MODEL_PACKAGE)
                .param("modelImplPackage", MODEL_IMPL_PACKAGE)
                .param("dataTypePackage", DATA_TYPE_PACKAGE)
                .param("models", MODELS)
                .param("javaTypes", JAVA_TYPES)
                .param("skip", SKIP)
                .param("sourcesAndResourcesProcessing", SOURCES_AND_RESOURCES_PROCESSING)
                .param("project", project)
                .build();

        mojo.execute();

        verify(generator).generate();
        verify(project).addCompileSourceRoot(OUTPUT.toString());
        verify(project).addResource(any(Resource.class));
        verify(log).info("Adding the generated java types and generated resources as compiled source root.");
        verify(log).info("Finished: 10 s");
    }

    @Test
    void execute_with_common_models_in_project() throws MojoExecutionException, MojoFailureException {
        final Log log = mock(Log.class);
        MavenProject project = mock(MavenProject.class);
        Artifact artifact = mock(Artifact.class);
        when(artifact.getGroupId()).thenReturn("com.weedow");
        when(artifact.getArtifactId()).thenReturn("schema-org-java-commons");
        when(project.getArtifacts()).thenReturn(Set.of(artifact));

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mockSchemaModelGeneratorBuilder(false);

        SchemaModelGenerator generator = mock(SchemaModelGenerator.class);
        when(schemaModelGeneratorBuilder.build()).thenReturn(generator);

        SchemaModelGeneratorMojo mojo = new MojoBuilder()
                .log(log)
                .schemaModelGeneratorBuilder(schemaModelGeneratorBuilder)
                .param("verbose", VERBOSE)
                .param("schemaVersion", SCHEMA_VERSION)
                .param("output", OUTPUT)
                .param("modelPackage", MODEL_PACKAGE)
                .param("modelImplPackage", MODEL_IMPL_PACKAGE)
                .param("dataTypePackage", DATA_TYPE_PACKAGE)
                .param("models", MODELS)
                .param("javaTypes", JAVA_TYPES)
                .param("skip", SKIP)
                .param("sourcesAndResourcesProcessing", SOURCES_AND_RESOURCES_PROCESSING)
                .param("project", project)
                .build();

        mojo.execute();

        verify(generator).generate();
        verify(project).addCompileSourceRoot(OUTPUT.toString());
        verify(project).addResource(any(Resource.class));
        verify(log).info("Adding the generated java types and generated resources as compiled source root.");
        verify(log).info("Finished: 10 s");
    }

    @Test
    void execute_with_skip_option() throws MojoExecutionException, MojoFailureException {
        boolean skip = true;
        MavenProject project = mock(MavenProject.class);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mock(SchemaModelGeneratorBuilder.class);

        final Log log = mock(Log.class);

        SchemaModelGeneratorMojo mojo = new MojoBuilder()
                .log(log)
                .schemaModelGeneratorBuilder(schemaModelGeneratorBuilder)
                .param("verbose", VERBOSE)
                .param("schemaVersion", SCHEMA_VERSION)
                .param("output", OUTPUT)
                .param("modelPackage", MODEL_PACKAGE)
                .param("modelImplPackage", MODEL_IMPL_PACKAGE)
                .param("dataTypePackage", DATA_TYPE_PACKAGE)
                .param("models", MODELS)
                .param("javaTypes", JAVA_TYPES)
                .param("skip", skip)
                .param("sourcesAndResourcesProcessing", SOURCES_AND_RESOURCES_PROCESSING)
                .param("project", project)
                .build();

        mojo.execute();

        verifyNoInteractions(schemaModelGeneratorBuilder);
        verify(project).addCompileSourceRoot(OUTPUT.toString());
        verify(project).addResource(any(Resource.class));
        verify(log).info("Code generation is skipped.");
    }

    @Test
    void execute_with_skip_option_and_skip_compilation() throws MojoExecutionException, MojoFailureException {
        boolean skip = true;
        SourcesAndResourcesProcessing sourcesAndResourcesProcessing = SourcesAndResourcesProcessing.NOTHING;
        MavenProject project = mock(MavenProject.class);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mock(SchemaModelGeneratorBuilder.class);

        final Log log = mock(Log.class);

        SchemaModelGeneratorMojo mojo = new MojoBuilder()
                .log(log)
                .schemaModelGeneratorBuilder(schemaModelGeneratorBuilder)
                .param("verbose", VERBOSE)
                .param("schemaVersion", SCHEMA_VERSION)
                .param("output", OUTPUT)
                .param("modelPackage", MODEL_PACKAGE)
                .param("modelImplPackage", MODEL_IMPL_PACKAGE)
                .param("dataTypePackage", DATA_TYPE_PACKAGE)
                .param("models", MODELS)
                .param("javaTypes", JAVA_TYPES)
                .param("skip", skip)
                .param("sourcesAndResourcesProcessing", sourcesAndResourcesProcessing)
                .param("project", project)
                .build();

        mojo.execute();

        verifyNoInteractions(schemaModelGeneratorBuilder);
        verifyNoInteractions(project);
        verify(log).info("Code generation is skipped.");
    }

    @Test
    void execute_with_test_sources_processing() throws MojoExecutionException, MojoFailureException {
        final Log log = mock(Log.class);
        MavenProject project = mock(MavenProject.class);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mockSchemaModelGeneratorBuilder(true);

        SchemaModelGenerator generator = mock(SchemaModelGenerator.class);
        when(schemaModelGeneratorBuilder.build()).thenReturn(generator);

        SchemaModelGeneratorMojo mojo = new MojoBuilder()
                .log(log)
                .schemaModelGeneratorBuilder(schemaModelGeneratorBuilder)
                .param("verbose", VERBOSE)
                .param("schemaVersion", SCHEMA_VERSION)
                .param("output", OUTPUT)
                .param("modelPackage", MODEL_PACKAGE)
                .param("modelImplPackage", MODEL_IMPL_PACKAGE)
                .param("dataTypePackage", DATA_TYPE_PACKAGE)
                .param("models", MODELS)
                .param("javaTypes", JAVA_TYPES)
                .param("skip", SKIP)
                .param("sourcesAndResourcesProcessing", SourcesAndResourcesProcessing.TEST_SOURCES_AND_RESOURCES)
                .param("project", project)
                .build();

        mojo.execute();

        verify(generator).generate();
        verify(project).addTestCompileSourceRoot(OUTPUT.toString());
        verify(project).addTestResource(any(Resource.class));
        verify(log).info("Adding the generated java types and generated resources as compiled test-source root.");
        verify(log).info("Finished: 10 s");
    }

    private static SchemaModelGeneratorBuilder mockSchemaModelGeneratorBuilder(boolean copyCommonModels) {
        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(SCHEMA_VERSION);

        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(OUTPUT.toPath())
                .setModels(MODELS)
                .setModelPackage(MODEL_PACKAGE)
                .setModelImplPackage(MODEL_IMPL_PACKAGE)
                .setDataTypePackage(DATA_TYPE_PACKAGE)
                .setCopyCommonModels(copyCommonModels);

        SchemaModelGeneratorBuilder schemaModelGeneratorBuilder = mock(SchemaModelGeneratorBuilder.class);
        when(schemaModelGeneratorBuilder.parserOptions(parserOptions)).thenReturn(schemaModelGeneratorBuilder);
        when(schemaModelGeneratorBuilder.generatorOptions(argThat(new GeneratorOptionsWithIgnoredHandlerMatcher(generatorOptions)))).thenReturn(schemaModelGeneratorBuilder);
        when(schemaModelGeneratorBuilder.verbose(VERBOSE)).thenReturn(schemaModelGeneratorBuilder);

        return schemaModelGeneratorBuilder;
    }

    private record GeneratorOptionsWithIgnoredHandlerMatcher(
            GeneratorOptions expectedOptions
    ) implements ArgumentMatcher<GeneratorOptions> {

        @Override
        public boolean matches(GeneratorOptions actualOptions) {
            if (actualOptions == null) return expectedOptions == null;
            if (expectedOptions == null) return false;
            if (actualOptions.getCompleteHandlers().size() != 1) return false;

            // CompleteHandler is called explicitly, because SchemaModelGenerator is mocked and so CompleteHandlers are not called
            actualOptions.getCompleteHandlers().get(0).onComplete(Duration.ofSeconds(10));

            return Objects.equals(actualOptions.getOutputFolder(), expectedOptions.getOutputFolder()) &&
                    Objects.equals(actualOptions.getModelPackage(), expectedOptions.getModelPackage()) &&
                    Objects.equals(actualOptions.getModelImplPackage(), expectedOptions.getModelImplPackage()) &&
                    Objects.equals(actualOptions.getDataTypePackage(), expectedOptions.getDataTypePackage()) &&
                    actualOptions.isCopyCommonModels() == expectedOptions.isCopyCommonModels() &&
                    Objects.equals(actualOptions.getModels(), expectedOptions.getModels()) &&
                    actualOptions.getCompleteHandlers().size() == 1;
        }
    }
}