package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Type;
import lombok.Value;

import java.util.Set;

@Value
public class Context {

    Type type;
    String usedPackage;
    Set<String> imports;
}
