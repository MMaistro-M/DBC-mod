/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.cfg;

import com.relocated.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.relocated.fasterxml.jackson.annotation.ObjectIdResolver;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.KeyDeserializer;
import com.relocated.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.relocated.fasterxml.jackson.databind.SerializationConfig;
import com.relocated.fasterxml.jackson.databind.cfg.MapperConfig;
import com.relocated.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.relocated.fasterxml.jackson.databind.introspect.Annotated;
import com.relocated.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.relocated.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.relocated.fasterxml.jackson.databind.util.Converter;

public abstract class HandlerInstantiator {
    public abstract JsonDeserializer<?> deserializerInstance(DeserializationConfig var1, Annotated var2, Class<?> var3);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig var1, Annotated var2, Class<?> var3);

    public abstract JsonSerializer<?> serializerInstance(SerializationConfig var1, Annotated var2, Class<?> var3);

    public abstract TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> var1, Annotated var2, Class<?> var3);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig<?> var1, Annotated var2, Class<?> var3);

    public ValueInstantiator valueInstantiatorInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        return null;
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return null;
    }

    public ObjectIdResolver resolverIdGeneratorInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return null;
    }

    public PropertyNamingStrategy namingStrategyInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return null;
    }

    public Converter<?, ?> converterInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return null;
    }

    public VirtualBeanPropertyWriter virtualPropertyWriterInstance(MapperConfig<?> config, Class<?> implClass) {
        return null;
    }

    public Object includeFilterInstance(SerializationConfig config, BeanPropertyDefinition forProperty, Class<?> filterClass) {
        return null;
    }
}

