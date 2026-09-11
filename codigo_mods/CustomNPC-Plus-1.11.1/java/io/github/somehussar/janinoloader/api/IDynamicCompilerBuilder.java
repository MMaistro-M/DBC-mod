/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.api;

import io.github.somehussar.janinoloader.JaninoCompilerBuilder;
import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;

public interface IDynamicCompilerBuilder {
    public static IDynamicCompilerBuilder createBuilder() {
        return new JaninoCompilerBuilder();
    }

    public IDynamicCompilerBuilder setParentClassLoader(ClassLoader var1);

    public IDynamicCompilerBuilder setClassFilter(LoadClassCondition var1);

    public IDynamicCompiler getCompiler();
}

