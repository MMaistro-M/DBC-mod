/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind;

import com.relocated.fasterxml.jackson.databind.BeanDescription;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.JavaType;

public abstract class AbstractTypeResolver {
    public JavaType findTypeMapping(DeserializationConfig config, JavaType type) {
        return null;
    }

    @Deprecated
    public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
        return null;
    }

    public JavaType resolveAbstractType(DeserializationConfig config, BeanDescription typeDesc) {
        return null;
    }
}

