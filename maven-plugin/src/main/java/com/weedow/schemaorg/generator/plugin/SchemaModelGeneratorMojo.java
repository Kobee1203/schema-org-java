package com.weedow.schemaorg.generator.plugin;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Goal which generates Schema.org models.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class SchemaModelGeneratorMojo extends AbstractMojo {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorMojo.class);

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

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (verbose) {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.weedow.schemaorg");
            logger.setLevel(Level.DEBUG);
        }

        long start = System.currentTimeMillis();

        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(schemaVersion);

        GeneratorOptions generatorOptions = new GeneratorOptions();
        generatorOptions.setModels(models);

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .build();
        generator.generate();

        long end = System.currentTimeMillis();
        LOG.info("Finished: {} s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS));
    }
}
