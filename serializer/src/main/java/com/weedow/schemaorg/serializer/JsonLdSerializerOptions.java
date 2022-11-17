package com.weedow.schemaorg.serializer;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JsonLdSerializerOptions {

    boolean prettyPrint;
}
