/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader;

import io.github.somehussar.janinoloader.JaninoCompiler;
import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.IDynamicCompilerBuilder;
import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;

public class JaninoCompilerBuilder
implements IDynamicCompilerBuilder {
    private ClassLoader parent = this.getClass().getClassLoader();
    private LoadClassCondition classFilter;

    @Override
    public IDynamicCompilerBuilder setParentClassLoader(ClassLoader classLoader) {
        this.parent = classLoader;
        return this;
    }

    @Override
    public IDynamicCompilerBuilder setClassFilter(LoadClassCondition filter) {
        this.classFilter = filter;
        return this;
    }

    @Override
    public IDynamicCompiler getCompiler() {
        return new JaninoCompiler(this.parent, this.classFilter);
    }
}

