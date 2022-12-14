package com.weedow.schemaorg.serializer.deserialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public final class PackageScanner {

    private static final Logger LOG = LoggerFactory.getLogger(PackageScanner.class);

    private static final String CLASS_EXTENSION = ".class";

    private PackageScanner() {
    }

    /**
     * Scans the given package for classes.
     *
     * @param packageName The package to scan.
     * @return the classes contained in the given package.
     */
    public static Set<Class<?>> getClassesIn(String packageName) {
        Set<Class<?>> classes = Collections.emptySet();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = packageName.replace(".", "/");
        try (InputStream stream = classLoader.getResourceAsStream(path)) {
            if (stream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                    classes = reader.lines()
                            .filter(line -> line.endsWith(CLASS_EXTENSION))
                            .map(className -> getClass(className, packageName))
                            .collect(Collectors.toSet());
                }
            }
        } catch (Exception e) {
            LOG.warn("Could not read package content: {}", e.getMessage(), e);
        }

        return classes;
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            LOG.warn("Could not resolve class {}, which was found in package {}", className, packageName, e);
        }
        return null;
    }
}
