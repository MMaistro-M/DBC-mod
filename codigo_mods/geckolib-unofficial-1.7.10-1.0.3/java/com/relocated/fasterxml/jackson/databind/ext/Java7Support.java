/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.ext;

import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.PropertyName;
import com.relocated.fasterxml.jackson.databind.introspect.Annotated;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.util.logging.Logger;

public abstract class Java7Support {
    private static final Java7Support IMPL;

    public static Java7Support instance() {
        return IMPL;
    }

    public abstract Boolean findTransient(Annotated var1);

    public abstract Boolean hasCreatorAnnotation(Annotated var1);

    public abstract PropertyName findConstructorName(AnnotatedParameter var1);

    public abstract Class<?> getClassJavaNioFilePath();

    public abstract JsonDeserializer<?> getDeserializerForJavaNioFilePath(Class<?> var1);

    public abstract JsonSerializer<?> getSerializerForJavaNioFilePath(Class<?> var1);

    static {
        Java7Support impl = null;
        try {
            Class<?> cls = Class.forName("com.relocated.fasterxml.jackson.databind.ext.Java7SupportImpl");
            impl = (Java7Support)ClassUtil.createInstance(cls, false);
        }
        catch (Throwable t) {
            Logger.getLogger(Java7Support.class.getName()).warning("Unable to load JDK7 types (annotations, java.nio.file.Path): no Java7 support added");
        }
        IMPL = impl;
    }
}

