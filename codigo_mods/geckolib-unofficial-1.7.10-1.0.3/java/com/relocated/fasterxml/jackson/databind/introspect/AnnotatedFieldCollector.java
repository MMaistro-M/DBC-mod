/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.introspect;

import com.relocated.fasterxml.jackson.databind.AnnotationIntrospector;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.relocated.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.relocated.fasterxml.jackson.databind.introspect.CollectorBase;
import com.relocated.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedFieldCollector
extends CollectorBase {
    private final TypeFactory _typeFactory;
    private final ClassIntrospector.MixInResolver _mixInResolver;

    AnnotatedFieldCollector(AnnotationIntrospector intr, TypeFactory types, ClassIntrospector.MixInResolver mixins) {
        super(intr);
        this._typeFactory = types;
        this._mixInResolver = intr == null ? null : mixins;
    }

    public static List<AnnotatedField> collectFields(AnnotationIntrospector intr, TypeResolutionContext tc, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type) {
        return new AnnotatedFieldCollector(intr, types, mixins).collect(tc, type);
    }

    List<AnnotatedField> collect(TypeResolutionContext tc, JavaType type) {
        Map<String, FieldBuilder> foundFields = this._findFields(tc, type, null);
        if (foundFields == null) {
            return Collections.emptyList();
        }
        ArrayList<AnnotatedField> result = new ArrayList<AnnotatedField>(foundFields.size());
        for (FieldBuilder b : foundFields.values()) {
            result.add(b.build());
        }
        return result;
    }

    private Map<String, FieldBuilder> _findFields(TypeResolutionContext tc, JavaType type, Map<String, FieldBuilder> fields) {
        Class<?> mixin;
        JavaType parent = type.getSuperClass();
        if (parent == null) {
            return fields;
        }
        Class<?> cls = type.getRawClass();
        fields = this._findFields(new TypeResolutionContext.Basic(this._typeFactory, parent.getBindings()), parent, fields);
        for (Field f : ClassUtil.getDeclaredFields(cls)) {
            if (!this._isIncludableField(f)) continue;
            if (fields == null) {
                fields = new LinkedHashMap<String, FieldBuilder>();
            }
            FieldBuilder b = new FieldBuilder(tc, f);
            if (this._intr != null) {
                b.annotations = this.collectAnnotations(b.annotations, f.getDeclaredAnnotations());
            }
            fields.put(f.getName(), b);
        }
        if (this._mixInResolver != null && (mixin = this._mixInResolver.findMixInClassFor(cls)) != null) {
            this._addFieldMixIns(mixin, cls, fields);
        }
        return fields;
    }

    private void _addFieldMixIns(Class<?> mixInCls, Class<?> targetClass, Map<String, FieldBuilder> fields) {
        List<Class<?>> parents = ClassUtil.findSuperClasses(mixInCls, targetClass, true);
        for (Class<?> mixin : parents) {
            for (Field mixinField : ClassUtil.getDeclaredFields(mixin)) {
                String name;
                FieldBuilder b;
                if (!this._isIncludableField(mixinField) || (b = fields.get(name = mixinField.getName())) == null) continue;
                b.annotations = this.collectAnnotations(b.annotations, mixinField.getDeclaredAnnotations());
            }
        }
    }

    private boolean _isIncludableField(Field f) {
        if (f.isSynthetic()) {
            return false;
        }
        int mods = f.getModifiers();
        return !Modifier.isStatic(mods);
    }

    private static final class FieldBuilder {
        public final TypeResolutionContext typeContext;
        public final Field field;
        public AnnotationCollector annotations;

        public FieldBuilder(TypeResolutionContext tc, Field f) {
            this.typeContext = tc;
            this.field = f;
            this.annotations = AnnotationCollector.emptyCollector();
        }

        public AnnotatedField build() {
            return new AnnotatedField(this.typeContext, this.field, this.annotations.asAnnotationMap());
        }
    }
}

