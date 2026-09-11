/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.KeyDeserializer;

public interface ContextualKeyDeserializer {
    public KeyDeserializer createContextual(DeserializationContext var1, BeanProperty var2) throws JsonMappingException;
}

