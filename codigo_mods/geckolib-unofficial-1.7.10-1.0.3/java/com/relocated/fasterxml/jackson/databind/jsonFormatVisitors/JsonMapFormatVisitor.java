/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.jsonFormatVisitors;

import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.relocated.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider;

public interface JsonMapFormatVisitor
extends JsonFormatVisitorWithSerializerProvider {
    public void keyFormat(JsonFormatVisitable var1, JavaType var2) throws JsonMappingException;

    public void valueFormat(JsonFormatVisitable var1, JavaType var2) throws JsonMappingException;

    public static class Base
    implements JsonMapFormatVisitor {
        protected SerializerProvider _provider;

        public Base() {
        }

        public Base(SerializerProvider p) {
            this._provider = p;
        }

        @Override
        public SerializerProvider getProvider() {
            return this._provider;
        }

        @Override
        public void setProvider(SerializerProvider p) {
            this._provider = p;
        }

        @Override
        public void keyFormat(JsonFormatVisitable handler, JavaType keyType) throws JsonMappingException {
        }

        @Override
        public void valueFormat(JsonFormatVisitable handler, JavaType valueType) throws JsonMappingException {
        }
    }
}

