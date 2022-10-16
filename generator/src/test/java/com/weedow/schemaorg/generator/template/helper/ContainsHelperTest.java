package com.weedow.schemaorg.generator.template.helper;

import com.github.jknack.handlebars.Options;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

class ContainsHelperTest {

    @Test
    void contains_in_a_list() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply(List.of("value1", "value2"), options);

        verify(options).hash("yes", true);
    }

    @Test
    void does_not_contain_in_a_list() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value3");

        ContainsHelper.INSTANCE.apply(List.of("value1", "value2"), options);

        verify(options).hash("no", false);
    }

    @Test
    void contains_in_an_iterable() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply(new MyIterable(List.of("value1", "value2")), options);

        verify(options).hash("yes", true);
    }

    @Test
    void does_not_contain_in_an_iterable() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value3");

        ContainsHelper.INSTANCE.apply(new MyIterable(List.of("value1", "value2")), options);

        verify(options).hash("no", false);
    }

    @Test
    void contains_in_an_array() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply(new String[]{"value1", "value2"}, options);

        verify(options).hash("yes", true);
    }

    @Test
    void does_not_contain_in_an_array() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value3");

        ContainsHelper.INSTANCE.apply(new String[]{"value1", "value2"}, options);

        verify(options).hash("no", false);
    }

    @Test
    void contains_in_a_string() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply("This is my value1", options);

        verify(options).hash("yes", true);
    }

    @Test
    void does_not_contain_in_a_string() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply("This is my value", options);

        verify(options).hash("no", false);
    }

    @Test
    void does_not_contain_in_an_empty_list() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");

        ContainsHelper.INSTANCE.apply(Collections.emptyList(), options);

        verify(options).hash("no", false);
    }

    @Test
    void does_not_contain_in_null_value() throws IOException {
        Options options = mock(Options.class);
        when(options.param(0, null)).thenReturn("value1");
        when(options.isFalsy(null)).thenReturn(true);

        ContainsHelper.INSTANCE.apply(null, options);

        verify(options).hash("no", false);
    }

    public static class MyIterable implements Iterable<String> {

        private final List<String> values;

        public MyIterable(List<String> values) {
            this.values = values;
        }

        @Override
        public Iterator<String> iterator() {
            return values.iterator();
        }
    }
}