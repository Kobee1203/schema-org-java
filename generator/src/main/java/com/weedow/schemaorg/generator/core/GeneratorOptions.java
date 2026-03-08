package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.core.handler.CompleteHandler;
import com.weedow.schemaorg.generator.core.handler.ErrorHandler;
import com.weedow.schemaorg.generator.core.handler.SuccessHandler;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Filters to restrict the type properties or the type itself.
     *
     * @return List of FilterOption
     * @param filters to apply
     */
    private List<FilterOption> filters;

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

    /**
     * Filter Mode.
     */
    public enum FilterMode {
        /** Include the specified type properties or type */
        INCLUDE,
        /** Exclude the specified type properties or type */
        EXCLUDE;

        /**
         * Whether the given value is a valid FilterMode value.
         *
         * @param value value to check
         * @return {@code true} if the value is valid, {@code false} instead.
         */
        public static boolean isFilterMode(String value) {
            return Arrays.stream(values()).anyMatch(filterMode -> filterMode.name().equalsIgnoreCase(value));
        }
    }

    /**
     * Class representing a filter:
     * <ul>
     *     <li>typeName: required</li>
     *     <li>mode: optional. Default is EXCLUDE</li>
     *     <li>properties: optional. If empty, it means that the type itself is filtered.</li>
     * </ul>
     */
    @Getter
    public static class FilterOption {
        private static final String SEPARATOR = ":";
        private static final String PROP_SEPARATOR = ",";

        /**
         * The type name.
         *
         * @return the type name
         */
        String typeName;
        /**
         * The filter mode.
         *
         * @return the filter mode. Default is FilterMode#EXCLUDE.
         */
        FilterMode mode = FilterMode.EXCLUDE;
        /**
         * The properties
         *
         * @return the type properties to filter.
         */
        List<String> properties = new java.util.ArrayList<>();

        /**
         * Parses a string with format {@code {typeName}:[mode]:[property1,property2]} and returns the corresponding FilterOption.
         * <p>
         * Allowed formats:
         * <ul>
         *     <li>{@code {typeName}:{mode}:{property1,*property2*,schema:property3}}</li>
         *     <li>{@code {typeName}:[property1,*property2*,schema:property3]}</li>
         *     <li>{@code {typeName}:*}</li>
         *     <li>{@code {typeName}:{mode}}</li>
         *     <li>{@code {typeName}}</li>
         * </ul>
         *
         * @param value the value to parse
         * @return a FilterOption
         */
        public static FilterOption parse(String value) {
            FilterOption filter = new FilterOption();
            String[] parts = value.split(SEPARATOR, 3);

            filter.typeName = parts[0];

            if (parts.length > 1) {
                // Case: MyType:(include|exclude) or MyType:(include|exclude):prop1,prop2
                if (FilterMode.isFilterMode(parts[1])) {
                    filter.mode = FilterMode.valueOf(parts[1].toUpperCase());
                    if (parts.length > 2) {
                        filter.properties = List.of(parts[2].split(PROP_SEPARATOR));
                    }
                } else {
                    // Case : MyType:prop1,prop2 (use default mode 'exclude')
                    filter.properties = List.of(parts[1].split(PROP_SEPARATOR));
                }
            }
            return filter;
        }
    }
}
