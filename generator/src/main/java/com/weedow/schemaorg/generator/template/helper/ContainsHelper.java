package com.weedow.schemaorg.generator.template.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;

import java.io.IOException;
import java.util.Collection;

/**
 * <p>Helper to check if any value is included in a Collection, an Iterable, an array or a String.</p>
 * <p>Example:</p>
 * <pre>
 * {{#contains list "value"}} ... {{/contains}}
 * {{#contains iterable "value"}} ... {{/contains}}
 * {{#contains array "value"}} ... {{/contains}}
 * {{#contains string "value"}} ... {{/contains}}
 * </pre>
 */
public class ContainsHelper implements Helper<Object> {

    /**
     * A singleton instance of this helper.
     */
    public static final Helper<Object> INSTANCE = new ContainsHelper();

    /**
     * The helper's name.
     */
    public static final String NAME = "contains";

    @Override
    public Object apply(final Object context, final Options options) throws IOException {
        Object value = options.param(0, null);

        boolean result = false;
        if (!options.isFalsy(context)) {
            if (context instanceof Collection) {
                result = ((Collection<?>) context).contains(value);
            } else if (context instanceof Iterable) {
                result = contains((Iterable<?>) context, value);
            } else if (context.getClass().isArray()) {
                result = contains((Object[]) context, value);
            } else if (context instanceof String) {
                result = ((String) context).contains(value.toString());
            }
        }

        if (options.tagType == TagType.SECTION) {
            return result ? options.fn() : options.inverse();
        }
        return result
                ? options.hash("yes", true)
                : options.hash("no", false);
    }

    private static boolean contains(Iterable<?> context, Object value) {
        for (Object o : context) {
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(Object[] context, Object value) {
        for (Object o : context) {
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
