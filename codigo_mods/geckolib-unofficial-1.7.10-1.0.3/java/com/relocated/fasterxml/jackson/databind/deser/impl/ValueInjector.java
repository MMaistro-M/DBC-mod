/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser.impl;

import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.PropertyMetadata;
import com.relocated.fasterxml.jackson.databind.PropertyName;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;

public class ValueInjector
extends BeanProperty.Std {
    private static final long serialVersionUID = 1L;
    protected final Object _valueId;

    public ValueInjector(PropertyName propName, JavaType type, AnnotatedMember mutator, Object valueId) {
        super(propName, type, null, mutator, PropertyMetadata.STD_OPTIONAL);
        this._valueId = valueId;
    }

    public Object findValue(DeserializationContext context, Object beanInstance) throws JsonMappingException {
        return context.findInjectableValue(this._valueId, this, beanInstance);
    }

    public void inject(DeserializationContext context, Object beanInstance) throws IOException {
        this._member.setValue(beanInstance, this.findValue(context, beanInstance));
    }
}

