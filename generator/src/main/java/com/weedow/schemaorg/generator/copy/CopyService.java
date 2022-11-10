package com.weedow.schemaorg.generator.copy;

import java.nio.file.Path;

public interface CopyService {

    /**
     * Copy the decompiled content of the given {@code Class} to the file <i>'{ClassName}.java'</i> which is written to the {@code targetDirectory}.
     *
     * @param clazz           Class to copy
     * @param targetDirectory Target directory where the java class file is written
     */
    void copy(Class<?> clazz, Path targetDirectory);
}
