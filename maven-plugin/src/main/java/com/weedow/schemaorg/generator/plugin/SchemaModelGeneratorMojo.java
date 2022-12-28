package com.weedow.schemaorg.generator.plugin;

import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Goal which generates Schema.org models.
 *
 * @see <a href="https://maven.apache.org/plugin-developers/index.html">https://maven.apache.org/plugin-developers/index.html</a>
 * @see <a href="https://maven.apache.org/developers/mojo-api-specification.html">https://maven.apache.org/developers/mojo-api-specification.html</a>
 */
@Mojo(
        name = "generate",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        threadSafe = true,
        requiresDependencyCollection = ResolutionScope.COMPILE
)
public class SchemaModelGeneratorMojo extends AbstractMojo {

    private static final String COMMON_MODELS_GROUP_ID = "com.weedow";
    private static final String COMMON_MODELS_ARTIFACT_ID = "schema-org-java-commons";

    @SuppressWarnings("unused")
    @Parameter(name = "verbose", defaultValue = "false")
    private boolean verbose;

    /**
     * Schema resource location to be used for generation. If not defined, uses the 'schemaVersion' parameter.
     * The value can be either a "classpath:" pseudo URL, a "file:" URL, or a plain file path.
     */
    @SuppressWarnings("unused")
    @Parameter(name = "schemaResource", property = "weedow.schemaorg.generator.maven.plugin.schemaResource")
    private String schemaResource;

    /** Schema version to be used for generation. If not defined, uses the local default resource named `schemaorg-current-https.jsonld` present in the classpath. */
    @SuppressWarnings("unused")
    @Parameter(name = "schemaVersion", property = "weedow.schemaorg.generator.maven.plugin.schemaVersion")
    private String schemaVersion;

    /** List of models to be generated. If not defined, all models are be generated. */
    @SuppressWarnings("unused")
    @Parameter(name = "models", property = "weedow.schemaorg.generator.maven.plugin.models")
    private List<String> models;

    /** Location of the output directory. */
    @SuppressWarnings("unused")
    @Parameter(name = "output", property = "weedow.schemaorg.generator.maven.plugin.output", defaultValue = "${project.build.directory}/generated-sources/schemaorg")
    private File output;

    /** Package of the models */
    @SuppressWarnings("unused")
    @Parameter(name = "modelPackage", property = "weedow.schemaorg.generator.maven.plugin.modelPackage", defaultValue = "org.schema.model")
    private String modelPackage;

    /** Package of the model implementations */
    @SuppressWarnings("unused")
    @Parameter(name = "modelImplPackage", property = "weedow.schemaorg.generator.maven.plugin.modelImplPackage", defaultValue = "org.schema.model.impl")
    private String modelImplPackage;

    /** Package of the data type */
    @SuppressWarnings("unused")
    @Parameter(name = "dataTypePackage", property = "weedow.schemaorg.generator.maven.plugin.dataTypePackage", defaultValue = "org.schema.model.datatype")
    private String dataTypePackage;

    /** Skip the execution. Can also be set globally through the {@code weedow.schemaorg.generator.maven.plugin.skip} property. */
    @SuppressWarnings("unused")
    @Parameter(name = "skip", property = "weedow.schemaorg.generator.maven.plugin.skip", defaultValue = "false")
    private boolean skip;

    /**
     * Specify the behavior of the plugin with the generated java types and generated resources.
     * <ul>
     * <li>Add the output directory to the project as a source root: SOURCES_AND_RESOURCES</li>
     * <li>Add the output directory to the project as a test source root: TEST_SOURCES_AND_RESOURCES</li>
     * <li>Do nothing: NOTHING</li>
     * </ul>
     */
    @SuppressWarnings("unused")
    @Parameter(name = "sourcesAndResourcesProcessing", defaultValue = "SOURCES_AND_RESOURCES")
    private SourcesAndResourcesProcessing sourcesAndResourcesProcessing;

    /** The project being built. */
    @SuppressWarnings("unused")
    @Parameter(readonly = true, required = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().info("Code generation is skipped.");
            // Even when no new sources are generated, the existing ones should still be compiled if needed.
            processSourcesAndResources();
            return;
        }

        // Copy common models if the artifact 'schema-org-java-commons' is not present in the current project
        boolean copyCommonModels = !isCommonModelsPresent();

        long start = System.currentTimeMillis();

        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaResource(schemaResource);
        parserOptions.setSchemaVersion(schemaVersion);

        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(output.toPath())
                .setModels(models)
                .setModelPackage(modelPackage)
                .setModelImplPackage(modelImplPackage)
                .setDataTypePackage(dataTypePackage)
                .setCopyCommonModels(copyCommonModels);

        final SchemaModelGenerator generator = schemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .verbose(verbose)
                .build();
        generator.generate();

        processSourcesAndResources();

        long end = System.currentTimeMillis();
        getLog().info(String.format("Finished: %s s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS)));
    }

    @SuppressWarnings("unchecked")
    private boolean isCommonModelsPresent() {
        boolean schemaOrgJavaCommonsPresent = false;
        Set<Artifact> artifacts = project.getArtifacts();
        if (artifacts != null) {
            schemaOrgJavaCommonsPresent = artifacts.stream().anyMatch(artifact -> COMMON_MODELS_GROUP_ID.equals(artifact.getGroupId()) && COMMON_MODELS_ARTIFACT_ID.equals(artifact.getArtifactId()));
        }
        getLog().info("Is Schema.org Java Commons present? " + schemaOrgJavaCommonsPresent);
        return schemaOrgJavaCommonsPresent;
    }

    private void processSourcesAndResources() {
        final String outputDirectory = output.toString();

        final Resource resource = new Resource();
        resource.setDirectory(outputDirectory);
        resource.setExcludes(List.of("**/*.java"));

        if (SourcesAndResourcesProcessing.SOURCES_AND_RESOURCES.equals(sourcesAndResourcesProcessing)) {
            getLog().info("Adding the generated java types and generated resources as compiled source root.");
            project.addCompileSourceRoot(outputDirectory);
            project.addResource(resource);
        } else if (SourcesAndResourcesProcessing.TEST_SOURCES_AND_RESOURCES.equals(sourcesAndResourcesProcessing)) {
            getLog().info("Adding the generated java types and generated resources as compiled test-source root.");
            project.addTestCompileSourceRoot(outputDirectory);
            project.addTestResource(resource);
        }
    }

    SchemaModelGeneratorBuilder schemaModelGeneratorBuilder() {
        return new SchemaModelGeneratorBuilder();
    }
}
