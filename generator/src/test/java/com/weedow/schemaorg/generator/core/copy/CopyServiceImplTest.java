package com.weedow.schemaorg.generator.core.copy;

import nl.altindag.log.LogCaptor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class CopyServiceImplTest {

    @Test
    void copy() {
        try (LogCaptor logCaptor = LogCaptor.forClass(CopyServiceImpl.class)) {
            final CopyService copyService = new CopyServiceImpl();

            final Path targetDirectory = Paths.get("target/test");
            try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
                copyService.copy(CopyServiceImplTest.class, targetDirectory);

                Assertions.assertThat(logCaptor.getInfoLogs()).containsExactly("Copying 'com.weedow.schemaorg.generator.core.copy.CopyServiceImplTest'");
                Assertions.assertThat(logCaptor.getWarnLogs()).isEmpty();
                Assertions.assertThat(logCaptor.getErrorLogs()).isEmpty();
                files.verify(() -> Files.createDirectories(targetDirectory));
                files.verify(() -> Files.copy(any(InputStream.class), eq(Paths.get("target/test/CopyServiceImplTest.java")), eq(StandardCopyOption.REPLACE_EXISTING)));
            }
        }
    }

    @Test
    void throw_exception_while_copying() {
        try (LogCaptor logCaptor = LogCaptor.forClass(CopyServiceImpl.class)) {
            final CopyService copyService = new CopyServiceImpl();

            final Path targetDirectory = Paths.get("target/test");
            try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
                files.when(() -> Files.createDirectories(targetDirectory)).thenThrow(new IOException("Could not create the directory"));

                copyService.copy(CopyServiceImplTest.class, targetDirectory);

                Assertions.assertThat(logCaptor.getInfoLogs()).containsExactly("Copying 'com.weedow.schemaorg.generator.core.copy.CopyServiceImplTest'");
                Assertions.assertThat(logCaptor.getWarnLogs()).containsExactly("Could not read the resource 'CopyServiceImplTest.java': Could not create the directory");
                Assertions.assertThat(logCaptor.getErrorLogs()).isEmpty();
                files.verify(
                        () -> Files.copy(any(InputStream.class), eq(Paths.get("target/test/CopyServiceImplTest.java")), eq(StandardCopyOption.REPLACE_EXISTING)),
                        Mockito.never()
                );
            }
        }
    }
}