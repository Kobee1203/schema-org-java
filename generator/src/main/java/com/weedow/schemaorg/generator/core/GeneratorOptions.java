package com.weedow.schemaorg.generator.core;

import java.io.File;

public final class GeneratorOptions {

    private File outputFolder = new File("target/schemaorg");
    private String modelPackage = "org.schema.model";
    private String modelImplPackage = "org.schema.model.impl";
    private String dataTypePackage = "org.schema.model.datatype";

    public File getOutputFolder() {
        return outputFolder;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public String getModelImplPackage() {
        return modelImplPackage;
    }

    public String getDataTypePackage() {
        return dataTypePackage;
    }

    public File getModelFolder() {
        return new File(outputFolder, modelPackage.replace(".", "/"));
    }

    public File getModelImplFolder() {
        return new File(outputFolder, modelImplPackage.replace(".", "/"));
    }

    public File getDataTypeFolder() {
        return new File(outputFolder, dataTypePackage.replace(".", "/"));
    }
}
