/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser.std;

import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.relocated.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public class StringDeserializer
extends StdScalarDeserializer<String> {
    private static final long serialVersionUID = 1L;
    public static final StringDeserializer instance = new StringDeserializer();

    public StringDeserializer() {
        super(String.class);
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return "";
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            return p.getText();
        }
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.START_ARRAY) {
            return (String)this._deserializeFromArray(p, ctxt);
        }
        if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (ob instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[])ob, false);
            }
            return ob.toString();
        }
        String text = p.getValueAsString();
        if (text != null) {
            return text;
        }
        return (String)ctxt.handleUnexpectedToken(this._valueClass, p);
    }

    @Override
    public String deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p, ctxt);
    }
}

