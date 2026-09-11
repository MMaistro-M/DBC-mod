/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.annotation;

import com.relocated.fasterxml.jackson.annotation.JacksonAnnotation;
import com.relocated.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonValueInstantiator {
    public Class<? extends ValueInstantiator> value();
}

