package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.databind.Module;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class JsonLdSerializerOptions {

    boolean prettyPrint;

    @Singular
    List<Module> modules;
}
