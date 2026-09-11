/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ser.impl;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonNode;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.relocated.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

public class FailingSerializer
extends StdSerializer<Object> {
    protected final String _msg;

    public FailingSerializer(String msg) {
        super(Object.class);
        this._msg = msg;
    }

    @Override
    public void serialize(Object value, JsonGenerator g, SerializerProvider provider) throws IOException {
        provider.reportMappingProblem(this._msg, new Object[0]);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return null;
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) {
    }
}

