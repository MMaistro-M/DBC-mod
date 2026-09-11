/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.introspect;

import com.relocated.fasterxml.jackson.databind.AnnotationIntrospector;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.cfg.MapperConfig;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.relocated.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.relocated.fasterxml.jackson.databind.type.TypeBindings;
import com.relocated.fasterxml.jackson.databind.util.Annotations;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;

public class AnnotatedClassResolver {
    private static final Annotations NO_ANNOTATIONS = AnnotationCollector.emptyAnnotations();
    private final MapperConfig<?> _config;
    private final AnnotationIntrospector _intr;
    private final ClassIntrospector.MixInResolver _mixInResolver;
    private final TypeBindings _bindings;
    private final JavaType _type;
    private final Class<?> _class;
    private final Class<?> _primaryMixin;

    AnnotatedClassResolver(MapperConfig<?> config, JavaType type, ClassIntrospector.MixInResolver r) {
        this._config = config;
        this._type = type;
        this._class = type.getRawClass();
        this._mixInResolver = r;
        this._bindings = type.getBindings();
        this._intr = config.isAnnotationProcessingEnabled() ? config.getAnnotationIntrospector() : null;
        this._primaryMixin = this._config.findMixInClassFor(this._class);
    }

    AnnotatedClassResolver(MapperConfig<?> config, Class<?> cls, ClassIntrospector.MixInResolver r) {
        this._config = config;
        this._type = null;
        this._class = cls;
        this._mixInResolver = r;
        this._bindings = TypeBindings.emptyBindings();
        if (config == null) {
            this._intr = null;
            this._primaryMixin = null;
        } else {
            this._intr = config.isAnnotationProcessingEnabled() ? config.getAnnotationIntrospector() : null;
            this._primaryMixin = this._config.findMixInClassFor(this._class);
        }
    }

    public static AnnotatedClass resolve(MapperConfig<?> config, JavaType forType, ClassIntrospector.MixInResolver r) {
        if (forType.isArrayType() && AnnotatedClassResolver.skippableArray(config, forType.getRawClass())) {
            return AnnotatedClassResolver.createArrayType(config, forType.getRawClass());
        }
        return new AnnotatedClassResolver(config, forType, r).resolveFully();
    }

    public static AnnotatedClass resolveWithoutSuperTypes(MapperConfig<?> config, Class<?> forType) {
        return AnnotatedClassResolver.resolveWithoutSuperTypes(config, forType, config);
    }

    public static AnnotatedClass resolveWithoutSuperTypes(MapperConfig<?> config, JavaType forType, ClassIntrospector.MixInResolver r) {
        if (forType.isArrayType() && AnnotatedClassResolver.skippableArray(config, forType.getRawClass())) {
            return AnnotatedClassResolver.createArrayType(config, forType.getRawClass());
        }
        return new AnnotatedClassResolver(config, forType, r).resolveWithoutSuperTypes();
    }

    public static AnnotatedClass resolveWithoutSuperTypes(MapperConfig<?> config, Class<?> forType, ClassIntrospector.MixInResolver r) {
        if (forType.isArray() && AnnotatedClassResolver.skippableArray(config, forType)) {
            return AnnotatedClassResolver.createArrayType(config, forType);
        }
        return new AnnotatedClassResolver(config, forType, r).resolveWithoutSuperTypes();
    }

    private static boolean skippableArray(MapperConfig<?> config, Class<?> type) {
        return config == null || config.findMixInClassFor(type) == null;
    }

    static AnnotatedClass createPrimordial(Class<?> raw) {
        return new AnnotatedClass(raw);
    }

    static AnnotatedClass createArrayType(MapperConfig<?> config, Class<?> raw) {
        return new AnnotatedClass(raw);
    }

    AnnotatedClass resolveFully() {
        List<JavaType> superTypes = ClassUtil.findSuperTypes(this._type, null, false);
        return new AnnotatedClass(this._type, this._class, superTypes, this._primaryMixin, this.resolveClassAnnotations(superTypes), this._bindings, this._intr, this._mixInResolver, this._config.getTypeFactory());
    }

    AnnotatedClass resolveWithoutSuperTypes() {
        List<JavaType> superTypes = Collections.emptyList();
        return new AnnotatedClass(null, this._class, superTypes, this._primaryMixin, this.resolveClassAnnotations(superTypes), this._bindings, this._intr, this._config, this._config.getTypeFactory());
    }

    private Annotations resolveClassAnnotations(List<JavaType> superTypes) {
        if (this._intr == null) {
            return NO_ANNOTATIONS;
        }
        AnnotationCollector resolvedCA = AnnotationCollector.emptyCollector();
        if (this._primaryMixin != null) {
            resolvedCA = this._addClassMixIns(resolvedCA, this._class, this._primaryMixin);
        }
        resolvedCA = this._addAnnotationsIfNotPresent(resolvedCA, ClassUtil.findClassAnnotations(this._class));
        for (JavaType type : superTypes) {
            if (this._mixInResolver != null) {
                Class<?> cls = type.getRawClass();
                resolvedCA = this._addClassMixIns(resolvedCA, cls, this._mixInResolver.findMixInClassFor(cls));
            }
            resolvedCA = this._addAnnotationsIfNotPresent(resolvedCA, ClassUtil.findClassAnnotations(type.getRawClass()));
        }
        if (this._mixInResolver != null) {
            resolvedCA = this._addClassMixIns(resolvedCA, Object.class, this._mixInResolver.findMixInClassFor(Object.class));
        }
        return resolvedCA.asAnnotations();
    }

    private AnnotationCollector _addClassMixIns(AnnotationCollector annotations, Class<?> target, Class<?> mixin) {
        if (mixin != null) {
            annotations = this._addAnnotationsIfNotPresent(annotations, ClassUtil.findClassAnnotations(mixin));
            for (Class<?> parent : ClassUtil.findSuperClasses(mixin, target, false)) {
                annotations = this._addAnnotationsIfNotPresent(annotations, ClassUtil.findClassAnnotations(parent));
            }
        }
        return annotations;
    }

    private AnnotationCollector _addAnnotationsIfNotPresent(AnnotationCollector c, Annotation[] anns) {
        if (anns != null) {
            for (Annotation ann : anns) {
                if (c.isPresent(ann)) continue;
                c = c.addOrOverride(ann);
                if (!this._intr.isAnnotationBundle(ann)) continue;
                c = this._addFromBundleIfNotPresent(c, ann);
            }
        }
        return c;
    }

    private AnnotationCollector _addFromBundleIfNotPresent(AnnotationCollector c, Annotation bundle) {
        for (Annotation ann : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (ann instanceof Target || ann instanceof Retention || c.isPresent(ann)) continue;
            c = c.addOrOverride(ann);
            if (!this._intr.isAnnotationBundle(ann)) continue;
            c = this._addFromBundleIfNotPresent(c, ann);
        }
        return c;
    }
}

