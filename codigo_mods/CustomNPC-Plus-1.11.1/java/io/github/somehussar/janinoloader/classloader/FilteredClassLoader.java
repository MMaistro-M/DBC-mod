/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.classloader;

import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;

public class FilteredClassLoader
extends ClassLoader {
    private final LoadClassCondition classFilter;

    FilteredClassLoader(ClassLoader parent, LoadClassCondition classFilter) {
        super(parent);
        this.classFilter = classFilter;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (this.classFilter != null && !this.classFilter.isValid(name)) {
            throw new ClassNotFoundException(this.classFilter.classNotLoadedMessage(name));
        }
        return super.loadClass(name, resolve);
    }
}

