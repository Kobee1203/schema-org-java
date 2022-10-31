package com.jparams.verifier.tostring;

import java.util.List;

/**
 * Scans package and identifies classes
 */
@SuppressWarnings("ALL")
final class PackageScanner {
    private PackageScanner() {
    }

    /**
     * Scan for all classes in the given package
     *
     * @param packageName package to scan
     * @param recursively true to recursively scan all sub packages
     * @return classes
     */
    public static List<Class<?>> findClasses(final String packageName, final boolean recursively) {
        return nl.jqno.equalsverifier.internal.reflection.PackageScanner.getClassesIn(packageName, recursively);
    }
}