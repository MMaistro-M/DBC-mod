/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.annotation;

import com.relocated.fasterxml.jackson.annotation.JacksonAnnotation;
import com.relocated.fasterxml.jackson.annotation.OptBoolean;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(value=RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonMerge {
    public OptBoolean value() default OptBoolean.TRUE;
}

