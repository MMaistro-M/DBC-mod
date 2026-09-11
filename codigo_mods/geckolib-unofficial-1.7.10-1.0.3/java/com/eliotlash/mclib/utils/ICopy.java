/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.utils;

public interface ICopy<T> {
    public T copy();

    default public void copy(T origin) {
    }
}

