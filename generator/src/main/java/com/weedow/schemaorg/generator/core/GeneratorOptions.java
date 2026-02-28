package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.core.handler.CompleteHandler;
import com.weedow.schemaorg.generator.core.handler.ErrorHandler;
import com.weedow.schemaorg.generator.core.handler.SuccessHandler;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration options for Schema.org model generation.
 */
@Data
@Accessors(chain = true)
public final class GeneratorOptions {

    /** Default output directory for generated sources. */
    public static final Path DEFAULT_OUTPUT_DIR = Path.of("target", "generated-sources", "schemaorg");

    /** Default package name for model interfaces. */
    public static final String DEFAULT_MODEL_PACKAGE = "org.schema.model";

    /** Default package name for model implementations. */
    public static final String DEFAULT_MODEL_IMPL_PACKAGE = "org.schema.model.impl";

    /** Default package name for data type classes. */
    public static final String DEFAULT_DATE_TYPE_PACKAGE = "org.schema.model.datatype";

    /**
     * Output directory for generated sources.
     *
     * @return Output directory for generated sources
     * @param outputFolder Output directory for generated sources
     */
    private Path outputFolder = DEFAULT_OUTPUT_DIR;
    /**
     * Package name for model interfaces.
     *
     * @return Package name for model interfaces
     * @param modelPackage Package name for model interfaces
     */
    private String modelPackage = DEFAULT_MODEL_PACKAGE;
    /**
     * Package name for model implementations.
     *
     * @return Package name for model implementations
     * @param modelImplPackage Package name for model implementations
     */
    private String modelImplPackage = DEFAULT_MODEL_IMPL_PACKAGE;
    /**
     * Package name for data type classes.
     *
     * @return Package name for data type classes
     * @param dataTypePackage Package name for data type classes
     */
    private String dataTypePackage = DEFAULT_DATE_TYPE_PACKAGE;

    /**
     * Whether the common models are copied. Default is true.
     *
     * @return Whether the common models are copied
     * @param copyCommonModels Whether the common models are copied
     */
    private boolean copyCommonModels = true;

    /**
     * Specific models to generate.
     *
     * @return Specific models to generate
     * @param models Specific models to generate
     */
    private List<String> models;

    /**
     * Success handlers to be notified when a file is successfully generated.
     *
     * @return Success handlers to be notified when a file is successfully generated
     */
    @Setter(AccessLevel.NONE)
    private final List<SuccessHandler> successHandlers = new ArrayList<>();
    /**
     * Error handlers to be notified when generation fails.
     *
     * @return Error handlers to be notified when generation fails
     */
    @Setter(AccessLevel.NONE)
    private final List<ErrorHandler> errorHandlers = new ArrayList<>();
    /**
     * Complete handler to be notified when generation completes.
     *
     * @return Complete handler to be notified when generation completes
     */
    @Setter(AccessLevel.NONE)
    private final List<CompleteHandler> completeHandlers = new ArrayList<>();

    /**
     * Gets the folder path for model interfaces.
     *
     * @return the resolved model folder path
     */
    public Path getModelFolder() {
        return resolvePath(modelPackage);
    }

    /**
     * Gets the folder path for model implementations.
     *
     * @return the resolved model implementation folder path
     */
    public Path getModelImplFolder() {
        return resolvePath(modelImplPackage);
    }

    /**
     * Gets the folder path for data type classes.
     *
     * @return the resolved data type folder path
     */
    public Path getDataTypeFolder() {
        return resolvePath(dataTypePackage);
    }

    /**
     * <p>Convert the given package as {@link Path} and resolve this path against the outputFolder path.</p>
     * <p>Example:</p>
     * <pre>
     * <b>Output folder:</b> /target/generated-sources/schemaorg
     * <b>Package to resolve:</b> com.weedow.commons
     * <b>==&gt; Result:</b> /target/generated-sources/schemaorg/com/weedow/commons
     * </pre>
     *
     * @param packageToResolve Package value to resolve against the outputFolder path.
     * @return The resolved path
     */
    public Path resolvePath(String packageToResolve) {
        return outputFolder.resolve(Path.of("", packageToResolve.split("\\.")));
    }

    /**
     * Adds a success handler to be notified when a file is successfully generated.
     *
     * @param successHandler the success handler to add
     * @return this GeneratorOptions for method chaining
     */
    public GeneratorOptions addSuccessHandler(SuccessHandler successHandler) {
        successHandlers.add(successHandler);
        return this;
    }

    /**
     * Adds an error handler to be notified when generation fails.
     *
     * @param errorHandler the error handler to add
     * @return this GeneratorOptions for method chaining
     */
    public GeneratorOptions addErrorHandler(ErrorHandler errorHandler) {
        errorHandlers.add(errorHandler);
        return this;
    }

    /**
     * Adds a complete handler to be notified when generation completes.
     *
     * @param completeHandler the complete handler to add
     * @return this GeneratorOptions for method chaining
     */
    public GeneratorOptions addCompleteHandler(CompleteHandler completeHandler) {
        completeHandlers.add(completeHandler);
        return this;
    }
}
