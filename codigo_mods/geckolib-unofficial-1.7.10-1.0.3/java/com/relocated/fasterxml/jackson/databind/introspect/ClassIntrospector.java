/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.introspect;

import com.relocated.fasterxml.jackson.databind.BeanDescription;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.SerializationConfig;
import com.relocated.fasterxml.jackson.databind.cfg.MapperConfig;

public abstract class ClassIntrospector {
    protected ClassIntrospector() {
    }

    public abstract BeanDescription forSerialization(SerializationConfig var1, JavaType var2, MixInResolver var3);

    public abstract BeanDescription forDeserialization(DeserializationConfig var1, JavaType var2, MixInResolver var3);

    public abstract BeanDescription forDeserializationWithBuilder(DeserializationConfig var1, JavaType var2, MixInResolver var3);

    public abstract BeanDescription forCreation(DeserializationConfig var1, JavaType var2, MixInResolver var3);

    public abstract BeanDescription forClassAnnotations(MapperConfig<?> var1, JavaType var2, MixInResolver var3);

    public abstract BeanDescription forDirectClassAnnotations(MapperConfig<?> var1, JavaType var2, MixInResolver var3);

    public static interface MixInResolver {
        public Class<?> findMixInClassFor(Class<?> var1);

        public MixInResolver copy();
    }
}

