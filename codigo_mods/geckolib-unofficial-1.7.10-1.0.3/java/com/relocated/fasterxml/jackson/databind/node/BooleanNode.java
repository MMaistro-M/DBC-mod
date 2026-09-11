/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.node;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.node.JsonNodeType;
import com.relocated.fasterxml.jackson.databind.node.ValueNode;
import java.io.IOException;

public class BooleanNode
extends ValueNode {
    public static final BooleanNode TRUE = new BooleanNode(true);
    public static final BooleanNode FALSE = new BooleanNode(false);
    private final boolean _value;

    private BooleanNode(boolean v) {
        this._value = v;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.BOOLEAN;
    }

    @Override
    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override
    public boolean booleanValue() {
        return this._value;
    }

    @Override
    public String asText() {
        return this._value ? "true" : "false";
    }

    @Override
    public boolean asBoolean() {
        return this._value;
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return this._value;
    }

    @Override
    public int asInt(int defaultValue) {
        return this._value ? 1 : 0;
    }

    @Override
    public long asLong(long defaultValue) {
        return this._value ? 1L : 0L;
    }

    @Override
    public double asDouble(double defaultValue) {
        return this._value ? 1.0 : 0.0;
    }

    @Override
    public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
        g.writeBoolean(this._value);
    }

    @Override
    public int hashCode() {
        return this._value ? 3 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof BooleanNode)) {
            return false;
        }
        return this._value == ((BooleanNode)o)._value;
    }
}

