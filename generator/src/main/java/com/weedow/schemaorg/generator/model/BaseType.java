package com.weedow.schemaorg.generator.model;

import lombok.Builder;
import lombok.Value;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a base type definition with Java interface and implementation classes.
 * Extracts method information from the interface class for code generation.
 */
@Value
public class BaseType {
    /**
     * The type identifier.
     *
     * @return the type identifier
     */
    String id;

    /**
     * The Java interface class.
     *
     * @return the Java interface class
     */
    Class<?> interfaceClass;

    /**
     * The Java implementation class.
     *
     * @return the Java implementation class
     */
    Class<?> implementationClass;

    /**
     * The set of method information extracted from the interface.
     *
     * @return the set of method information
     */
    Set<MethodInfo> methods;

    /**
     * Creates a new BaseType with the specified classes.
     *
     * @param id the type identifier
     * @param interfaceClass the Java interface class
     * @param implementationClass the Java implementation class (may be null)
     */
    public BaseType(String id, Class<?> interfaceClass, Class<?> implementationClass) {
        this.id = id;
        this.interfaceClass = interfaceClass;
        this.implementationClass = implementationClass;
        this.methods = getMethodInfos(interfaceClass);
    }

    /**
     * Extracts method information from a class.
     *
     * @param clazz the class to extract methods from
     * @return set of method information objects
     */
    private static Set<MethodInfo> getMethodInfos(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).map(m ->
                        MethodInfo.builder()
                                .name(m.getName())
                                .returnType(m.getReturnType().getName())
                                .modifiers(Modifier.toString(m.getModifiers()).replace("abstract", ""))
                                .parameters(
                                        Arrays.stream(m.getParameters())
                                                .map(param ->
                                                        MethodInfo.ParameterInfo.builder()
                                                                .name(param.getName())
                                                                .type(param.getType().getName())
                                                                .build())
                                                .toList()
                                )
                                .exceptions(
                                        Arrays.stream(m.getExceptionTypes())
                                                .map(Class::getName)
                                                .toList()
                                )
                                .build()
                )
                .collect(Collectors.toSet());
    }

    /**
     * Gets the fully qualified name of the interface class.
     *
     * @return the interface class name
     */
    public String getInterfaceClassName() {
        return interfaceClass.getName();
    }

    /**
     * Gets the fully qualified name of the implementation class.
     *
     * @return the implementation class name
     */
    public String getImplementationClassName() {
        return implementationClass.getName();
    }

    /**
     * Class with method information.
     */
    @Value
    @Builder
    public static class MethodInfo {
        /**
         * Method name
         *
         * @return The method name
         * @param name The method name
         */
        String name;
        /**
         * Return type of the method
         *
         * @return The method return type
         * @param returnType The method return type
         */
        String returnType;
        /**
         * Access modifier
         *
         * @return The access modifier
         * @param modifiers The access modifier
         */
        String modifiers;
        /**
         * Method parameters
         *
         * @return The method parameters
         * @param parameters The method parameters
         */
        List<ParameterInfo> parameters;
        /**
         * Exceptions thrown by the method
         *
         * @return the exceptions thrown by the method
         * @param exceptions the exceptions thrown by the method
         */
        List<String> exceptions;

        public String getParametersAsString() {
            return parameters.stream()
                    .map(param -> param.getType() + " " + param.getName())
                    .collect(Collectors.joining(", ", "", ""));
        }

        public String getExceptionsAsString() {
            return !exceptions.isEmpty() ? exceptions.stream().collect(Collectors.joining(", ", " throws ", "")) : "";
        }

        public boolean isVoidReturnType() {
            return "void".equals(returnType);
        }

        /**
         * Parameter information.
         */
        @Value
        @Builder
        private static class ParameterInfo {
            /**
             * Parameter name
             *
             * @return The parameter name
             * @param name The parameter name
             */
            String name;
            /**
             * Parameter type
             *
             * @return The parameter type
             * @param type The parameter type
             */
            String type;
        }
    }
}
