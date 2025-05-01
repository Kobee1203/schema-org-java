package com.weedow.schemaorg.serializer.deserialization.processor;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.converter.ConversionService;
import com.weedow.schemaorg.serializer.converter.ConversionServiceImpl;
import com.weedow.schemaorg.serializer.spec.DataTypeSpecificationService;
import com.weedow.schemaorg.serializer.utils.SerializerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

public class DeserializerPostProcessorImpl implements PostProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DeserializerPostProcessorImpl.class);

    private static final List<Class<?>> SCALAR_TYPES = Arrays.asList(
            Boolean.class,
            String.class,
            Number.class
    );

    private final ConversionService conversionService = new ConversionServiceImpl();

    @Override
    public <T> T process(T obj) {
        if(obj instanceof Collection<?> coll) {
            for(Object o : coll) {
                process(o);
            }
        } else {
            fixObjectFieldValues(obj);
        }
        return obj;
    }

    private void fixObjectFieldValues(Object obj) {
        Class<?> clazz = obj.getClass();
        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                fixObjectFieldValue(obj, field);
            }
            clazz = clazz.getSuperclass();
        }
    }

    private void fixObjectFieldValue(Object obj, Field field) {
        Object fieldValue = getFieldValue(obj, field);
        if (fieldValue != null) {
            Class<?> fieldValueClass = fieldValue.getClass();
            if (JsonLdNode.class.isAssignableFrom(fieldValueClass) && !Enum.class.isAssignableFrom(fieldValueClass)) {
                fixObjectFieldValues(fieldValue);
            } else if (field.getType() == Object.class) {
                updateObjectFieldValue(field, fieldValue).ifPresent(o -> setFieldValue(obj, field, o));
            } else if (field.getType() == List.class) {
                fixListFieldValue(field, (List<?>) fieldValue);
            }
        }
    }

    private void fixListFieldValue(Field field, List<?> list) {
        Class<?> genericType = (Class<?>) SerializerUtils.getTypeParameter(field.getGenericType());
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i);
            Class<?> valueClass = value.getClass();
            if (JsonLdNode.class.isAssignableFrom(valueClass) && !Enum.class.isAssignableFrom(valueClass)) {
                fixObjectFieldValues(value);
            } else if (genericType == Object.class) {
                final int index = i;
                // noinspection unchecked - required to cast as List<Object>
                updateObjectFieldValue(field, value).ifPresent(o -> ((List<Object>) list).set(index, o));
            }
        }
    }

    private Optional<?> updateObjectFieldValue(Field field, Object fieldValue) {
        Optional<?> opt = Optional.empty();

        JsonLdFieldTypes jsonLdFieldTypes = field.getDeclaredAnnotation(JsonLdFieldTypes.class);
        if (jsonLdFieldTypes != null) {
            List<Class<JsonLdDataType<?>>> fieldTypes = filterAndSort(jsonLdFieldTypes.value(), fieldValue);
            if (!fieldTypes.isEmpty()) {
                for (Class<? extends JsonLdDataType<?>> fieldType : fieldTypes) {
                    JsonLdDataType<?> value = conversionService.convert(fieldValue, fieldType);

                    if (value != null) {
                        opt = Optional.of(value);
                        break;
                    }
                }
            }
        }

        return opt;
    }

    @SuppressWarnings("unchecked")
    private static List<Class<JsonLdDataType<?>>> filterAndSort(Class<?>[] fieldTypes, Object fieldValue) {
        if (isScalarValue(fieldValue)) {
            return Arrays.stream(fieldTypes)
                    .filter(JsonLdDataType.class::isAssignableFrom)
                    .map(clazz -> (Class<JsonLdDataType<?>>) clazz)
                    .sorted(DataTypeSpecificationService.getInstance().getDataTypeComparator())
                    .toList();
        }
        return Collections.emptyList();
    }

    private static boolean isScalarValue(Object fieldValue) {
        if (fieldValue == null) {
            return false;
        }
        Class<?> fieldValueClass = fieldValue.getClass();
        return SCALAR_TYPES.stream().anyMatch(clazz -> clazz.isAssignableFrom(fieldValueClass));
    }

    @SuppressWarnings("java:S3011")
    private static void setFieldValue(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOG.warn("Could not set value {} to '{}': {}", value, field.getName(), e.getMessage());
        }
    }

    @SuppressWarnings("java:S3011")
    private static Object getFieldValue(Object obj, Field field) {
        Object value;
        try {
            field.setAccessible(true);
            value = field.get(obj);
        } catch (IllegalAccessException e) {
            LOG.warn("Could not access to value of field {}: {}", field.getName(), e.getMessage());
            value = null;
        }
        return value;
    }
}
