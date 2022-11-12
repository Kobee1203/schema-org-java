package com.weedow.schemaorg.generator.core.copy;

import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class CopyServiceImpl implements CopyService {

    private static final Logger LOG = LoggerFactory.getLogger(CopyServiceImpl.class);

    /**
     * {@inheritDoc}
     * <p>Thsi implementation copies the Java file present in the same package as the given Class.</p>
     *
     * @param clazz           Class to copy
     * @param targetDirectory Target directory where the java class file is written
     */
    @Override
    public void copy(Class<?> clazz, Path targetDirectory) {
        LOG.info("Copying '{}'", clazz.getName());

        String resource = clazz.getSimpleName() + ".java";
        try (InputStream in = clazz.getResourceAsStream(resource)) {
            final Path targetFile = targetDirectory.resolve(resource);

            Files.createDirectories(targetDirectory);
            Files.copy(Objects.requireNonNull(in), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            LOG.warn("Could not read the resource '" + resource + "': " + e.getMessage(), e);
        }
    }
}
