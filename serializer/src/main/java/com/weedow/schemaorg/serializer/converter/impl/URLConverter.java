package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("java:S6548")
public class URLConverter extends AbstractConverter {

    private static final Logger LOG = LoggerFactory.getLogger(URLConverter.class);

    public static final URLConverter INSTANCE = new URLConverter();

    private URLConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return (URL.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("URL");
    }

    @Override
    public URL getValue(Object source) {
        try {
            return source instanceof URL url ? url : new URL(source.toString());
        } catch (MalformedURLException e) {
            LOG.warn("Could not create URL from {}", source);
            return null;
        }
    }
}
