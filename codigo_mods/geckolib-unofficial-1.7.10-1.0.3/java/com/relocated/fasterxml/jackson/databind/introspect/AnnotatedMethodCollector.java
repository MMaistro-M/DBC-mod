/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.introspect;

import com.relocated.fasterxml.jackson.databind.AnnotationIntrospector;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMethodMap;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.relocated.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.relocated.fasterxml.jackson.databind.introspect.CollectorBase;
import com.relocated.fasterxml.jackson.databind.introspect.MemberKey;
import com.relocated.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedMethodCollector
extends CollectorBase {
    private final ClassIntrospector.MixInResolver _mixInResolver;

    AnnotatedMethodCollector(AnnotationIntrospector intr, ClassIntrospector.MixInResolver mixins) {
        super(intr);
        this._mixInResolver = intr == null ? null : mixins;
    }

    public static AnnotatedMethodMap collectMethods(AnnotationIntrospector intr, TypeResolutionContext tc, ClassIntrospector.MixInResolver mixins, TypeFactory types, JavaType type, List<JavaType> superTypes, Class<?> primaryMixIn) {
        return new AnnotatedMethodCollector(intr, mixins).collect(types, tc, type, superTypes, primaryMixIn);
    }

    AnnotatedMethodMap collect(TypeFactory typeFactory, TypeResolutionContext tc, JavaType mainType, List<JavaType> superTypes, Class<?> primaryMixIn) {
        LinkedHashMap<MemberKey, MethodBuilder> methods = new LinkedHashMap<MemberKey, MethodBuilder>();
        this._addMemberMethods(tc, mainType.getRawClass(), methods, primaryMixIn);
        for (JavaType type : superTypes) {
            Class<?> mixin = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(type.getRawClass());
            this._addMemberMethods(new TypeResolutionContext.Basic(typeFactory, type.getBindings()), type.getRawClass(), methods, mixin);
        }
        if (methods.isEmpty()) {
            return new AnnotatedMethodMap();
        }
        LinkedHashMap<MemberKey, AnnotatedMethod> actual = new LinkedHashMap<MemberKey, AnnotatedMethod>(methods.size());
        for (Map.Entry entry : methods.entrySet()) {
            AnnotatedMethod am = ((MethodBuilder)entry.getValue()).build();
            if (am == null) continue;
            actual.put((MemberKey)entry.getKey(), am);
        }
        return new AnnotatedMethodMap(actual);
    }

    private void _addMemberMethods(TypeResolutionContext tc, Class<?> cls, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (mixInCls != null) {
            this._addMethodMixIns(tc, cls, methods, mixInCls);
        }
        if (cls == null) {
            return;
        }
        for (Method m : ClassUtil.getClassMethods(cls)) {
            Method old;
            if (!this._isIncludableMemberMethod(m)) continue;
            MemberKey key = new MemberKey(m);
            MethodBuilder b = methods.get(key);
            if (b == null) {
                AnnotationCollector c = this._intr == null ? AnnotationCollector.emptyCollector() : this.collectAnnotations(m.getDeclaredAnnotations());
                methods.put(key, new MethodBuilder(tc, m, c));
                continue;
            }
            if (this._intr != null) {
                b.annotations = this.collectDefaultAnnotations(b.annotations, m.getDeclaredAnnotations());
            }
            if ((old = b.method) == null) {
                b.method = m;
                continue;
            }
            if (!Modifier.isAbstract(old.getModifiers()) || Modifier.isAbstract(m.getModifiers())) continue;
            b.method = m;
        }
    }

    protected void _addMethodMixIns(TypeResolutionContext tc, Class<?> targetClass, Map<MemberKey, MethodBuilder> methods, Class<?> mixInCls) {
        if (this._intr == null) {
            return;
        }
        for (Class<?> mixin : ClassUtil.findRawSuperTypes(mixInCls, targetClass, true)) {
            for (Method m : ClassUtil.getDeclaredMethods(mixin)) {
                if (!this._isIncludableMemberMethod(m)) continue;
                MemberKey key = new MemberKey(m);
                MethodBuilder b = methods.get(key);
                Annotation[] anns = m.getDeclaredAnnotations();
                if (b == null) {
                    methods.put(key, new MethodBuilder(tc, null, this.collectAnnotations(anns)));
                    continue;
                }
                b.annotations = this.collectDefaultAnnotations(b.annotations, anns);
            }
        }
    }

    private boolean _isIncludableMemberMethod(Method m) {
        if (Modifier.isStatic(m.getModifiers()) || m.isSynthetic() || m.isBridge()) {
            return false;
        }
        int pcount = m.getParameterTypes().length;
        return pcount <= 2;
    }

    private static final class MethodBuilder {
        public final TypeResolutionContext typeContext;
        public Method method;
        public AnnotationCollector annotations;

        public MethodBuilder(TypeResolutionContext tc, Method m, AnnotationCollector ann) {
            this.typeContext = tc;
            this.method = m;
            this.annotations = ann;
        }

        public AnnotatedMethod build() {
            if (this.method == null) {
                return null;
            }
            return new AnnotatedMethod(this.typeContext, this.method, this.annotations.asAnnotationMap(), null);
        }
    }
}

