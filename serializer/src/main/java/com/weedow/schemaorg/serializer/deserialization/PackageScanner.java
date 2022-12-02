package com.weedow.schemaorg.serializer.deserialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class PackageScanner {

    private static final Logger LOG = LoggerFactory.getLogger(PackageScanner.class);

    private static final String CLASS_EXTENSION = ".class";
    private static final int CLASS_EXTENSION_SIZE = CLASS_EXTENSION.length();

    private PackageScanner() {
    }

    /**
     * Scans the given package for classes.
     *
     * @param packageName The package to scan.
     * @return the classes contained in the given package.
     */
    public static List<Class<?>> getClassesIn(String packageName) {
        return getDirs(packageName)
                .stream()
                .flatMap(d -> getClassesInDir(packageName, d).stream())
                .collect(Collectors.toList());
    }

    private static List<File> getDirs(String packageName) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        List<File> files = Collections.emptyList();
        try {
            files = Collections.list(cl.getResources(path))
                    .stream()
                    .map(r -> new File(getResourcePath(r)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.warn("Could not get resources in the package {}: {}", packageName, e.getMessage(), e);
        }
        return files;
    }

    private static String getResourcePath(URL r) {
        try {
            return r.toURI().getPath();
        } catch (URISyntaxException e) {
            throw new PackageScannerException(String.format("Could not resource path of %s", r), e);
        }
    }

    private static List<Class<?>> getClassesInDir(String packageName, File dir) {
        File[] files;

        if (!dir.exists() || (files = dir.listFiles()) == null) {
            LOG.info("{} does not exist.", dir);
            return Collections.emptyList();
        }

        return Arrays
                .stream(files)
                .filter(f -> f.getName().endsWith(CLASS_EXTENSION))
                .flatMap(f -> {
                    List<Class<?>> classes = !f.isDirectory() ? Collections.singletonList(fileToClass(packageName, f)) : Collections.emptyList();
                    return classes.stream();
                })
                .collect(Collectors.toList());
    }

    private static Class<?> fileToClass(String packageName, File file) {
        String className = file.getName().substring(0, file.getName().length() - CLASS_EXTENSION_SIZE);
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            throw new PackageScannerException(String.format("Could not resolve class %s, which was found in package %s", className, packageName), e);
        }
    }

    private static class PackageScannerException extends RuntimeException {
        public PackageScannerException(String msg, Exception e) {
            super(msg, e);
        }
    }
}
