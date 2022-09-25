package com.weedow.schemaorg.generator.template.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

@SuppressWarnings("java:S115")
// Constant names should comply with a naming convention: we don't because The member's name become the name of the helper
public enum CharSequenceHelpers implements Helper<Object> {

    capitalizeWithUnderscore {
        @Override
        public CharSequence safeApply(Object value, Options options) {
            return camelCaseToUnderScoreUpperCase(value.toString());
        }
    };

    @Override
    public Object apply(final Object context, final Options options) throws IOException {
        if (options.isFalsy(context)) {
            Object param = options.param(0, null);
            return param == null ? null : param.toString();
        }
        return safeApply(context, options);
    }

    /**
     * Apply the helper to the context.
     *
     * @param context The context object (param=0).
     * @param options The options object.
     * @return A string result.
     */
    protected abstract CharSequence safeApply(Object context, Options options);

    public static String camelCaseToUnderScoreUpperCase(String camelCase) {
        StringBuilder sb = new StringBuilder();

        boolean prevUpperCase = false;
        boolean prevDigit = false;
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            final char nextC = nextCharacter(camelCase, i);
            if ((Character.isUpperCase(c))) {
                if (i > 0 && (!prevUpperCase || Character.isLowerCase(nextC))) {
                    sb.append("_");
                }
                sb.append(c);
                prevUpperCase = true;
            } else if (Character.isDigit(c)) {
                if (i > 0 && !prevDigit && !prevUpperCase) {
                    sb.append("_");
                }
                sb.append(c);
                prevDigit = true;
            } else {
                sb.append(Character.toUpperCase(c));
                prevUpperCase = false;
                // Special case to not add '_' with after a character in lower case that is between digits (eg. Nonprofit501c4 -> NONPROFIT_501C4)
                prevDigit = prevDigit && Character.isDigit(nextC);
            }
        }

        return sb.toString();
    }

    private static char nextCharacter(String camelCase, int i) {
        final int nextIndex = i + 1;
        return nextIndex < camelCase.length() ? camelCase.charAt(nextIndex) : Character.MIN_VALUE;
    }
}
