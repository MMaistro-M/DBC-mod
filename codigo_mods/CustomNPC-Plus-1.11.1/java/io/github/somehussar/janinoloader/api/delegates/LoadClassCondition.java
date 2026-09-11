/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.api.delegates;

public interface LoadClassCondition {
    public boolean isValid(String var1);

    default public String classNotLoadedMessage(String name) {
        return name;
    }
}

