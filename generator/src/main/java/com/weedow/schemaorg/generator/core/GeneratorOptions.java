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

@Data
@Accessors(chain = true)
public final class GeneratorOptions {

    public static final Path DEFAULT_OUTPUT_DIR = Path.of("target", "generated-sources", "schemaorg");
    public static final String DEFAULT_MODEL_PACKAGE = "org.schema.model";
    public static final String DEFAULT_MODEL_IMPL_PACKAGE = "org.schema.model.impl";
    public static final String DEFAULT_DATE_TYPE_PACKAGE = "org.schema.model.datatype";

    private Path outputFolder = DEFAULT_OUTPUT_DIR;
    private String modelPackage = DEFAULT_MODEL_PACKAGE;
    private String modelImplPackage = DEFAULT_MODEL_IMPL_PACKAGE;
    private String dataTypePackage = DEFAULT_DATE_TYPE_PACKAGE;

    private boolean copyCommonModels = true;

    private List<String> models;

    @Setter(AccessLevel.NONE)
    private final List<SuccessHandler> successHandlers = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private final List<ErrorHandler> errorHandlers = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private final List<CompleteHandler> completeHandlers = new ArrayList<>();

    public Path getModelFolder() {
        return resolvePath(modelPackage);
    }

    public Path getModelImplFolder() {
        return resolvePath(modelImplPackage);
    }

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

    public GeneratorOptions addSuccessHandler(SuccessHandler successHandler) {
        successHandlers.add(successHandler);
        return this;
    }

    public GeneratorOptions addErrorHandler(ErrorHandler errorHandler) {
        errorHandlers.add(errorHandler);
        return this;
    }

    public GeneratorOptions addCompleteHandler(CompleteHandler completeHandler) {
        completeHandlers.add(completeHandler);
        return this;
    }
}
