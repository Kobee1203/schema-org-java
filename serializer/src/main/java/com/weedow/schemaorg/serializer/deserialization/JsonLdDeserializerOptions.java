package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.Module;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class JsonLdDeserializerOptions {

    @Singular
    List<Module> modules;
}
