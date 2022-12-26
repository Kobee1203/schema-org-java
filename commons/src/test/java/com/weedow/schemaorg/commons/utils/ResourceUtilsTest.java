package com.weedow.schemaorg.commons.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URL;

class ResourceUtilsTest {

    @Test
    void getURL_with_classpath_prefix() throws FileNotFoundException {
        URL url = ResourceUtils.getURL("classpath:file.txt");
        Assertions.assertThat(url).isNotNull();
        Assertions.assertThat(url.getProtocol()).isEqualTo("file");
    }

    @Test
    void getURL_with_classpath_prefix_when_file_is_not_found() {
        Assertions.assertThatThrownBy(() -> ResourceUtils.getURL("classpath:file-not-found.txt"))
                .isInstanceOf(FileNotFoundException.class)
                .hasMessage("class path resource [file-not-found.txt] cannot be resolved to URL because it does not exist");
    }

    @Test
    void getURL() throws FileNotFoundException {
        URL url = ResourceUtils.getURL("https://schema.org/docs/favicon.ico");
        Assertions.assertThat(url).isNotNull();
        Assertions.assertThat(url.getProtocol()).isEqualTo("https");
        Assertions.assertThat(url.getHost()).isEqualTo("schema.org");
        Assertions.assertThat(url.getAuthority()).isEqualTo("schema.org");
        Assertions.assertThat(url.getDefaultPort()).isEqualTo(443);
        Assertions.assertThat(url.getPort()).isEqualTo(-1);
        Assertions.assertThat(url.getFile()).isEqualTo("/docs/favicon.ico");
        Assertions.assertThat(url.getPath()).isEqualTo("/docs/favicon.ico");
    }

    @Test
    void getURL_as_file_path() throws FileNotFoundException {
        URL url = ResourceUtils.getURL("C:/schema.org/docs/favicon.ico");
        Assertions.assertThat(url).isNotNull();
        Assertions.assertThat(url.getProtocol()).isEqualTo("file");
        // Windows and linux have not the same behavior:
        // - Windows is /C:/schema.org/docs/favicon.ico
        // - Linux: path may be /home/runner/work/schema-org-java/schema-org-java/commons/C:/schema.org/docs/favicon.ico
        Assertions.assertThat(url.getFile()).endsWith("/C:/schema.org/docs/favicon.ico");
        Assertions.assertThat(url.getPath()).endsWith("/C:/schema.org/docs/favicon.ico");
    }
}