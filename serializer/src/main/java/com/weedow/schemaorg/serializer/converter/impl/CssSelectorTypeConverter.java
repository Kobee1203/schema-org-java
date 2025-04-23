package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S6548")
public class CssSelectorTypeConverter extends AbstractConverter {

    public static final CssSelectorTypeConverter INSTANCE = new CssSelectorTypeConverter();

    private CssSelectorTypeConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return String.class.isAssignableFrom(targetType) && jsonLdDataType.getSimpleName().equals("CssSelectorType");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
