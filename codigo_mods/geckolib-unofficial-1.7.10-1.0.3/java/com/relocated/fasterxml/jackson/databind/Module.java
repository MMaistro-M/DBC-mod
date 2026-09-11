/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind;

import com.relocated.fasterxml.jackson.core.JsonFactory;
import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.ObjectCodec;
import com.relocated.fasterxml.jackson.core.Version;
import com.relocated.fasterxml.jackson.core.Versioned;
import com.relocated.fasterxml.jackson.databind.AbstractTypeResolver;
import com.relocated.fasterxml.jackson.databind.AnnotationIntrospector;
import com.relocated.fasterxml.jackson.databind.DeserializationFeature;
import com.relocated.fasterxml.jackson.databind.MapperFeature;
import com.relocated.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.relocated.fasterxml.jackson.databind.SerializationFeature;
import com.relocated.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import com.relocated.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.relocated.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.relocated.fasterxml.jackson.databind.deser.Deserializers;
import com.relocated.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.relocated.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.relocated.fasterxml.jackson.databind.jsontype.NamedType;
import com.relocated.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.relocated.fasterxml.jackson.databind.ser.Serializers;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import com.relocated.fasterxml.jackson.databind.type.TypeModifier;
import java.util.Collection;

public abstract class Module
implements Versioned {
    public abstract String getModuleName();

    @Override
    public abstract Version version();

    public Object getTypeId() {
        return this.getClass().getName();
    }

    public abstract void setupModule(SetupContext var1);

    public static interface SetupContext {
        public Version getMapperVersion();

        public <C extends ObjectCodec> C getOwner();

        public TypeFactory getTypeFactory();

        public boolean isEnabled(MapperFeature var1);

        public boolean isEnabled(DeserializationFeature var1);

        public boolean isEnabled(SerializationFeature var1);

        public boolean isEnabled(JsonFactory.Feature var1);

        public boolean isEnabled(JsonParser.Feature var1);

        public boolean isEnabled(JsonGenerator.Feature var1);

        public MutableConfigOverride configOverride(Class<?> var1);

        public void addDeserializers(Deserializers var1);

        public void addKeyDeserializers(KeyDeserializers var1);

        public void addSerializers(Serializers var1);

        public void addKeySerializers(Serializers var1);

        public void addBeanDeserializerModifier(BeanDeserializerModifier var1);

        public void addBeanSerializerModifier(BeanSerializerModifier var1);

        public void addAbstractTypeResolver(AbstractTypeResolver var1);

        public void addTypeModifier(TypeModifier var1);

        public void addValueInstantiators(ValueInstantiators var1);

        public void setClassIntrospector(ClassIntrospector var1);

        public void insertAnnotationIntrospector(AnnotationIntrospector var1);

        public void appendAnnotationIntrospector(AnnotationIntrospector var1);

        public void registerSubtypes(Class<?> ... var1);

        public void registerSubtypes(NamedType ... var1);

        public void registerSubtypes(Collection<Class<?>> var1);

        public void setMixInAnnotations(Class<?> var1, Class<?> var2);

        public void addDeserializationProblemHandler(DeserializationProblemHandler var1);

        public void setNamingStrategy(PropertyNamingStrategy var1);
    }
}

