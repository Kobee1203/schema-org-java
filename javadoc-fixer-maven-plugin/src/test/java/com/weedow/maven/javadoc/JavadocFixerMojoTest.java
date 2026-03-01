package com.weedow.maven.javadoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class JavadocFixerMojoTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldFixConstructorsWithoutJavadoc() throws Exception {
        // Given
        String input = """
                package com.example;
                
                public class Test {
                    @java.lang.SuppressWarnings("all")
                    @lombok.Generated
                    public Test() {
                    }
                }
                """;

        String expected = """
                package com.example;
                
                public class Test {
                    /** Default constructor */
                @java.lang.SuppressWarnings("all")
                    @lombok.Generated
                    public Test() {
                    }
                }
                """;

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

    @Test
    void shouldFixCanEqualWithoutJavadoc() throws Exception {
        // Given
        String input = """
                package com.example;
                
                public class Test {
                    @java.lang.SuppressWarnings("all")
                    @lombok.Generated
                    protected boolean canEqual(final java.lang.Object other) {
                        return other instanceof Test;
                    }
                }
                """;

        String expected = """
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
                """;

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

    @Test
    void shouldHandleMultipleFixesInSameFile() throws Exception {
        // Given
        String input = """
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
                """;

        String expected = """
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
                """;

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

    private void setField(Object target, String fieldName, Object value) throws Exception {
        var field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
