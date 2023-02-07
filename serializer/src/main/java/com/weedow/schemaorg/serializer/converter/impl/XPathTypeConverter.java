package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class XPathTypeConverter extends AbstractConverter {

    public static final XPathTypeConverter INSTANCE = new XPathTypeConverter();

    private XPathTypeConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return String.class.isAssignableFrom(targetType) && jsonLdDataType.getSimpleName().equals("XPathType");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
