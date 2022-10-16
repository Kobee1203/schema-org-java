package com.weedow.schemaorg.generator.core;

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

    private Path outputFolder = Path.of("target", "generated-sources", "schemaorg");
    private String modelPackage = "org.schema.model";
    private String modelImplPackage = "org.schema.model.impl";
    private String dataTypePackage = "org.schema.model.datatype";

    private List<String> models;

    @Setter(AccessLevel.NONE)
    private final List<SuccessHandler> successHandlers = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private final List<ErrorHandler> errorHandlers = new ArrayList<>();

    public Path getModelFolder() {
        return outputFolder.resolve(Path.of("", modelPackage.split("\\.")));
    }

    public Path getModelImplFolder() {
        return outputFolder.resolve(Path.of("", modelImplPackage.split("\\.")));
    }

    public Path getDataTypeFolder() {
        return outputFolder.resolve(Path.of("", dataTypePackage.split("\\.")));
    }

    public GeneratorOptions addSuccessHandler(SuccessHandler successHandler) {
        successHandlers.add(successHandler);
        return this;
    }

    public GeneratorOptions addErrorHandler(ErrorHandler errorHandler) {
        errorHandlers.add(errorHandler);
        return this;
    }
}
