/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.util.AccessPattern;

public interface NullValueProvider {
    public Object getNullValue(DeserializationContext var1) throws JsonMappingException;

    public AccessPattern getNullAccessPattern();
}

