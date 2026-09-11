/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ser;

import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;

public interface ContextualSerializer {
    public JsonSerializer<?> createContextual(SerializerProvider var1, BeanProperty var2) throws JsonMappingException;
}

