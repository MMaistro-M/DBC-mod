/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.jsontype.impl;

import com.relocated.fasterxml.jackson.annotation.JsonTypeInfo;
import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.core.util.JsonParserSequence;
import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.relocated.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase;
import com.relocated.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsArrayTypeDeserializer
extends TypeDeserializerBase
implements Serializable {
    private static final long serialVersionUID = 1L;

    public AsArrayTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public AsArrayTypeDeserializer(AsArrayTypeDeserializer src, BeanProperty property) {
        super(src, property);
    }

    @Override
    public TypeDeserializer forProperty(BeanProperty prop) {
        return prop == this._property ? this : new AsArrayTypeDeserializer(this, prop);
    }

    @Override
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }

    @Override
    public Object deserializeTypedFromArray(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromScalar(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    @Override
    public Object deserializeTypedFromAny(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return this._deserialize(jp, ctxt);
    }

    protected Object _deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object typeId;
        if (p.canReadTypeId() && (typeId = p.getTypeId()) != null) {
            return this._deserializeWithNativeTypeId(p, ctxt, typeId);
        }
        boolean hadStartArray = p.isExpectedStartArrayToken();
        String typeId2 = this._locateTypeId(p, ctxt);
        JsonDeserializer<Object> deser = this._findDeserializer(ctxt, typeId2);
        if (this._typeIdVisible && !this._usesExternalId() && p.getCurrentToken() == JsonToken.START_OBJECT) {
            TokenBuffer tb = new TokenBuffer(null, false);
            tb.writeStartObject();
            tb.writeFieldName(this._typePropertyName);
            tb.writeString(typeId2);
            p.clearCurrentToken();
            p = JsonParserSequence.createFlattened(false, tb.asParser(p), p);
            p.nextToken();
        }
        Object value = deser.deserialize(p, ctxt);
        if (hadStartArray && p.nextToken() != JsonToken.END_ARRAY) {
            ctxt.reportWrongTokenException(this.baseType(), JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value", new Object[0]);
        }
        return value;
    }

    protected String _locateTypeId(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!p.isExpectedStartArrayToken()) {
            if (this._defaultImpl != null) {
                return this._idResolver.idFromBaseType();
            }
            ctxt.reportWrongTokenException(this.baseType(), JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + this.baseTypeName(), new Object[0]);
            return null;
        }
        JsonToken t = p.nextToken();
        if (t == JsonToken.VALUE_STRING) {
            String result = p.getText();
            p.nextToken();
            return result;
        }
        if (this._defaultImpl != null) {
            return this._idResolver.idFromBaseType();
        }
        ctxt.reportWrongTokenException(this.baseType(), JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of %s)", this.baseTypeName());
        return null;
    }

    protected boolean _usesExternalId() {
        return false;
    }
}

