/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser.impl;

import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.PropertyName;
import com.relocated.fasterxml.jackson.databind.deser.NullValueProvider;
import com.relocated.fasterxml.jackson.databind.exc.InvalidNullException;
import com.relocated.fasterxml.jackson.databind.util.AccessPattern;
import java.io.Serializable;

public class NullsFailProvider
implements NullValueProvider,
Serializable {
    private static final long serialVersionUID = 1L;
    protected final PropertyName _name;
    protected final JavaType _type;

    protected NullsFailProvider(PropertyName name, JavaType type) {
        this._name = name;
        this._type = type;
    }

    public static NullsFailProvider constructForProperty(BeanProperty prop) {
        return new NullsFailProvider(prop.getFullName(), prop.getType());
    }

    public static NullsFailProvider constructForRootValue(JavaType t) {
        return new NullsFailProvider(null, t);
    }

    @Override
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override
    public Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        throw InvalidNullException.from(ctxt, this._name, this._type);
    }
}

