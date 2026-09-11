/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.classloader;

import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;
import io.github.somehussar.janinoloader.classloader.FilteredClassLoader;
import java.util.Map;

public class MemoryClassLoader
extends FilteredClassLoader {
    private final Map<String, byte[]> storedClasses;

    public MemoryClassLoader(ClassLoader parent, LoadClassCondition classFilter, Map<String, byte[]> storedClasses) {
        super(parent, classFilter);
        this.storedClasses = storedClasses;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = this.storedClasses.get(name.replaceAll("\\.", "/") + ".class");
        if (bytes != null) {
            return this.defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }
}

