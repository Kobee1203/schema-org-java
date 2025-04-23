package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S6548")
public class BooleanConverter extends AbstractConverter {

    public static final String FALSE = "false";
    public static final String TRUE = "true";

    public static final BooleanConverter INSTANCE = new BooleanConverter();

    private BooleanConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        // Boolean.class == SerializerUtils.getJavaType(targetType)
        return (Boolean.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("Boolean");
    }

    @Override
    public Boolean getValue(Object source) {
        if (source instanceof Boolean bool) {
            return bool;
        } else {
            return toBooleanObject(source.toString());
        }
    }

    /**
     * Converts a String to a Boolean.
     * This method may return {@code null} in order to call the next converter when the string is not a boolean.
     *
     * @param str the String to check; upper and lower case are treated as the same
     * @return the Boolean value of the string, {@code null} if no match or {@code null} input
     * @see com.weedow.schemaorg.serializer.deserialization.processor.DeserializerPostProcessorImpl
     */
    @SuppressWarnings("java:S2447") // Null should not returned from a "Boolean" method: Reviewed
    private static Boolean toBooleanObject(final String str) {
        if (TRUE.equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        }
        if (FALSE.equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        return null;
    }
}
