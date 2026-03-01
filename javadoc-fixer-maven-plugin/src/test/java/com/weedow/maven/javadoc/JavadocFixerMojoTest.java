package com.weedow.maven.javadoc;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class JavadocFixerMojoTest {

    @TempDir
    Path tempDir;

    @ParameterizedTest
    @MethodSource("javadocFixArguments")
    void shouldFixJavadoc(String input, String expected) throws Exception {
        // Given
        Path testFile = tempDir.resolve("Test.java");
        Files.writeString(testFile, input, StandardCharsets.UTF_8);

        // When
        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", false);
        mojo.execute();

        // Then
        String result = Files.readString(testFile, StandardCharsets.UTF_8);
        assertThat(result).isEqualTo(expected);
    }

    private static java.util.stream.Stream<org.junit.jupiter.params.provider.Arguments> javadocFixArguments() {
        return java.util.stream.Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        // Constructor without javadoc
                        """
                                package com.example;
                                
                                public class Test {
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    public Test() {
                                    }
                                }
                                """,
                        """
                                package com.example;
                                
                                public class Test {
                                    /** Default constructor */
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    public Test() {
                                    }
                                }
                                """
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        // canEqual without javadoc
                        """
                                package com.example;
                                
                                public class Test {
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    protected boolean canEqual(final java.lang.Object other) {
                                        return other instanceof Test;
                                    }
                                }
                                """,
                        """
                                package com.example;
                                
                                public class Test {
                                    /**
                                     * canEqual method.
                                     * @param other Other.
                                     * @return true or false
                                     */
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    protected boolean canEqual(final java.lang.Object other) {
                                        return other instanceof Test;
                                    }
                                }
                                """
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        // Multiple fixes in same file
                        """
                                package com.example;
                                
                                public class Test {
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    public Test() {
                                    }
                                
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    protected boolean canEqual(final java.lang.Object other) {
                                        return other instanceof Test;
                                    }
                                }
                                """,
                        """
                                package com.example;
                                
                                public class Test {
                                    /** Default constructor */
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    public Test() {
                                    }
                                
                                    /**
                                     * canEqual method.
                                     * @param other Other.
                                     * @return true or false
                                     */
                                    @java.lang.SuppressWarnings("all")
                                    @lombok.Generated
                                    protected boolean canEqual(final java.lang.Object other) {
                                        return other instanceof Test;
                                    }
                                }
                                """
                )
        );
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        var field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
