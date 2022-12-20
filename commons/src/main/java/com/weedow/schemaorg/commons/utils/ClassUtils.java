package com.weedow.schemaorg.commons.utils;

public final class ClassUtils {

    private ClassUtils() {
    }


    /**
     * Return the default ClassLoader to use:
     * typically the thread context ClassLoader, if available; the ClassLoader that loaded the ClassUtils class will be used as fallback.
     * <p>
     * Call this method if you intend to use the thread context ClassLoader in a scenario where you clearly prefer a non-null ClassLoader reference:
     * for example, for class path resource loading (but not necessarily for {@code Class.forName}, which accepts a {@code null} ClassLoader reference as well).
     *
     * @return the default ClassLoader (only {@code null} if even the system ClassLoader isn't accessible)
     * @see Thread#getContextClassLoader()
     * @see ClassLoader#getSystemClassLoader()
     * @see "org.springframework.util.ClassUtils.getDefaultClassLoader()"
     */
    @SuppressWarnings("java:S1181")
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        // getClassLoader() returning null indicates the bootstrap ClassLoader -> access system ClassLoader
        return cl != null ? cl : ClassLoader.getSystemClassLoader();
    }
}
