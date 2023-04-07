package com.weedow.schemaorg.serializer.deserialization;

import com.weedow.schemaorg.serializer.SerializerException;

import java.util.List;

/**
 * Deserialize s String into an object of type {@code E}.
 *
 * @param <E> Type of the object resulting from the deserialized String
 */
public interface Deserializer<E> {

    /**
     * Deserializes a String into an Object {@code T}.
     *
     * @param data String to deserialize
     * @param <T>  Type of the returned object
     * @return an Object {@code T} resulting from the given deserialized String
     * @throws SerializerException if the String cannot be deserialized
     */
    <T extends E> T deserialize(String data) throws SerializerException;

    /**
     * Deserializes a String into a List of Objects {@code T}.
     *
     * @param data String to deserialize
     * @param <T>  Type of the returned object
     * @return a List of Objects {@code T} resulting from the given deserialized String
     * @throws SerializerException if the String cannot be deserialized
     */
    <T extends E> List<T> deserializeList(String data) throws SerializerException;

}