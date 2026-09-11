/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser;

import com.relocated.fasterxml.jackson.annotation.JacksonInject;
import com.relocated.fasterxml.jackson.annotation.JsonCreator;
import com.relocated.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.relocated.fasterxml.jackson.core.JsonLocation;
import com.relocated.fasterxml.jackson.databind.AbstractTypeResolver;
import com.relocated.fasterxml.jackson.databind.AnnotationIntrospector;
import com.relocated.fasterxml.jackson.databind.BeanDescription;
import com.relocated.fasterxml.jackson.databind.BeanProperty;
import com.relocated.fasterxml.jackson.databind.DeserializationConfig;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonMappingException;
import com.relocated.fasterxml.jackson.databind.JsonNode;
import com.relocated.fasterxml.jackson.databind.KeyDeserializer;
import com.relocated.fasterxml.jackson.databind.MapperFeature;
import com.relocated.fasterxml.jackson.databind.PropertyMetadata;
import com.relocated.fasterxml.jackson.databind.PropertyName;
import com.relocated.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.relocated.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.relocated.fasterxml.jackson.databind.deser.AbstractDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.relocated.fasterxml.jackson.databind.deser.CreatorProperty;
import com.relocated.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.relocated.fasterxml.jackson.databind.deser.Deserializers;
import com.relocated.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.relocated.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.relocated.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.relocated.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.relocated.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.relocated.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.relocated.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.relocated.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.relocated.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.relocated.fasterxml.jackson.databind.introspect.Annotated;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.relocated.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.relocated.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.relocated.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.relocated.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.relocated.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.relocated.fasterxml.jackson.databind.jsontype.NamedType;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.relocated.fasterxml.jackson.databind.type.ArrayType;
import com.relocated.fasterxml.jackson.databind.type.CollectionLikeType;
import com.relocated.fasterxml.jackson.databind.type.CollectionType;
import com.relocated.fasterxml.jackson.databind.type.MapLikeType;
import com.relocated.fasterxml.jackson.databind.type.MapType;
import com.relocated.fasterxml.jackson.databind.type.ReferenceType;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import com.relocated.fasterxml.jackson.databind.util.ClassUtil;
import com.relocated.fasterxml.jackson.databind.util.EnumResolver;
import com.relocated.fasterxml.jackson.databind.util.NameTransformer;
import com.relocated.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.relocated.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory
extends DeserializerFactory
implements Serializable {
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    private static final Class<?> CLASS_CHAR_BUFFER = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_MAP_ENTRY = Map.Entry.class;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap();
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    protected final DeserializerFactoryConfig _factoryConfig;

    protected BasicDeserializerFactory(DeserializerFactoryConfig config) {
        this._factoryConfig = config;
    }

    public DeserializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig var1);

    @Override
    public final DeserializerFactory withAdditionalDeserializers(Deserializers additional) {
        return this.withConfig(this._factoryConfig.withAdditionalDeserializers(additional));
    }

    @Override
    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers additional) {
        return this.withConfig(this._factoryConfig.withAdditionalKeyDeserializers(additional));
    }

    @Override
    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier modifier) {
        return this.withConfig(this._factoryConfig.withDeserializerModifier(modifier));
    }

    @Override
    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver resolver) {
        return this.withConfig(this._factoryConfig.withAbstractTypeResolver(resolver));
    }

    @Override
    public final DeserializerFactory withValueInstantiators(ValueInstantiators instantiators) {
        return this.withConfig(this._factoryConfig.withValueInstantiators(instantiators));
    }

    @Override
    public JavaType mapAbstractType(DeserializationConfig config, JavaType type) throws JsonMappingException {
        JavaType next;
        while ((next = this._mapAbstractType2(config, type)) != null) {
            Class<?> nextCls;
            Class<?> prevCls = type.getRawClass();
            if (prevCls == (nextCls = next.getRawClass()) || !prevCls.isAssignableFrom(nextCls)) {
                throw new IllegalArgumentException("Invalid abstract type resolution from " + type + " to " + next + ": latter is not a subtype of former");
            }
            type = next;
        }
        return type;
    }

    private JavaType _mapAbstractType2(DeserializationConfig config, JavaType type) throws JsonMappingException {
        Class<?> currClass = type.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver resolver : this._factoryConfig.abstractTypeResolvers()) {
                JavaType concrete = resolver.findTypeMapping(config, type);
                if (ClassUtil.rawClass(concrete) == currClass) continue;
                return concrete;
            }
        }
        return null;
    }

    @Override
    public ValueInstantiator findValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        ValueInstantiator instantiator = null;
        AnnotatedClass ac = beanDesc.getClassInfo();
        Object instDef = ctxt.getAnnotationIntrospector().findValueInstantiator(ac);
        if (instDef != null) {
            instantiator = this._valueInstantiatorInstance(config, ac, instDef);
        }
        if (instantiator == null && (instantiator = this._findStdValueInstantiator(config, beanDesc)) == null) {
            instantiator = this._constructDefaultValueInstantiator(ctxt, beanDesc);
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators insts : this._factoryConfig.valueInstantiators()) {
                instantiator = insts.findValueInstantiator(config, beanDesc, instantiator);
                if (instantiator != null) continue;
                ctxt.reportBadTypeDefinition(beanDesc, "Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", insts.getClass().getName());
            }
        }
        if (instantiator.getIncompleteParameter() != null) {
            AnnotatedParameter nonAnnotatedParam = instantiator.getIncompleteParameter();
            AnnotatedWithParams ctor = nonAnnotatedParam.getOwner();
            throw new IllegalArgumentException("Argument #" + nonAnnotatedParam.getIndex() + " of constructor " + ctor + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
        }
        return instantiator;
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        if (beanDesc.getBeanClass() == JsonLocation.class) {
            return new JsonLocationInstantiator();
        }
        return null;
    }

    protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        CreatorCollector creators = new CreatorCollector(beanDesc, ctxt.getConfig());
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        DeserializationConfig config = ctxt.getConfig();
        VisibilityChecker<?> vchecker = config.getDefaultVisibilityChecker(beanDesc.getBeanClass(), beanDesc.getClassInfo());
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorDefs = this._findCreatorsFromProperties(ctxt, beanDesc);
        this._addDeserializerFactoryMethods(ctxt, beanDesc, vchecker, intr, creators, creatorDefs);
        if (beanDesc.getType().isConcrete()) {
            this._addDeserializerConstructors(ctxt, beanDesc, vchecker, intr, creators, creatorDefs);
        }
        return creators.constructValueInstantiator(config);
    }

    protected Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> result = Collections.emptyMap();
        for (BeanPropertyDefinition propDef : beanDesc.findProperties()) {
            Iterator<AnnotatedParameter> it = propDef.getConstructorParameters();
            while (it.hasNext()) {
                AnnotatedParameter param = it.next();
                AnnotatedWithParams owner = param.getOwner();
                BeanPropertyDefinition[] defs = result.get(owner);
                int index = param.getIndex();
                if (defs == null) {
                    if (result.isEmpty()) {
                        result = new LinkedHashMap<AnnotatedWithParams, BeanPropertyDefinition[]>();
                    }
                    defs = new BeanPropertyDefinition[owner.getParameterCount()];
                    result.put(owner, defs);
                } else if (defs[index] != null) {
                    throw new IllegalStateException("Conflict: parameter #" + index + " of " + owner + " bound to more than one property; " + defs[index] + " vs " + propDef);
                }
                defs[index] = propDef;
            }
        }
        return result;
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig config, Annotated annotated, Object instDef) throws JsonMappingException {
        ValueInstantiator inst;
        if (instDef == null) {
            return null;
        }
        if (instDef instanceof ValueInstantiator) {
            return (ValueInstantiator)instDef;
        }
        if (!(instDef instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + instDef.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        Class instClass = (Class)instDef;
        if (ClassUtil.isBogusClass(instClass)) {
            return null;
        }
        if (!ValueInstantiator.class.isAssignableFrom(instClass)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + instClass.getName() + "; expected Class<ValueInstantiator>");
        }
        HandlerInstantiator hi = config.getHandlerInstantiator();
        if (hi != null && (inst = hi.valueInstantiatorInstance(config, annotated, instClass)) != null) {
            return inst;
        }
        return (ValueInstantiator)ClassUtil.createInstance(instClass, config.canOverrideAccessModifiers());
    }

    protected void _addDeserializerConstructors(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams) throws JsonMappingException {
        boolean isNonStaticInnerClass;
        AnnotatedConstructor defaultCtor = beanDesc.findDefaultConstructor();
        if (defaultCtor != null && (!creators.hasDefaultCreator() || this._hasCreatorAnnotation(ctxt, defaultCtor))) {
            creators.setDefaultCreator(defaultCtor);
        }
        if (isNonStaticInnerClass = beanDesc.isNonStaticInnerClass()) {
            return;
        }
        LinkedList<AnnotatedConstructor> implicitCtors = null;
        for (AnnotatedConstructor ctor : beanDesc.getConstructors()) {
            JsonCreator.Mode creatorMode = intr.findCreatorAnnotation(ctxt.getConfig(), ctor);
            boolean isCreator = creatorMode != null && creatorMode != JsonCreator.Mode.DISABLED;
            BeanPropertyDefinition[] propDefs = creatorParams.get(ctor);
            int argCount = ctor.getParameterCount();
            if (argCount == 1) {
                BeanPropertyDefinition argDef = propDefs == null ? null : propDefs[0];
                boolean useProps = this._checkIfCreatorPropertyBased(intr, ctor, argDef, creatorMode);
                if (useProps) {
                    SettableBeanProperty[] properties = new SettableBeanProperty[1];
                    PropertyName name = argDef == null ? null : argDef.getFullName();
                    AnnotatedParameter arg = ctor.getParameter(0);
                    properties[0] = this.constructCreatorProperty(ctxt, beanDesc, name, 0, arg, intr.findInjectableValue(arg));
                    creators.addPropertyCreator(ctor, isCreator, properties);
                    continue;
                }
                this._handleSingleArgumentConstructor(ctxt, beanDesc, vchecker, intr, creators, ctor, isCreator, vchecker.isCreatorVisible(ctor));
                if (argDef == null) continue;
                ((POJOPropertyBuilder)argDef).removeConstructors();
                continue;
            }
            AnnotatedParameter nonAnnotatedParam = null;
            SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
            int explicitNameCount = 0;
            int implicitWithCreatorCount = 0;
            int injectCount = 0;
            for (int i = 0; i < argCount; ++i) {
                PropertyName name;
                AnnotatedParameter param = ctor.getParameter(i);
                BeanPropertyDefinition propDef = propDefs == null ? null : propDefs[i];
                JacksonInject.Value injectId = intr.findInjectableValue(param);
                PropertyName propertyName = name = propDef == null ? null : propDef.getFullName();
                if (propDef != null && propDef.isExplicitlyNamed()) {
                    ++explicitNameCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    continue;
                }
                if (injectId != null) {
                    ++injectCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    continue;
                }
                NameTransformer unwrapper = intr.findUnwrappingNameTransformer(param);
                if (unwrapper != null) {
                    this._reportUnwrappedCreatorProperty(ctxt, beanDesc, param);
                    continue;
                }
                if (isCreator && name != null && !name.isEmpty()) {
                    ++implicitWithCreatorCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    continue;
                }
                if (nonAnnotatedParam != null) continue;
                nonAnnotatedParam = param;
            }
            int namedCount = explicitNameCount + implicitWithCreatorCount;
            if (isCreator || explicitNameCount > 0 || injectCount > 0) {
                if (namedCount + injectCount == argCount) {
                    creators.addPropertyCreator(ctor, isCreator, properties);
                    continue;
                }
                if (explicitNameCount == 0 && injectCount + 1 == argCount) {
                    creators.addDelegatingCreator(ctor, isCreator, properties);
                    continue;
                }
                PropertyName impl = this._findImplicitParamName(nonAnnotatedParam, intr);
                if (impl == null || impl.isEmpty()) {
                    int ix = nonAnnotatedParam.getIndex();
                    throw new IllegalArgumentException("Argument #" + ix + " of constructor " + ctor + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                }
            }
            if (creators.hasDefaultCreator()) continue;
            if (implicitCtors == null) {
                implicitCtors = new LinkedList<AnnotatedConstructor>();
            }
            implicitCtors.add(ctor);
        }
        if (implicitCtors != null && !creators.hasDelegatingCreator() && !creators.hasPropertyBasedCreator()) {
            this._checkImplicitlyNamedConstructors(ctxt, beanDesc, vchecker, intr, creators, implicitCtors);
        }
    }

    protected void _checkImplicitlyNamedConstructors(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, List<AnnotatedConstructor> implicitCtors) throws JsonMappingException {
        AnnotatedConstructor found = null;
        SettableBeanProperty[] foundProps = null;
        block0: for (AnnotatedConstructor ctor : implicitCtors) {
            if (!vchecker.isCreatorVisible(ctor)) continue;
            int argCount = ctor.getParameterCount();
            SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
            for (int i = 0; i < argCount; ++i) {
                AnnotatedParameter param = ctor.getParameter(i);
                PropertyName name = this._findParamName(param, intr);
                if (name == null || name.isEmpty()) continue block0;
                properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, param.getIndex(), param, null);
            }
            if (found != null) {
                found = null;
                break;
            }
            found = ctor;
            foundProps = properties;
        }
        if (found != null) {
            creators.addPropertyCreator(found, false, foundProps);
            BasicBeanDescription bbd = (BasicBeanDescription)beanDesc;
            for (void var13_16 : foundProps) {
                PropertyName pn = var13_16.getFullName();
                if (bbd.hasProperty(pn)) continue;
                SimpleBeanPropertyDefinition newDef = SimpleBeanPropertyDefinition.construct(ctxt.getConfig(), var13_16.getMember(), pn);
                bbd.addProperty(newDef);
            }
        }
    }

    protected boolean _handleSingleArgumentConstructor(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, AnnotatedConstructor ctor, boolean isCreator, boolean isVisible) throws JsonMappingException {
        Class<?> type = ctor.getRawParameterType(0);
        if (type == String.class || type == CharSequence.class) {
            if (isCreator || isVisible) {
                creators.addStringCreator(ctor, isCreator);
            }
            return true;
        }
        if (type == Integer.TYPE || type == Integer.class) {
            if (isCreator || isVisible) {
                creators.addIntCreator(ctor, isCreator);
            }
            return true;
        }
        if (type == Long.TYPE || type == Long.class) {
            if (isCreator || isVisible) {
                creators.addLongCreator(ctor, isCreator);
            }
            return true;
        }
        if (type == Double.TYPE || type == Double.class) {
            if (isCreator || isVisible) {
                creators.addDoubleCreator(ctor, isCreator);
            }
            return true;
        }
        if (type == Boolean.TYPE || type == Boolean.class) {
            if (isCreator || isVisible) {
                creators.addBooleanCreator(ctor, isCreator);
            }
            return true;
        }
        if (isCreator) {
            creators.addDelegatingCreator(ctor, isCreator, null);
            return true;
        }
        return false;
    }

    protected void _addDeserializerFactoryMethods(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
            JsonCreator.Mode creatorMode = intr.findCreatorAnnotation(ctxt.getConfig(), factory);
            boolean isCreator = creatorMode != null && creatorMode != JsonCreator.Mode.DISABLED;
            int argCount = factory.getParameterCount();
            if (argCount == 0) {
                if (!isCreator) continue;
                creators.setDefaultCreator(factory);
                continue;
            }
            BeanPropertyDefinition[] propDefs = creatorParams.get(factory);
            if (argCount == 1) {
                BeanPropertyDefinition argDef = propDefs == null ? null : propDefs[0];
                boolean useProps = this._checkIfCreatorPropertyBased(intr, factory, argDef, creatorMode);
                if (!useProps) {
                    this._handleSingleArgumentFactory(config, beanDesc, vchecker, intr, creators, factory, isCreator);
                    if (argDef == null) continue;
                    ((POJOPropertyBuilder)argDef).removeConstructors();
                    continue;
                }
            } else if (!isCreator) continue;
            AnnotatedParameter nonAnnotatedParam = null;
            SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
            int implicitNameCount = 0;
            int explicitNameCount = 0;
            int injectCount = 0;
            for (int i = 0; i < argCount; ++i) {
                PropertyName name;
                AnnotatedParameter param = factory.getParameter(i);
                BeanPropertyDefinition propDef = propDefs == null ? null : propDefs[i];
                JacksonInject.Value injectable = intr.findInjectableValue(param);
                PropertyName propertyName = name = propDef == null ? null : propDef.getFullName();
                if (propDef != null && propDef.isExplicitlyNamed()) {
                    ++explicitNameCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
                    continue;
                }
                if (injectable != null) {
                    ++injectCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
                    continue;
                }
                NameTransformer unwrapper = intr.findUnwrappingNameTransformer(param);
                if (unwrapper != null) {
                    this._reportUnwrappedCreatorProperty(ctxt, beanDesc, param);
                    continue;
                }
                if (isCreator && name != null && !name.isEmpty()) {
                    ++implicitNameCount;
                    properties[i] = this.constructCreatorProperty(ctxt, beanDesc, name, i, param, injectable);
                    continue;
                }
                if (nonAnnotatedParam != null) continue;
                nonAnnotatedParam = param;
            }
            int namedCount = explicitNameCount + implicitNameCount;
            if (!isCreator && explicitNameCount <= 0 && injectCount <= 0) continue;
            if (namedCount + injectCount == argCount) {
                creators.addPropertyCreator(factory, isCreator, properties);
                continue;
            }
            if (explicitNameCount == 0 && injectCount + 1 == argCount) {
                creators.addDelegatingCreator(factory, isCreator, properties);
                continue;
            }
            throw new IllegalArgumentException("Argument #" + nonAnnotatedParam.getIndex() + " of factory method " + factory + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
        }
    }

    protected boolean _handleSingleArgumentFactory(DeserializationConfig config, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, AnnotatedMethod factory, boolean isCreator) throws JsonMappingException {
        Class<?> type = factory.getRawParameterType(0);
        if (type == String.class || type == CharSequence.class) {
            if (isCreator || vchecker.isCreatorVisible(factory)) {
                creators.addStringCreator(factory, isCreator);
            }
            return true;
        }
        if (type == Integer.TYPE || type == Integer.class) {
            if (isCreator || vchecker.isCreatorVisible(factory)) {
                creators.addIntCreator(factory, isCreator);
            }
            return true;
        }
        if (type == Long.TYPE || type == Long.class) {
            if (isCreator || vchecker.isCreatorVisible(factory)) {
                creators.addLongCreator(factory, isCreator);
            }
            return true;
        }
        if (type == Double.TYPE || type == Double.class) {
            if (isCreator || vchecker.isCreatorVisible(factory)) {
                creators.addDoubleCreator(factory, isCreator);
            }
            return true;
        }
        if (type == Boolean.TYPE || type == Boolean.class) {
            if (isCreator || vchecker.isCreatorVisible(factory)) {
                creators.addBooleanCreator(factory, isCreator);
            }
            return true;
        }
        if (isCreator) {
            creators.addDelegatingCreator(factory, isCreator, null);
            return true;
        }
        return false;
    }

    protected void _reportUnwrappedCreatorProperty(DeserializationContext ctxt, BeanDescription beanDesc, AnnotatedParameter param) throws JsonMappingException {
        ctxt.reportBadDefinition(beanDesc.getType(), String.format("Cannot define Creator parameter %d as `@JsonUnwrapped`: combination not yet supported", param.getIndex()));
    }

    protected SettableBeanProperty constructCreatorProperty(DeserializationContext ctxt, BeanDescription beanDesc, PropertyName name, int index, AnnotatedParameter param, JacksonInject.Value injectable) throws JsonMappingException {
        PropertyMetadata metadata;
        DeserializationConfig config = ctxt.getConfig();
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr == null) {
            metadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        } else {
            Boolean b = intr.hasRequiredMarker(param);
            String desc = intr.findPropertyDescription(param);
            Integer idx = intr.findPropertyIndex(param);
            String def = intr.findPropertyDefaultValue(param);
            metadata = PropertyMetadata.construct(b, desc, idx, def);
        }
        JavaType type = this.resolveMemberAndTypeAnnotations(ctxt, param, param.getType());
        BeanProperty.Std property = new BeanProperty.Std(name, type, intr.findWrapperName(param), param, metadata);
        TypeDeserializer typeDeser = (TypeDeserializer)type.getTypeHandler();
        if (typeDeser == null) {
            typeDeser = this.findTypeDeserializer(config, type);
        }
        Object injectableValueId = injectable == null ? null : injectable.getId();
        SettableBeanProperty prop = new CreatorProperty(name, type, property.getWrapperName(), typeDeser, beanDesc.getClassAnnotations(), param, index, injectableValueId, metadata);
        JsonDeserializer<?> deser = this.findDeserializerFromAnnotation(ctxt, param);
        if (deser == null) {
            deser = (JsonDeserializer<?>)type.getValueHandler();
        }
        if (deser != null) {
            deser = ctxt.handlePrimaryContextualization(deser, prop, type);
            prop = ((SettableBeanProperty)prop).withValueDeserializer(deser);
        }
        return prop;
    }

    protected PropertyName _findParamName(AnnotatedParameter param, AnnotationIntrospector intr) {
        if (param != null && intr != null) {
            PropertyName name = intr.findNameForDeserialization(param);
            if (name != null) {
                return name;
            }
            String str = intr.findImplicitPropertyName(param);
            if (str != null && !str.isEmpty()) {
                return PropertyName.construct(str);
            }
        }
        return null;
    }

    protected PropertyName _findImplicitParamName(AnnotatedParameter param, AnnotationIntrospector intr) {
        String str = intr.findImplicitPropertyName(param);
        if (str != null && !str.isEmpty()) {
            return PropertyName.construct(str);
        }
        return null;
    }

    protected boolean _checkIfCreatorPropertyBased(AnnotationIntrospector intr, AnnotatedWithParams creator, BeanPropertyDefinition propDef, JsonCreator.Mode creatorMode) {
        String implName;
        if (creatorMode == JsonCreator.Mode.PROPERTIES) {
            return true;
        }
        if (creatorMode == JsonCreator.Mode.DELEGATING) {
            return false;
        }
        if (propDef != null && propDef.isExplicitlyNamed() || intr.findInjectableValue(creator.getParameter(0)) != null) {
            return true;
        }
        return propDef != null && (implName = propDef.getName()) != null && !implName.isEmpty() && propDef.couldSerialize();
    }

    @Override
    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext ctxt, ArrayType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer deser;
        DeserializationConfig config = ctxt.getConfig();
        JavaType elemType = type.getContentType();
        JsonDeserializer contentDeser = (JsonDeserializer)elemType.getValueHandler();
        TypeDeserializer elemTypeDeser = (TypeDeserializer)elemType.getTypeHandler();
        if (elemTypeDeser == null) {
            elemTypeDeser = this.findTypeDeserializer(config, elemType);
        }
        if ((deser = this._findCustomArrayDeserializer(type, config, beanDesc, elemTypeDeser, contentDeser)) == null) {
            if (contentDeser == null) {
                Class<?> raw = elemType.getRawClass();
                if (elemType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(raw);
                }
                if (raw == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            deser = new ObjectArrayDeserializer((JavaType)type, contentDeser, elemTypeDeser);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyArrayDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext ctxt, CollectionType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer deser;
        JavaType contentType = type.getContentType();
        JsonDeserializer contentDeser = (JsonDeserializer)contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = this.findTypeDeserializer(config, contentType);
        }
        if ((deser = this._findCustomCollectionDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser)) == null) {
            Class<?> collectionClass = type.getRawClass();
            if (contentDeser == null && EnumSet.class.isAssignableFrom(collectionClass)) {
                deser = new EnumSetDeserializer(contentType, null);
            }
        }
        if (deser == null) {
            if (type.isInterface() || type.isAbstract()) {
                CollectionType implType = this._mapAbstractCollectionType(type, config);
                if (implType == null) {
                    if (type.getTypeHandler() == null) {
                        throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Collection type " + type);
                    }
                    deser = AbstractDeserializer.constructForNonPOJO(beanDesc);
                } else {
                    type = implType;
                    beanDesc = config.introspectForCreation(type);
                }
            }
            if (deser == null) {
                ValueInstantiator inst = this.findValueInstantiator(ctxt, beanDesc);
                if (!inst.canCreateUsingDefault() && type.hasRawClass(ArrayBlockingQueue.class)) {
                    return new ArrayBlockingQueueDeserializer(type, contentDeser, contentTypeDeser, inst);
                }
                deser = contentType.hasRawClass(String.class) ? new StringCollectionDeserializer((JavaType)type, contentDeser, inst) : new CollectionDeserializer(type, contentDeser, contentTypeDeser, inst);
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyCollectionDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    protected CollectionType _mapAbstractCollectionType(JavaType type, DeserializationConfig config) {
        Class<Object> collectionClass = type.getRawClass();
        if ((collectionClass = _collectionFallbacks.get(collectionClass.getName())) == null) {
            return null;
        }
        return (CollectionType)config.constructSpecializedType(type, collectionClass);
    }

    @Override
    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext ctxt, CollectionLikeType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser;
        JavaType contentType = type.getContentType();
        JsonDeserializer contentDeser = (JsonDeserializer)contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = this.findTypeDeserializer(config, contentType);
        }
        if ((deser = this._findCustomCollectionLikeDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser)) != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyCollectionLikeDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public JsonDeserializer<?> createMapDeserializer(DeserializationContext ctxt, MapType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer deser;
        DeserializationConfig config = ctxt.getConfig();
        JavaType keyType = type.getKeyType();
        JavaType contentType = type.getContentType();
        JsonDeserializer contentDeser = (JsonDeserializer)contentType.getValueHandler();
        KeyDeserializer keyDes = (KeyDeserializer)keyType.getValueHandler();
        TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = this.findTypeDeserializer(config, contentType);
        }
        if ((deser = this._findCustomMapDeserializer(type, config, beanDesc, keyDes, contentTypeDeser, contentDeser)) == null) {
            ValueInstantiator inst;
            Class<Object> mapClass = type.getRawClass();
            if (EnumMap.class.isAssignableFrom(mapClass)) {
                inst = mapClass == EnumMap.class ? null : this.findValueInstantiator(ctxt, beanDesc);
                Class<?> kt = keyType.getRawClass();
                if (kt == null || !kt.isEnum()) {
                    throw new IllegalArgumentException("Cannot construct EnumMap; generic (key) type not available");
                }
                deser = new EnumMapDeserializer(type, inst, null, contentDeser, contentTypeDeser, null);
            }
            if (deser == null) {
                if (type.isInterface() || type.isAbstract()) {
                    Class<? extends Map> fallback = _mapFallbacks.get(mapClass.getName());
                    if (fallback != null) {
                        mapClass = fallback;
                        type = (MapType)config.constructSpecializedType(type, mapClass);
                        beanDesc = config.introspectForCreation(type);
                    } else {
                        if (type.getTypeHandler() == null) {
                            throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Map type " + type);
                        }
                        deser = AbstractDeserializer.constructForNonPOJO(beanDesc);
                    }
                }
                if (deser == null) {
                    inst = this.findValueInstantiator(ctxt, beanDesc);
                    MapDeserializer md = new MapDeserializer(type, inst, keyDes, contentDeser, contentTypeDeser);
                    JsonIgnoreProperties.Value ignorals = config.getDefaultPropertyIgnorals(Map.class, beanDesc.getClassInfo());
                    Set<String> ignored = ignorals == null ? null : ignorals.findIgnoredForDeserialization();
                    md.setIgnorableProperties(ignored);
                    deser = md;
                }
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyMapDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext ctxt, MapLikeType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser;
        JavaType keyType = type.getKeyType();
        JavaType contentType = type.getContentType();
        DeserializationConfig config = ctxt.getConfig();
        JsonDeserializer contentDeser = (JsonDeserializer)contentType.getValueHandler();
        KeyDeserializer keyDes = (KeyDeserializer)keyType.getValueHandler();
        TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = this.findTypeDeserializer(config, contentType);
        }
        if ((deser = this._findCustomMapLikeDeserializer(type, config, beanDesc, keyDes, contentTypeDeser, contentDeser)) != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyMapLikeDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        Class<?> enumClass = type.getRawClass();
        JsonDeserializer deser = this._findCustomEnumDeserializer(enumClass, config, beanDesc);
        if (deser == null) {
            ValueInstantiator valueInstantiator = this._constructDefaultValueInstantiator(ctxt, beanDesc);
            SettableBeanProperty[] creatorProps = valueInstantiator == null ? null : valueInstantiator.getFromObjectArguments(ctxt.getConfig());
            for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
                if (!this._hasCreatorAnnotation(ctxt, factory)) continue;
                if (factory.getParameterCount() == 0) {
                    deser = EnumDeserializer.deserializerForNoArgsCreator(config, enumClass, factory);
                    break;
                }
                Class<?> returnType = factory.getRawReturnType();
                if (!returnType.isAssignableFrom(enumClass)) continue;
                deser = EnumDeserializer.deserializerForCreator(config, enumClass, factory, valueInstantiator, creatorProps);
                break;
            }
            if (deser == null) {
                deser = new EnumDeserializer(this.constructEnumResolver(enumClass, config, beanDesc.findJsonValueAccessor()), (Boolean)config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyEnumDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig config, JavaType nodeType, BeanDescription beanDesc) throws JsonMappingException {
        Class<?> nodeClass = nodeType.getRawClass();
        JsonDeserializer<?> custom = this._findCustomTreeNodeDeserializer(nodeClass, config, beanDesc);
        if (custom != null) {
            return custom;
        }
        return JsonNodeDeserializer.getDeserializer(nodeClass);
    }

    @Override
    public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext ctxt, ReferenceType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser;
        JavaType contentType = type.getContentType();
        JsonDeserializer contentDeser = (JsonDeserializer)contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer)contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = this.findTypeDeserializer(config, contentType);
        }
        if ((deser = this._findCustomReferenceDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser)) == null && type.isTypeOrSubTypeOf(AtomicReference.class)) {
            Class<?> rawType = type.getRawClass();
            ValueInstantiator inst = rawType == AtomicReference.class ? null : this.findValueInstantiator(ctxt, beanDesc);
            return new AtomicReferenceDeserializer(type, inst, contentTypeDeser, contentDeser);
        }
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyReferenceDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    @Override
    public TypeDeserializer findTypeDeserializer(DeserializationConfig config, JavaType baseType) throws JsonMappingException {
        JavaType defaultType;
        BeanDescription bean = config.introspectClassAnnotations(baseType.getRawClass());
        AnnotatedClass ac = bean.getClassInfo();
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        TypeResolverBuilder<?> b = ai.findTypeResolver(config, ac, baseType);
        Collection<NamedType> subtypes = null;
        if (b == null) {
            b = config.getDefaultTyper(baseType);
            if (b == null) {
                return null;
            }
        } else {
            subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, ac);
        }
        if (b.getDefaultImpl() == null && baseType.isAbstract() && (defaultType = this.mapAbstractType(config, baseType)) != null && !defaultType.hasRawClass(baseType.getRawClass())) {
            b = b.defaultImpl(defaultType.getRawClass());
        }
        return b.buildTypeDeserializer(config, baseType, subtypes);
    }

    protected JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(type, ctxt.getConfig(), beanDesc);
    }

    @Override
    public KeyDeserializer createKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        KeyDeserializer deser = null;
        if (this._factoryConfig.hasKeyDeserializers()) {
            KeyDeserializers d;
            BeanDescription beanDesc = config.introspectClassAnnotations(type.getRawClass());
            Iterator<KeyDeserializers> i$ = this._factoryConfig.keyDeserializers().iterator();
            while (i$.hasNext() && (deser = (d = i$.next()).findKeyDeserializer(type, config, beanDesc)) == null) {
            }
        }
        if (deser == null) {
            deser = type.isEnumType() ? this._createEnumKeyDeserializer(ctxt, type) : StdKeyDeserializers.findStringBasedKeyDeserializer(config, type);
        }
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyKeyDeserializer(config, type, deser);
            }
        }
        return deser;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        Class<?> enumClass = type.getRawClass();
        Object beanDesc = config.introspect(type);
        KeyDeserializer des = this.findKeyDeserializerFromAnnotation(ctxt, ((BeanDescription)beanDesc).getClassInfo());
        if (des != null) {
            return des;
        }
        JsonDeserializer<?> custom = this._findCustomEnumDeserializer(enumClass, config, (BeanDescription)beanDesc);
        if (custom != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, custom);
        }
        JsonDeserializer<Object> valueDesForKey = this.findDeserializerFromAnnotation(ctxt, ((BeanDescription)beanDesc).getClassInfo());
        if (valueDesForKey != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, valueDesForKey);
        }
        EnumResolver enumRes = this.constructEnumResolver(enumClass, config, ((BeanDescription)beanDesc).findJsonValueAccessor());
        for (AnnotatedMethod factory : ((BeanDescription)beanDesc).getFactoryMethods()) {
            Class<?> returnType;
            if (!this._hasCreatorAnnotation(ctxt, factory)) continue;
            int argCount = factory.getParameterCount();
            if (argCount == 1 && (returnType = factory.getRawReturnType()).isAssignableFrom(enumClass)) {
                if (factory.getRawParameterType(0) != String.class) {
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + factory + ") not suitable, must be java.lang.String");
                }
                if (config.canOverrideAccessModifiers()) {
                    ClassUtil.checkAndFixAccess(factory.getMember(), ctxt.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                }
                return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes, factory);
            }
            throw new IllegalArgumentException("Unsuitable method (" + factory + ") decorated with @JsonCreator (for Enum type " + enumClass.getName() + ")");
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig config, JavaType baseType, AnnotatedMember annotated) throws JsonMappingException {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        TypeResolverBuilder<?> b = ai.findPropertyTypeResolver(config, annotated, baseType);
        if (b == null) {
            return this.findTypeDeserializer(config, baseType);
        }
        Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, annotated, baseType);
        return b.buildTypeDeserializer(config, baseType, subtypes);
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig config, JavaType containerType, AnnotatedMember propertyEntity) throws JsonMappingException {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        TypeResolverBuilder<?> b = ai.findPropertyContentTypeResolver(config, propertyEntity, containerType);
        JavaType contentType = containerType.getContentType();
        if (b == null) {
            return this.findTypeDeserializer(config, contentType);
        }
        Collection<NamedType> subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, propertyEntity, contentType);
        return b.buildTypeDeserializer(config, contentType, subtypes);
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser;
        Class<?> rawType = type.getRawClass();
        if (rawType == CLASS_OBJECT) {
            JavaType mt;
            JavaType lt;
            DeserializationConfig config = ctxt.getConfig();
            if (this._factoryConfig.hasAbstractTypeResolvers()) {
                lt = this._findRemappedType(config, List.class);
                mt = this._findRemappedType(config, Map.class);
            } else {
                mt = null;
                lt = null;
            }
            return new UntypedObjectDeserializer(lt, mt);
        }
        if (rawType == CLASS_STRING || rawType == CLASS_CHAR_BUFFER) {
            return StringDeserializer.instance;
        }
        if (rawType == CLASS_ITERABLE) {
            TypeFactory tf = ctxt.getTypeFactory();
            JavaType[] tps = tf.findTypeParameters(type, CLASS_ITERABLE);
            JavaType elemType = tps == null || tps.length != 1 ? TypeFactory.unknownType() : tps[0];
            CollectionType ct = tf.constructCollectionType(Collection.class, elemType);
            return this.createCollectionDeserializer(ctxt, ct, beanDesc);
        }
        if (rawType == CLASS_MAP_ENTRY) {
            JavaType kt = type.containedTypeOrUnknown(0);
            JavaType vt = type.containedTypeOrUnknown(1);
            TypeDeserializer vts = (TypeDeserializer)vt.getTypeHandler();
            if (vts == null) {
                vts = this.findTypeDeserializer(ctxt.getConfig(), vt);
            }
            JsonDeserializer valueDeser = (JsonDeserializer)vt.getValueHandler();
            KeyDeserializer keyDes = (KeyDeserializer)kt.getValueHandler();
            return new MapEntryDeserializer(type, keyDes, (JsonDeserializer<Object>)valueDeser, vts);
        }
        String clsName = rawType.getName();
        if (rawType.isPrimitive() || clsName.startsWith("java.")) {
            deser = NumberDeserializers.find(rawType, clsName);
            if (deser == null) {
                deser = DateDeserializers.find(rawType, clsName);
            }
            if (deser != null) {
                return deser;
            }
        }
        if (rawType == TokenBuffer.class) {
            return new TokenBufferDeserializer();
        }
        deser = this.findOptionalStdDeserializer(ctxt, type, beanDesc);
        if (deser != null) {
            return deser;
        }
        return JdkDeserializers.find(rawType, clsName);
    }

    protected JavaType _findRemappedType(DeserializationConfig config, Class<?> rawType) throws JsonMappingException {
        JavaType type = this.mapAbstractType(config, config.constructType(rawType));
        return type == null || type.hasRawClass(rawType) ? null : type;
    }

    protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findTreeNodeDeserializer(type, config, beanDesc);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findReferenceDeserializer(type, config, beanDesc, contentTypeDeserializer, contentDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<Object> deser = d.findBeanDeserializer(type, config, beanDesc);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findArrayDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findCollectionDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findCollectionLikeDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findEnumDeserializer(type, config, beanDesc);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findMapDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
            if (deser == null) continue;
            return deser;
        }
        return null;
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
        Object deserDef;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null && (deserDef = intr.findDeserializer(ann)) != null) {
            return ctxt.deserializerInstance(ann, deserDef);
        }
        return null;
    }

    protected KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
        Object deserDef;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null && (deserDef = intr.findKeyDeserializer(ann)) != null) {
            return ctxt.keyDeserializerInstance(ann, deserDef);
        }
        return null;
    }

    protected JsonDeserializer<Object> findContentDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
        Object deserDef;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null && (deserDef = intr.findContentDeserializer(ann)) != null) {
            return ctxt.deserializerInstance(ann, deserDef);
        }
        return null;
    }

    protected JavaType resolveMemberAndTypeAnnotations(DeserializationContext ctxt, AnnotatedMember member, JavaType type) throws JsonMappingException {
        TypeDeserializer valueTypeDeser;
        Object kdDef;
        KeyDeserializer kd;
        JavaType keyType;
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr == null) {
            return type;
        }
        if (type.isMapLikeType() && (keyType = type.getKeyType()) != null && (kd = ctxt.keyDeserializerInstance(member, kdDef = intr.findKeyDeserializer(member))) != null) {
            type = ((MapLikeType)type).withKeyValueHandler(kd);
            keyType = type.getKeyType();
        }
        if (type.hasContentType()) {
            TypeDeserializer contentTypeDeser;
            Object cdDef = intr.findContentDeserializer(member);
            JsonDeserializer<Object> cd = ctxt.deserializerInstance(member, cdDef);
            if (cd != null) {
                type = type.withContentValueHandler(cd);
            }
            if ((contentTypeDeser = this.findPropertyContentTypeDeserializer(ctxt.getConfig(), type, member)) != null) {
                type = type.withContentTypeHandler(contentTypeDeser);
            }
        }
        if ((valueTypeDeser = this.findPropertyTypeDeserializer(ctxt.getConfig(), type, member)) != null) {
            type = type.withTypeHandler(valueTypeDeser);
        }
        type = intr.refineDeserializationType(ctxt.getConfig(), member, type);
        return type;
    }

    protected EnumResolver constructEnumResolver(Class<?> enumClass, DeserializationConfig config, AnnotatedMember jsonValueAccessor) {
        if (jsonValueAccessor != null) {
            if (config.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(jsonValueAccessor.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return EnumResolver.constructUnsafeUsingMethod(enumClass, jsonValueAccessor, config.getAnnotationIntrospector());
        }
        return EnumResolver.constructUnsafe(enumClass, config.getAnnotationIntrospector());
    }

    protected boolean _hasCreatorAnnotation(DeserializationContext ctxt, Annotated ann) {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null) {
            JsonCreator.Mode mode = intr.findCreatorAnnotation(ctxt.getConfig(), ann);
            return mode != null && mode != JsonCreator.Mode.DISABLED;
        }
        return false;
    }

    @Deprecated
    protected JavaType modifyTypeByAnnotation(DeserializationContext ctxt, Annotated a, JavaType type) throws JsonMappingException {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr == null) {
            return type;
        }
        return intr.refineDeserializationType(ctxt.getConfig(), a, type);
    }

    @Deprecated
    protected JavaType resolveType(DeserializationContext ctxt, BeanDescription beanDesc, JavaType type, AnnotatedMember member) throws JsonMappingException {
        return this.resolveMemberAndTypeAnnotations(ctxt, member, type);
    }

    @Deprecated
    protected AnnotatedMethod _findJsonValueFor(DeserializationConfig config, JavaType enumType) {
        if (enumType == null) {
            return null;
        }
        Object beanDesc = config.introspect(enumType);
        return ((BeanDescription)beanDesc).findJsonValueMethod();
    }

    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(NavigableMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }
}

