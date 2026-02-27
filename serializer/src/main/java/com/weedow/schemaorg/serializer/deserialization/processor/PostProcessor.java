package com.weedow.schemaorg.serializer.deserialization.processor;

/**
 * Interface for post-processing deserialized objects.
 */
public interface PostProcessor {

    /**
     * Processes a deserialized object.
     *
     * @param <T> the type of the object
     * @param obj the object to process
     * @return the processed object
     */
    <T> T process(T obj);
}
