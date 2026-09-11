/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser.std;

import com.relocated.fasterxml.jackson.annotation.JsonFormat;
import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.JsonProcessingException;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.DeserializationFeature;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer
extends StdDeserializer<EnumSet<?>>
implements ContextualDeserializer {
    private static final long serialVersionUID = 1L;
    protected final JavaType _enumType;
    protected final Class<Enum> _enumClass;
    protected JsonDeserializer<Enum<?>> _enumDeserializer;
    protected final Boolean _unwrapSingle;

    public EnumSetDeserializer(JavaType enumType, JsonDeserializer<?> deser) {
        super(EnumSet.class);
        this._enumType = enumType;
        this._enumClass = enumType.getRawClass();
        if (!this._enumClass.isEnum()) {
            throw new IllegalArgumentException("Type " + enumType + " not Java Enum type");
        }
        this._enumDeserializer = deser;
        this._unwrapSingle = null;
    }

    protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer<?> deser, Boolean unwrapSingle) {
        super(base);
        this._enumType = base._enumType;
        this._enumClass = base._enumClass;
        this._enumDeserializer = deser;
        this._unwrapSingle = unwrapSingle;
    }

    public EnumSetDeserializer withDeserializer(JsonDeserializer<?> deser) {
        if (this._enumDeserializer == deser) {
            return this;
        }
        return new EnumSetDeserializer(this, deser, this._unwrapSingle);
    }

    public EnumSetDeserializer withResolved(JsonDeserializer<?> deser, Boolean unwrapSingle) {
        if (this._unwrapSingle == unwrapSingle && this._enumDeserializer == deser) {
            return this;
        }
        return new EnumSetDeserializer(this, deser, unwrapSingle);
    }

    @Override
    public boolean isCachable() {
        return this._enumType.getValueHandler() == null;
    }

    @Override
    public Boolean supportsUpdate(DeserializationConfig config) {
        return Boolean.TRUE;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Boolean unwrapSingle = this.findFormatFeature(ctxt, property, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<Object> deser = this._enumDeserializer;
        deser = deser == null ? ctxt.findContextualValueDeserializer(this._enumType, property) : ctxt.handleSecondaryContextualization(deser, property, this._enumType);
        return this.withResolved(deser, unwrapSingle);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        EnumSet result = this.constructSet();
        if (!p.isExpectedStartArrayToken()) {
            return this.handleNonArray(p, ctxt, result);
        }
        return this._deserialize(p, ctxt, result);
    }

    @Override
    public EnumSet<?> deserialize(JsonParser p, DeserializationContext ctxt, EnumSet<?> result) throws IOException {
        if (!p.isExpectedStartArrayToken()) {
            return this.handleNonArray(p, ctxt, result);
        }
        return this._deserialize(p, ctxt, result);
    }

    protected final EnumSet<?> _deserialize(JsonParser p, DeserializationContext ctxt, EnumSet result) throws IOException {
        try {
            JsonToken t;
            while ((t = p.nextToken()) != JsonToken.END_ARRAY) {
                if (t == JsonToken.VALUE_NULL) {
                    return (EnumSet)ctxt.handleUnexpectedToken(this._enumClass, p);
                }
                Enum<?> value = this._enumDeserializer.deserialize(p, ctxt);
                if (value == null) continue;
                result.add(value);
            }
        }
        catch (Exception e) {
            throw JsonMappingException.wrapWithPath((Throwable)e, (Object)result, result.size());
        }
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(p, ctxt);
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    protected EnumSet<?> handleNonArray(JsonParser p, DeserializationContext ctxt, EnumSet result) throws IOException {
        boolean canWrap;
        boolean bl = canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (!canWrap) {
            return (EnumSet)ctxt.handleUnexpectedToken(EnumSet.class, p);
        }
        if (p.hasToken(JsonToken.VALUE_NULL)) {
            return (EnumSet)ctxt.handleUnexpectedToken(this._enumClass, p);
        }
        try {
            Enum<?> value = this._enumDeserializer.deserialize(p, ctxt);
            if (value != null) {
                result.add(value);
            }
        }
        catch (Exception e) {
            throw JsonMappingException.wrapWithPath((Throwable)e, (Object)result, result.size());
        }
        return result;
    }
}

