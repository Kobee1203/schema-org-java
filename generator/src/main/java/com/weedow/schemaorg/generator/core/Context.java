package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Type;

import java.util.Objects;
import java.util.Set;

public final class Context {

    private final Type type;
    private final String usedPackage;
    private final Set<String> imports;

    public Context(Type type, String usedPackage, Set<String> imports) {
        this.type = type;
        this.usedPackage = usedPackage;
        this.imports = imports;
    }

    public Type getType() {
        return type;
    }

    public String getUsedPackage() {
        return usedPackage;
    }

    public Set<String> getImports() {
        return imports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(type, context.type)
                && Objects.equals(usedPackage, context.usedPackage)
                && Objects.equals(imports, context.imports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, usedPackage, imports);
    }

    @Override
    public String toString() {
        return "Context{" + "type=" + type + ", usedPackage='" + usedPackage + '\'' + ", imports=" + imports + '}';
    }
}
