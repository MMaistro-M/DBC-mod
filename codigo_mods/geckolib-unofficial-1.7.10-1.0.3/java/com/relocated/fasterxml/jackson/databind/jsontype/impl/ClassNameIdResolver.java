/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.jsontype.impl;

import com.relocated.fasterxml.jackson.annotation.JsonTypeInfo;
import com.relocated.fasterxml.jackson.databind.DatabindContext;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;

public class ClassNameIdResolver
extends TypeIdResolverBase {
    public ClassNameIdResolver(JavaType baseType, TypeFactory typeFactory) {
        super(baseType, typeFactory);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CLASS;
    }

    public void registerSubtype(Class<?> type, String name) {
    }

    @Override
    public String idFromValue(Object value) {
        return this._idFrom(value, value.getClass(), this._typeFactory);
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> type) {
        return this._idFrom(value, type, this._typeFactory);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        return this._typeFromId(id, context);
    }

    protected JavaType _typeFromId(String id, DatabindContext ctxt) throws IOException {
        JavaType t = ctxt.resolveSubType(this._baseType, id);
        if (t == null && ctxt instanceof DeserializationContext) {
            return ((DeserializationContext)ctxt).handleUnknownTypeId(this._baseType, id, this, "no such class found");
        }
        return t;
    }

    protected final String _idFrom(Object value, Class<?> cls, TypeFactory typeFactory) {
        Class<?> staticType;
        Class<?> outer;
        String str;
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        if ((str = cls.getName()).startsWith("java.util")) {
            if (value instanceof EnumSet) {
                Class<? extends Enum<?>> enumClass = ClassUtil.findEnumType((EnumSet)value);
                str = typeFactory.constructCollectionType(EnumSet.class, enumClass).toCanonical();
            } else if (value instanceof EnumMap) {
                Class<? extends Enum<?>> enumClass = ClassUtil.findEnumType((EnumMap)value);
                Class<Object> valueClass = Object.class;
                str = typeFactory.constructMapType(EnumMap.class, enumClass, valueClass).toCanonical();
            } else {
                String end = str.substring(9);
                if ((end.startsWith(".Arrays$") || end.startsWith(".Collections$")) && str.indexOf("List") >= 0) {
                    str = "java.util.ArrayList";
                }
            }
        } else if (str.indexOf(36) >= 0 && (outer = ClassUtil.getOuterClass(cls)) != null && ClassUtil.getOuterClass(staticType = this._baseType.getRawClass()) == null) {
            cls = this._baseType.getRawClass();
            str = cls.getName();
        }
        return str;
    }

    @Override
    public String getDescForKnownTypeIds() {
        return "class name used as type id";
    }
}

