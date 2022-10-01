package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.core.handler.ErrorHandler;
import com.weedow.schemaorg.generator.core.handler.SuccessHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class GeneratorOptions {

    private File outputFolder = new File("target/schemaorg");
    private String modelPackage = "org.schema.model";
    private String modelImplPackage = "org.schema.model.impl";
    private String dataTypePackage = "org.schema.model.datatype";

    private final List<SuccessHandler> successHandlers = new ArrayList<>();
    private final List<ErrorHandler> errorHandlers = new ArrayList<>();

    public File getOutputFolder() {
        return outputFolder;
    }

    public GeneratorOptions setOutputFolder(File outputFolder) {
        this.outputFolder = outputFolder;
        return this;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public GeneratorOptions setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
        return this;
    }

    public File getModelFolder() {
        return new File(outputFolder, modelPackage.replace(".", "/"));
    }

    public String getModelImplPackage() {
        return modelImplPackage;
    }

    public GeneratorOptions setModelImplPackage(String modelImplPackage) {
        this.modelImplPackage = modelImplPackage;
        return this;
    }

    public File getModelImplFolder() {
        return new File(outputFolder, modelImplPackage.replace(".", "/"));
    }

    public String getDataTypePackage() {
        return dataTypePackage;
    }

    public GeneratorOptions setDataTypePackage(String dataTypePackage) {
        this.dataTypePackage = dataTypePackage;
        return this;
    }

    public File getDataTypeFolder() {
        return new File(outputFolder, dataTypePackage.replace(".", "/"));
    }

    public List<SuccessHandler> getSuccessHandlers() {
        return successHandlers;
    }

    public GeneratorOptions addSuccessHandler(SuccessHandler successHandler) {
        successHandlers.add(successHandler);
        return this;
    }

    public List<ErrorHandler> getErrorHandlers() {
        return errorHandlers;
    }

    public GeneratorOptions addErrorHandler(ErrorHandler errorHandler) {
        errorHandlers.add(errorHandler);
        return this;
    }
}
