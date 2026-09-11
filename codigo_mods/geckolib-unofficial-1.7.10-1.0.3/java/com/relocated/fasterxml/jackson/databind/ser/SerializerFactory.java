/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ser;

import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.SerializationConfig;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.relocated.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.relocated.fasterxml.jackson.databind.ser.Serializers;

public abstract class SerializerFactory {
    public abstract SerializerFactory withAdditionalSerializers(Serializers var1);

    public abstract SerializerFactory withAdditionalKeySerializers(Serializers var1);

    public abstract SerializerFactory withSerializerModifier(BeanSerializerModifier var1);

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider var1, JavaType var2) throws JsonMappingException;

    public abstract TypeSerializer createTypeSerializer(SerializationConfig var1, JavaType var2) throws JsonMappingException;

    public abstract JsonSerializer<Object> createKeySerializer(SerializationConfig var1, JavaType var2, JsonSerializer<Object> var3) throws JsonMappingException;
}

