/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.PropertyMetadata;
import com.relocated.fasterxml.jackson.databind.PropertyName;
import com.relocated.fasterxml.jackson.databind.deser.NullValueProvider;
import com.relocated.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.relocated.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.relocated.fasterxml.jackson.databind.util.Annotations;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class CreatorProperty
extends SettableBeanProperty {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedParameter _annotated;
    protected final Object _injectableValueId;
    protected final int _creatorIndex;
    protected SettableBeanProperty _fallbackSetter;

    public CreatorProperty(PropertyName name, JavaType type, PropertyName wrapperName, TypeDeserializer typeDeser, Annotations contextAnnotations, AnnotatedParameter param, int index, Object injectableValueId, PropertyMetadata metadata) {
        super(name, type, wrapperName, typeDeser, contextAnnotations, metadata);
        this._annotated = param;
        this._creatorIndex = index;
        this._injectableValueId = injectableValueId;
        this._fallbackSetter = null;
    }

    protected CreatorProperty(CreatorProperty src, PropertyName newName) {
        super(src, newName);
        this._annotated = src._annotated;
        this._creatorIndex = src._creatorIndex;
        this._injectableValueId = src._injectableValueId;
        this._fallbackSetter = src._fallbackSetter;
    }

    protected CreatorProperty(CreatorProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
        super(src, deser, nva);
        this._annotated = src._annotated;
        this._creatorIndex = src._creatorIndex;
        this._injectableValueId = src._injectableValueId;
        this._fallbackSetter = src._fallbackSetter;
    }

    @Override
    public SettableBeanProperty withName(PropertyName newName) {
        return new CreatorProperty(this, newName);
    }

    @Override
    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
        if (this._valueDeserializer == deser) {
            return this;
        }
        return new CreatorProperty(this, deser, this._nullProvider);
    }

    @Override
    public SettableBeanProperty withNullProvider(NullValueProvider nva) {
        return new CreatorProperty(this, this._valueDeserializer, nva);
    }

    @Override
    public void fixAccess(DeserializationConfig config) {
        if (this._fallbackSetter != null) {
            this._fallbackSetter.fixAccess(config);
        }
    }

    public void setFallbackSetter(SettableBeanProperty fallbackSetter) {
        this._fallbackSetter = fallbackSetter;
    }

    public Object findInjectableValue(DeserializationContext context, Object beanInstance) throws JsonMappingException {
        if (this._injectableValueId == null) {
            context.reportBadDefinition(ClassUtil.classOf(beanInstance), String.format("Property '%s' (type %s) has no injectable value id configured", this.getName(), this.getClass().getName()));
        }
        return context.findInjectableValue(this._injectableValueId, this, beanInstance);
    }

    public void inject(DeserializationContext context, Object beanInstance) throws IOException {
        this.set(beanInstance, this.findInjectableValue(context, beanInstance));
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> acls) {
        if (this._annotated == null) {
            return null;
        }
        return this._annotated.getAnnotation(acls);
    }

    @Override
    public AnnotatedMember getMember() {
        return this._annotated;
    }

    @Override
    public int getCreatorIndex() {
        return this._creatorIndex;
    }

    @Override
    public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
        this._verifySetter();
        this._fallbackSetter.set(instance, this.deserialize(p, ctxt));
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
        this._verifySetter();
        return this._fallbackSetter.setAndReturn(instance, this.deserialize(p, ctxt));
    }

    @Override
    public void set(Object instance, Object value) throws IOException {
        this._verifySetter();
        this._fallbackSetter.set(instance, value);
    }

    @Override
    public Object setAndReturn(Object instance, Object value) throws IOException {
        this._verifySetter();
        return this._fallbackSetter.setAndReturn(instance, value);
    }

    @Override
    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    @Override
    public String toString() {
        return "[creator property, name '" + this.getName() + "'; inject id '" + this._injectableValueId + "']";
    }

    private final void _verifySetter() throws IOException {
        if (this._fallbackSetter == null) {
            this._reportMissingSetter(null, null);
        }
    }

    private void _reportMissingSetter(JsonParser p, DeserializationContext ctxt) throws IOException {
        String msg = "No fallback setter/field defined for creator property '" + this.getName() + "'";
        if (ctxt == null) {
            throw InvalidDefinitionException.from(p, msg, this.getType());
        }
        ctxt.reportBadDefinition(this.getType(), msg);
    }
}

