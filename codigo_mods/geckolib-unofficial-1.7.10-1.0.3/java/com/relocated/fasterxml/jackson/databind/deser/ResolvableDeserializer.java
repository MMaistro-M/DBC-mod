/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;

public interface ResolvableDeserializer {
    public void resolve(DeserializationContext var1) throws JsonMappingException;
}

