/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;

public interface ContextualDeserializer {
    public JsonDeserializer<?> createContextual(DeserializationContext var1, BeanProperty var2) throws JsonMappingException;
}

