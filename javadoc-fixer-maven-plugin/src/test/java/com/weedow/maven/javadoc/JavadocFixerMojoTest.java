package com.weedow.maven.javadoc;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JavadocFixerMojoTest {

    @TempDir
    Path tempDir;

    // -------------------------------------------------------------------------
    // Tests for execute() - uncovered branches
    // -------------------------------------------------------------------------

    @Test
    void shouldSkipExecutionWhenSkipIsTrue() throws Exception {
        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", true);

        // Should complete without error and without processing files
        mojo.execute();

        // No files should have been touched
        assertThat(Files.list(tempDir).count()).isZero();
    }

    @Test
    void shouldWarnWhenSourceDirectoryDoesNotExist() throws Exception {
        Path testFile = tempDir.resolve("Foo.java");
        Files.writeString(testFile, "class Foo {}", StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", new java.io.File("/non/existent/path"));
        setField(mojo, "skip", false);
        mojo.execute();

        // No files in tempDir should have been modified
        assertThat(Files.readString(testFile, StandardCharsets.UTF_8)).isEqualTo("class Foo {}");
    }

    @Test
    void shouldWarnWhenSourceDirectoryIsAFile() throws Exception {
        Path file = tempDir.resolve("notADirectory.java");
        String originalContent = "class NotADirectory {}";
        Files.writeString(file, originalContent, StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", file.toFile());
        setField(mojo, "skip", false);
        mojo.execute();

        // The file should not have been modified
        assertThat(Files.readString(file, StandardCharsets.UTF_8)).isEqualTo(originalContent);
    }

    @Test
    void shouldThrowMojoExecutionExceptionOnIOError() throws Exception {
        // Use a directory that cannot be walked (deleted after the mojo starts)
        // We simulate this by passing a path that exists but trick the walk — instead,
        // we pass sourceDirectory pointing to a file that is actually not a directory.
        // A cleaner approach: subclass and override findJavaFiles to throw.
        // Since findJavaFiles is package-private we can test via a subclass.
        JavadocFixerMojo mojo = new JavadocFixerMojo() {
            @Override
            List<Path> findJavaFiles(Path directory) throws java.io.IOException {
                throw new java.io.IOException("Simulated IO error");
            }
        };
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", false);

        assertThatThrownBy(mojo::execute)
                .isInstanceOf(MojoExecutionException.class)
                .hasMessageContaining("Error processing Java files");
    }

    // -------------------------------------------------------------------------
    // Tests for processFile() - debug log branch (fix > 0)
    // -------------------------------------------------------------------------

    @Test
    void shouldLogDebugWhenFixesAreApplied() throws Exception {
        // This test ensures the debug branch (fixes applied → writeString) is covered
        Path testFile = tempDir.resolve("Debug.java");
        String input = """
                package com.example;
                
                public class Debug {
                    @java.lang.SuppressWarnings("all")
                    @lombok.Generated
                    public Debug() {
                    }
                }
                """;
        Files.writeString(testFile, input, StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", false);
        mojo.execute();

        String result = Files.readString(testFile, StandardCharsets.UTF_8);
        assertThat(result).contains("/** Default constructor */");
    }

    @Test
    void shouldNotModifyFileWhenNoFixNeeded() throws Exception {
        Path testFile = tempDir.resolve("NoFix.java");
        String input = """
                package com.example;
                
                public class NoFix {
                    /** Already documented */
                    public NoFix() {
                    }
                }
                """;
        Files.writeString(testFile, input, StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", false);
        mojo.execute();

        assertThat(Files.readString(testFile, StandardCharsets.UTF_8)).isEqualTo(input);
    }

    // -------------------------------------------------------------------------
    // Parameterized tests for Javadoc fix scenarios
    // -------------------------------------------------------------------------

    @ParameterizedTest
    @MethodSource("javadocFixArguments")
    void shouldFixJavadoc(String input, String expected) throws Exception {
        Path testFile = tempDir.resolve("Test.java");
        Files.writeString(testFile, input, StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        setField(mojo, "sourceDirectory", tempDir.toFile());
        setField(mojo, "skip", false);
        mojo.execute();

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

    // -------------------------------------------------------------------------
    // Tests for findJavaFiles()
    // -------------------------------------------------------------------------

    @Test
    void shouldFindOnlyJavaFiles() throws Exception {
        Files.writeString(tempDir.resolve("Foo.java"), "class Foo {}", StandardCharsets.UTF_8);
        Files.writeString(tempDir.resolve("Bar.java"), "class Bar {}", StandardCharsets.UTF_8);
        Files.writeString(tempDir.resolve("readme.txt"), "ignored", StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        List<Path> result = mojo.findJavaFiles(tempDir);

        assertThat(result).hasSize(2)
                .allMatch(p -> p.toString().endsWith(".java"));
    }

    @Test
    void shouldFindJavaFilesRecursively() throws Exception {
        Path sub = Files.createDirectory(tempDir.resolve("sub"));
        Files.writeString(tempDir.resolve("Root.java"), "class Root {}", StandardCharsets.UTF_8);
        Files.writeString(sub.resolve("Child.java"), "class Child {}", StandardCharsets.UTF_8);

        JavadocFixerMojo mojo = new JavadocFixerMojo();
        List<Path> result = mojo.findJavaFiles(tempDir);

        assertThat(result).hasSize(2);
    }

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Class<?> clazz = target.getClass();
        while (clazz != null) {
            try {
                var field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(target, value);
                return;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy of " + target.getClass().getName());
    }
}
