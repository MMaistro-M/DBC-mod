/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ser.std;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonNode;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.relocated.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberSerializer
extends StdScalarSerializer<Number> {
    public static final NumberSerializer instance = new NumberSerializer((Class<? extends Number>)Number.class);
    protected final boolean _isInt;

    public NumberSerializer(Class<? extends Number> rawType) {
        super(rawType, false);
        this._isInt = rawType == BigInteger.class;
    }

    @Override
    public void serialize(Number value, JsonGenerator g, SerializerProvider provider) throws IOException {
        if (value instanceof BigDecimal) {
            g.writeNumber((BigDecimal)value);
        } else if (value instanceof BigInteger) {
            g.writeNumber((BigInteger)value);
        } else if (value instanceof Long) {
            g.writeNumber(value.longValue());
        } else if (value instanceof Double) {
            g.writeNumber(value.doubleValue());
        } else if (value instanceof Float) {
            g.writeNumber(value.floatValue());
        } else if (value instanceof Integer || value instanceof Byte || value instanceof Short) {
            g.writeNumber(value.intValue());
        } else {
            g.writeNumber(value.toString());
        }
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode(this._isInt ? "integer" : "number", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (this._isInt) {
            this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.BIG_INTEGER);
        } else {
            Class h = this.handledType();
            if (h == BigDecimal.class) {
                this.visitFloatFormat(visitor, typeHint, JsonParser.NumberType.BIG_DECIMAL);
            } else {
                visitor.expectNumberFormat(typeHint);
            }
        }
    }
}

