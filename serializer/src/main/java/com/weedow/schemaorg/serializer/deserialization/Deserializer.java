package com.weedow.schemaorg.serializer.deserialization;

import com.weedow.schemaorg.serializer.SerializerException;

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

}