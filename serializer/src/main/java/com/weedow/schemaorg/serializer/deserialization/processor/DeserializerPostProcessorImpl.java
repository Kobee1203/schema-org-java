package com.weedow.schemaorg.serializer.deserialization.processor;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.converter.ConversionService;
import com.weedow.schemaorg.serializer.converter.ConversionServiceImpl;
import com.weedow.schemaorg.serializer.deserialization.spec.DataTypeSpecificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        fixObjectFieldValues(obj);
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
            if (JsonLdNode.class.isAssignableFrom(fieldValue.getClass()) && !Enum.class.isAssignableFrom(fieldValue.getClass())) {
                fixObjectFieldValues(fieldValue);
            } else if (field.getType() == Object.class) {
                updateObjectFieldValue(obj, field, fieldValue);
            }
        }
    }

    private void updateObjectFieldValue(Object obj, Field field, Object fieldValue) {
        JsonLdFieldTypes jsonLdFieldTypes = field.getDeclaredAnnotation(JsonLdFieldTypes.class);
        if (jsonLdFieldTypes != null) {
            List<Class<? extends JsonLdDataType<?>>> fieldTypes = filterAndSort(jsonLdFieldTypes.value(), fieldValue);
            if (!fieldTypes.isEmpty()) {
                for (Class<? extends JsonLdDataType<?>> fieldType : fieldTypes) {
                    JsonLdDataType<?> value = conversionService.convert(fieldValue, fieldType);

                    if (value != null) {
                        setFieldValue(obj, field, fieldType, value);
                        break;
                    }
                }
            }
        }
    }

    private static List<Class<? extends JsonLdDataType<?>>> filterAndSort(Class<?>[] fieldTypes, Object fieldValue) {
        if (isScalarValue(fieldValue)) {
            return Arrays.stream(fieldTypes)
                    .filter(JsonLdDataType.class::isAssignableFrom)
                    .map(clazz -> (Class<JsonLdDataType<?>>) clazz)
                    .sorted(DataTypeSpecificationService.getInstance().getDataTypeComparator())
                    .collect(Collectors.toList());
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
    private static void setFieldValue(Object obj, Field field, Class<? extends JsonLdDataType<?>> fieldType, JsonLdDataType<?> value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            LOG.warn("Could not instantiate {}", fieldType);
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
