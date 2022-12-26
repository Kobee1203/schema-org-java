package com.weedow.schemaorg.serializer.deserialization;

import com.weedow.schemaorg.commons.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class PackageScanner {

    private static final Logger LOG = LoggerFactory.getLogger(PackageScanner.class);

    private static final String CLASS_EXTENSION = ".class";
    private static final int CLASS_EXTENSION_SIZE = CLASS_EXTENSION.length();

    private static final Comparator<Class<?>> CLASS_COMPARATOR = Comparator.comparing(Class::getName);

    private PackageScanner() {
    }

    public static Set<Class<?>> getClassesIn(String packageName) {
        return getClassesIn(packageName, (Class<?>) null);
    }

    public static Set<Class<?>> getClassesIn(String packageName, Class<?> isAssignableFrom) {
        Set<Class<?>> classes = getClassesIn(packageName, (String) null);

        if (isAssignableFrom == null) {
            return classes;
        } else {
            Set<Class<?>> filteredList = new LinkedHashSet<>();
            for (Class<?> clazz : classes) {
                if (isAssignableFrom.isAssignableFrom(clazz)) {
                    filteredList.add(clazz);
                }
            }

            return filteredList;
        }
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * It supports use of JAR files.
     *
     * @param packageName The base package
     * @param regexFilter an optional class name pattern.
     * @return The classes
     */
    public static Set<Class<?>> getClassesIn(String packageName, String regexFilter) {
        final Pattern regex = regexFilter != null ? Pattern.compile(regexFilter) : null;

        try {
            final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            final String path = packageName.replace('.', '/');

            Enumeration<URL> resources = classLoader.getResources(path);
            List<String> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(resolveDir(resource));
            }
            Set<Class<?>> classes = new TreeSet<>(CLASS_COMPARATOR);
            for (String directory : dirs) {
                classes.addAll(findClasses(directory, packageName, regex));
            }
            return classes;
        } catch (Exception e) {
            LOG.warn("Could not get classes from package {}: {}", packageName, e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    private static String resolveDir(URL resource) throws URISyntaxException {
        final String path = resource.toURI().getPath();
        return path != null ? path : resource.getFile();
    }

    /**
     * Recursive method used to find all classes in a given path (directory or zip file url). Directories are searched recursively.
     * It supports use of JAR files.
     *
     * @param path        The base directory or url from which to search.
     * @param packageName The package name for classes found inside the base directory
     * @param regex       an optional class name pattern.  e.g. .*Test
     * @return The classes
     * @throws IOException if an I/O exception occurs.
     */
    private static Set<Class<?>> findClasses(String path, String packageName, Pattern regex) throws IOException {
        Set<Class<?>> classes = new TreeSet<>(CLASS_COMPARATOR);

        findClassesInJar(classes, path, packageName, regex);

        File dir = new File(path);
        if (!dir.exists()) {
            return classes;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file.getAbsolutePath(), packageName + "." + file.getName(), regex));
                } else if (file.getName().endsWith(CLASS_EXTENSION)) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - CLASS_EXTENSION_SIZE);
                    if (regex == null || regex.matcher(className).matches())
                        classes.add(forName(className));
                }
            }
        }

        return classes;
    }

    /**
     * Check if the given path corresponds to a JAR file and read all jar entries to get classes belonging to the given package.
     * All found classes are added to the given Set of classes.
     *
     * @param classes     Set of classes to fill with the classes found in the package
     * @param path        PAth of the JAR file
     * @param packageName The package name for classes found inside the base directory
     * @param regex       an optional class name pattern.  e.g. .*Test
     * @throws IOException if an I/O exception occurs.
     */
    @SuppressWarnings("java:S5042") // The method doesn't extract files from ZIP, it reads all entries to get classes present in the given package
    private static void findClassesInJar(Set<Class<?>> classes, String path, String packageName, Pattern regex) throws IOException {
        if (path.startsWith("file:") && path.contains("!")) {
            String[] split = path.split("!");
            URL jar = new URL(split[0]);
            try (ZipInputStream zip = new ZipInputStream(jar.openStream())) {
                ZipEntry entry;
                while ((entry = zip.getNextEntry()) != null) {
                    if (entry.getName().endsWith(CLASS_EXTENSION)) {
                        String className = entry.getName()
                                .replaceAll("[$].*", "")
                                .replaceAll("[.]class", "")
                                .replace('/', '.');
                        if (className.startsWith(packageName) && (regex == null || regex.matcher(className).matches())) {
                            classes.add(forName(className));
                        }
                    }
                    zip.closeEntry();
                }
            }
        }
    }

    private static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOG.warn("Could not resolve class {}", className, e);
        }
        return null;
    }
}
