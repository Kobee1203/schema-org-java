package com.weedow.schemaorg.serializer;

/**
 * Serialize an object of type {@code T} to a String.
 *
 * @param <T> the type of object to be serialized
 */
public interface Serializer<T> {

    /**
     * Returns the media type representing the content type returned by {@link #serialize(Object)}.
     *
     * @return String representing the media type (eg. application/json)
     */
    String getMediaType();

    /**
     * Serializes an object to a {@link String}.
     *
     * @param object Object to serialize
     * @return A String representing the given serialized object
     */
    String serialize(T object) throws SerializerException;

}