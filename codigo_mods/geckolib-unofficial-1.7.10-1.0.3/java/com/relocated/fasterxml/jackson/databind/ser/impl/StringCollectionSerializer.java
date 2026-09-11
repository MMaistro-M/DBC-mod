/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ser.impl;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.core.type.WritableTypeId;
import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonNode;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.SerializationFeature;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.relocated.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class StringCollectionSerializer
extends StaticListSerializerBase<Collection<String>> {
    public static final StringCollectionSerializer instance = new StringCollectionSerializer();

    protected StringCollectionSerializer() {
        super(Collection.class);
    }

    protected StringCollectionSerializer(StringCollectionSerializer src, Boolean unwrapSingle) {
        super(src, unwrapSingle);
    }

    @Override
    public JsonSerializer<?> _withResolved(BeanProperty prop, Boolean unwrapSingle) {
        return new StringCollectionSerializer(this, unwrapSingle);
    }

    @Override
    protected JsonNode contentSchema() {
        return this.createSchemaNode("string", true);
    }

    @Override
    protected void acceptContentVisitor(JsonArrayFormatVisitor visitor) throws JsonMappingException {
        visitor.itemsFormat(JsonFormatTypes.STRING);
    }

    @Override
    public void serialize(Collection<String> value, JsonGenerator g, SerializerProvider provider) throws IOException {
        g.setCurrentValue(value);
        int len = value.size();
        if (len == 1 && (this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || this._unwrapSingle == Boolean.TRUE)) {
            this.serializeContents(value, g, provider);
            return;
        }
        g.writeStartArray(len);
        this.serializeContents(value, g, provider);
        g.writeEndArray();
    }

    @Override
    public void serializeWithType(Collection<String> value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        g.setCurrentValue(value);
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.START_ARRAY));
        this.serializeContents(value, g, provider);
        typeSer.writeTypeSuffix(g, typeIdDef);
    }

    private final void serializeContents(Collection<String> value, JsonGenerator g, SerializerProvider provider) throws IOException {
        int i = 0;
        try {
            for (String str : value) {
                if (str == null) {
                    provider.defaultSerializeNull(g);
                } else {
                    g.writeString(str);
                }
                ++i;
            }
        }
        catch (Exception e) {
            this.wrapAndThrow(provider, (Throwable)e, value, i);
        }
    }
}

