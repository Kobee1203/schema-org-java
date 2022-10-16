package com.weedow.schemaorg.generator.plugin;

import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Goal which generates Schema.org models.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class SchemaModelGeneratorMojo extends AbstractMojo {

    @Parameter(name = "verbose", defaultValue = "false")
    private boolean verbose;

    /** Schema version to be used for generation. If not defined, uses the local resource from the plugin. */
    @Parameter(name = "schemaVersion", property = "weedow.schemaorg.generator.maven.plugin.schemaVersion")
    private String schemaVersion;

    /** Location of the output directory. */
    @Parameter(name = "output", property = "weedow.schemaorg.generator.maven.plugin.output", defaultValue = "${project.build.directory}/generated-sources/schemaorg")
    private File output;

    /** Package of the models */
    @Parameter(name = "modelPackage", property = "weedow.schemaorg.generator.maven.plugin.modelPackage", defaultValue = "org.schema.model")
    private String modelPackage;

    /** Package of the model implementations */
    @Parameter(name = "modelImplPackage", property = "weedow.schemaorg.generator.maven.plugin.modelImplPackage", defaultValue = "org.schema.model.impl")
    private String modelImplPackage;

    /** Package of the data type */
    @Parameter(name = "dataTypePackage", property = "weedow.schemaorg.generator.maven.plugin.dataTypePackage", defaultValue = "org.schema.model.datatype")
    private String dataTypePackage;

    @Parameter(name = "models", property = "weedow.schemaorg.generator.maven.plugin.models")
    private List<String> models;

    /** Skip the execution. */
    @Parameter(name = "skip", property = "codegen.skip", defaultValue = "false")
    private boolean skip;

    /** Add the output directory to the project as a source root, so that the generated java types are compiled and included in the project artifact. */
    @Parameter(defaultValue = "true")
    private boolean addCompileSourceRoot = true;

    /** The project being built. */
    @Parameter(readonly = true, required = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().info("Code generation is skipped.");
            // Even when no new sources are generated, the existing ones should
            // still be compiled if needed.
            addCompileSourceRootIfConfigured();
            return;
        }

        long start = System.currentTimeMillis();

        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(schemaVersion);

        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(output.toPath())
                .setModels(models)
                .setModelPackage(modelPackage)
                .setModelImplPackage(modelImplPackage)
                .setDataTypePackage(dataTypePackage);

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .verbose(verbose)
                .build();
        generator.generate();

        addCompileSourceRootIfConfigured();

        long end = System.currentTimeMillis();
        getLog().info(String.format("Finished: %s s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS)));
    }

    private void addCompileSourceRootIfConfigured() {
        if (addCompileSourceRoot) {
            project.addCompileSourceRoot(output.toString());
        }
    }
}
