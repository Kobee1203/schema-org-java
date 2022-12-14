package com.weedow.schemaorg.serializer.deserialization.processor;

public interface PostProcessor {

    <T> T process(T obj);
}
