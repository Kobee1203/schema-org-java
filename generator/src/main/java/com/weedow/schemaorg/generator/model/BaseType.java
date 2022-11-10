package com.weedow.schemaorg.generator.model;

import lombok.Builder;
import lombok.Value;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class BaseType {
    String id;

    Class<?> interfaceClass;

    Class<?> implementationClass;

    Set<MethodInfo> methods;

    public BaseType(String id, Class<?> interfaceClass, Class<?> implementationClass) {
        this.id = id;
        this.interfaceClass = interfaceClass;
        this.implementationClass = implementationClass;
        this.methods = getMethodInfos(interfaceClass);
    }

    public static Set<MethodInfo> getMethodInfos(Class<?> clazz) {
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
                                                        .collect(Collectors.toList())
                                        )
                                        .exceptions(
                                                Arrays.stream(m.getExceptionTypes())
                                                        .map(Class::getName)
                                                        .collect(Collectors.toList())
                                        )
                                        .build()
                )
                .collect(Collectors.toSet());
    }

    public String getInterfaceClassName() {
        return interfaceClass.getName();
    }

    public String getImplementationClassName() {
        return implementationClass.getName();
    }

    @Value
    @Builder
    private static class MethodInfo {
        String name;
        String returnType;
        String modifiers;
        List<ParameterInfo> parameters;
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

        @Value
        @Builder
        private static class ParameterInfo {
            String name;
            String type;
        }
    }
}
