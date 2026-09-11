/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.jsontype.impl;

import com.relocated.fasterxml.jackson.annotation.JsonTypeInfo;
import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.relocated.fasterxml.jackson.databind.jsontype.impl.TypeSerializerBase;

public class AsArrayTypeSerializer
extends TypeSerializerBase {
    public AsArrayTypeSerializer(TypeIdResolver idRes, BeanProperty property) {
        super(idRes, property);
    }

    @Override
    public AsArrayTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsArrayTypeSerializer(this._idResolver, prop);
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }
}

