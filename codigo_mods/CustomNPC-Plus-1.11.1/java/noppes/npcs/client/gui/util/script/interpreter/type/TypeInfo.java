/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import noppes.npcs.client.gui.util.script.interpreter.ObjectLiteralParser;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodSignature;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.IntersectionTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.OverloadSelector;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class TypeInfo {
    public static final TypeInfo VOID = TypeInfo.fromPrimitive("void");
    public static final TypeInfo BOOLEAN = TypeInfo.fromPrimitive("boolean");
    public static final TypeInfo STRING = TypeInfo.fromClass(String.class);
    static TypeInfo jsString = null;
    public static final TypeInfo OBJECT = TypeInfo.fromClass(Object.class);
    static TypeInfo jsObject = null;
    public static final TypeInfo NULL = TypeInfo.unresolved("null", "<null>");
    public static final TypeInfo ANY = new TypeInfo("any", "any", "", Kind.CLASS, null, true, null).setPrimitive(true);
    public static final TypeInfo NUMBER = new TypeInfo("number", "number", "", Kind.CLASS, Double.TYPE, true, null).setPrimitive(true);
    public static final Supplier<TypeInfo> OBJECT_LITERAL = () -> new TypeInfo("ObjectLiteral", "ObjectLiteral", "", Kind.CLASS, null, true, null);
    private final String simpleName;
    private final String fullName;
    private final String packageName;
    private final Kind kind;
    private final Class<?> javaClass;
    private final boolean resolved;
    private final TypeInfo enclosingType;
    private boolean isPrimitive;
    private boolean isObjectLiteral;
    private String typeParameterName;
    private TypeInfo boundType;
    private final JSTypeInfo jsTypeInfo;
    private final List<TypeParamInfo> typeParams = new ArrayList<TypeParamInfo>();
    private List<TypeInfo> appliedTypeArgs = new ArrayList<TypeInfo>();
    private TypeInfo rawType;
    private final TypeInfo elementType;
    private final List<FieldInfo> syntheticFields = new ArrayList<FieldInfo>();
    private final List<MethodInfo> syntheticMethods = new ArrayList<MethodInfo>();
    private JSDocInfo jsDocInfo;
    private MethodInfo cachedSAM;
    private boolean samCacheResolved = false;

    public static TypeInfo string() {
        return TypeChecker.isJavaScriptMode() && jsString != null ? jsString : STRING;
    }

    public static TypeInfo object() {
        return TypeChecker.isJavaScriptMode() && jsObject != null ? jsObject : OBJECT;
    }

    private TypeInfo(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass, boolean resolved, TypeInfo enclosingType) {
        this(simpleName, fullName, packageName, kind, javaClass, resolved, enclosingType, null, null, null, null);
    }

    private TypeInfo(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass, boolean resolved, TypeInfo enclosingType, JSTypeInfo jsTypeInfo) {
        this(simpleName, fullName, packageName, kind, javaClass, resolved, enclosingType, jsTypeInfo, null, null, null);
    }

    private TypeInfo(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass, boolean resolved, TypeInfo enclosingType, JSTypeInfo jsTypeInfo, TypeInfo rawType, List<TypeInfo> appliedTypeArgs, TypeInfo elementType) {
        this.simpleName = simpleName;
        this.fullName = fullName;
        this.packageName = packageName;
        this.kind = kind;
        this.javaClass = javaClass;
        this.resolved = resolved;
        this.enclosingType = enclosingType;
        this.jsTypeInfo = jsTypeInfo;
        this.rawType = rawType;
        this.elementType = elementType;
        if (appliedTypeArgs != null) {
            this.appliedTypeArgs.addAll(appliedTypeArgs);
        }
    }

    protected TypeInfo(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass, boolean resolved, TypeInfo enclosingType, boolean subclass) {
        this.simpleName = simpleName;
        this.fullName = fullName;
        this.packageName = packageName;
        this.kind = kind;
        this.javaClass = javaClass;
        this.resolved = resolved;
        this.enclosingType = enclosingType;
        this.jsTypeInfo = null;
        this.rawType = null;
        this.elementType = null;
    }

    public static TypeInfo resolved(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass) {
        return new TypeInfo(simpleName, fullName, packageName, kind, javaClass, true, null);
    }

    public static TypeInfo resolvedInner(String simpleName, String fullName, String packageName, Kind kind, Class<?> javaClass, TypeInfo enclosing) {
        return new TypeInfo(simpleName, fullName, packageName, kind, javaClass, true, enclosing);
    }

    public static TypeInfo unresolved(String simpleName, String fullPath) {
        int lastDot = fullPath.lastIndexOf(46);
        String pkg = lastDot > 0 ? fullPath.substring(0, lastDot) : "";
        return new TypeInfo(simpleName, fullPath, pkg, Kind.UNKNOWN, null, false, null);
    }

    public static TypeInfo typeParameter(String paramName) {
        TypeInfo ti = new TypeInfo(paramName, paramName, "", Kind.CLASS, Object.class, true, null);
        ti.typeParameterName = paramName;
        return ti;
    }

    public static TypeInfo typeParameter(String paramName, TypeInfo boundType) {
        Kind kind;
        Class<Object> javaClass = boundType != null && boundType.javaClass != null ? boundType.javaClass : Object.class;
        Kind kind2 = kind = boundType != null ? boundType.kind : Kind.CLASS;
        if (boundType instanceof IntersectionTypeInfo) {
            final IntersectionTypeInfo intersection = (IntersectionTypeInfo)boundType;
            TypeInfo ti = new TypeInfo(paramName, paramName, "", kind, javaClass, true, null){

                @Override
                public List<MethodInfo> getAllMethods() {
                    ArrayList<MethodInfo> result = new ArrayList<MethodInfo>(super.getAllMethods());
                    for (TypeInfo bound : intersection.getAdditionalBounds()) {
                        result.addAll(bound.getAllMethods());
                    }
                    return result;
                }

                @Override
                public List<FieldInfo> getAllFields() {
                    ArrayList<FieldInfo> result = new ArrayList<FieldInfo>(super.getAllFields());
                    for (TypeInfo bound : intersection.getAdditionalBounds()) {
                        result.addAll(bound.getAllFields());
                    }
                    return result;
                }
            };
            ti.typeParameterName = paramName;
            ti.boundType = boundType;
            return ti;
        }
        TypeInfo ti = new TypeInfo(paramName, paramName, "", kind, javaClass, true, null);
        ti.typeParameterName = paramName;
        ti.boundType = boundType;
        return ti;
    }

    public static TypeInfo typeParameter(String paramName, TypeParamInfo typeParam) {
        List<TypeInfo> additionalBounds;
        if (typeParam == null) {
            return TypeInfo.typeParameter(paramName);
        }
        TypeInfo primaryBound = typeParam.getBoundTypeInfo();
        if (primaryBound == null) {
            typeParam.resolveBoundType();
            primaryBound = typeParam.getBoundTypeInfo();
        }
        TypeInfo effectiveBound = primaryBound != null && primaryBound.isResolved() ? ((additionalBounds = typeParam.getAdditionalBoundTypes()) != null && !additionalBounds.isEmpty() ? IntersectionTypeInfo.of(primaryBound, additionalBounds) : primaryBound) : null;
        return TypeInfo.typeParameter(paramName, effectiveBound);
    }

    public boolean isTypeParameter() {
        return this.typeParameterName != null;
    }

    public String getTypeParameterName() {
        return this.typeParameterName;
    }

    public TypeInfo getBoundType() {
        return this.boundType;
    }

    public static TypeInfo fromClass(Class<?> clazz) {
        int lastDot;
        if (clazz == null) {
            return null;
        }
        Kind kind = clazz.isInterface() ? Kind.INTERFACE : (clazz.isEnum() ? Kind.ENUM : Kind.CLASS);
        String fullName = clazz.getName();
        String simpleName = clazz.getSimpleName();
        Package pkg = clazz.getPackage();
        String packageName = "";
        if (pkg != null) {
            packageName = pkg.getName();
        } else if (!fullName.equals(simpleName) && (lastDot = fullName.lastIndexOf(46)) > 0) {
            packageName = fullName.substring(0, lastDot);
        }
        TypeInfo enclosing = null;
        if (clazz.getEnclosingClass() != null) {
            enclosing = TypeInfo.fromClass(clazz.getEnclosingClass());
        }
        if (clazz.isArray()) {
            return TypeInfo.arrayOf(TypeInfo.fromClass(clazz.getComponentType()));
        }
        return new TypeInfo(simpleName, fullName, packageName, kind, clazz, true, enclosing);
    }

    public static TypeInfo fromGenericType(Type type) {
        if (type == null) {
            return null;
        }
        if (type instanceof Class) {
            return TypeInfo.fromClass((Class)type);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType)type;
            Type rawType = paramType.getRawType();
            if (!(rawType instanceof Class)) {
                return rawType != null ? TypeInfo.fromGenericType(rawType) : null;
            }
            TypeInfo rawTypeInfo = TypeInfo.fromClass((Class)rawType);
            Type[] typeArgs = paramType.getActualTypeArguments();
            ArrayList<TypeInfo> appliedArgs = new ArrayList<TypeInfo>(typeArgs.length);
            for (Type arg : typeArgs) {
                TypeInfo argInfo = TypeInfo.fromGenericType(arg);
                if (argInfo == null) continue;
                appliedArgs.add(argInfo);
            }
            if (!appliedArgs.isEmpty()) {
                return rawTypeInfo.parameterize(appliedArgs);
            }
            return rawTypeInfo;
        }
        if (type instanceof GenericArrayType) {
            GenericArrayType arrayType = (GenericArrayType)type;
            TypeInfo elementType = TypeInfo.fromGenericType(arrayType.getGenericComponentType());
            if (elementType != null) {
                return TypeInfo.arrayOf(elementType);
            }
            return TypeInfo.fromClass(Object[].class);
        }
        if (type instanceof TypeVariable) {
            TypeVariable typeVar = (TypeVariable)type;
            String varName = typeVar.getName();
            Type[] bounds = typeVar.getBounds();
            if (bounds != null && bounds.length > 0 && bounds[0] != Object.class) {
                return TypeInfo.typeParameter(varName, TypeInfo.fromGenericType(bounds[0]));
            }
            return TypeInfo.typeParameter(varName);
        }
        if (type instanceof WildcardType) {
            WildcardType wildcard = (WildcardType)type;
            Type[] upperBounds = wildcard.getUpperBounds();
            if (upperBounds.length > 0 && upperBounds[0] != Object.class) {
                return TypeInfo.fromGenericType(upperBounds[0]);
            }
            return TypeInfo.fromClass(Object.class);
        }
        return null;
    }

    public static TypeInfo fromPrimitive(String typeName) {
        Class<Object> primitiveClass = null;
        switch (typeName) {
            case "boolean": {
                primitiveClass = Boolean.TYPE;
                break;
            }
            case "byte": {
                primitiveClass = Byte.TYPE;
                break;
            }
            case "char": {
                primitiveClass = Character.TYPE;
                break;
            }
            case "short": {
                primitiveClass = Short.TYPE;
                break;
            }
            case "int": {
                primitiveClass = Integer.TYPE;
                break;
            }
            case "long": {
                primitiveClass = Long.TYPE;
                break;
            }
            case "float": {
                primitiveClass = Float.TYPE;
                break;
            }
            case "double": {
                primitiveClass = Double.TYPE;
                break;
            }
            case "void": {
                primitiveClass = Void.TYPE;
            }
        }
        return new TypeInfo(typeName, typeName, "", Kind.CLASS, primitiveClass, true, null).setPrimitive(true);
    }

    public static TypeInfo fromJSTypeInfo(JSTypeInfo jsType) {
        if (jsType == null) {
            return null;
        }
        String syntheticDisplayName = ObjectLiteralParser.getSyntheticObjectLiteralDisplayName(jsType);
        if (syntheticDisplayName != null) {
            return new TypeInfo(syntheticDisplayName, jsType.getFullName(), "", Kind.INTERFACE, null, true, null, jsType);
        }
        String simpleName = jsType.getSimpleName();
        String fullName = jsType.getFullName();
        String namespace = jsType.getNamespace();
        return new TypeInfo(simpleName, fullName, namespace != null ? namespace : "", Kind.INTERFACE, null, true, null, jsType);
    }

    public static TypeInfo objectLiteral(List<ObjectLiteralParser.ObjectLiteralProperty> properties) {
        TypeInfo t = OBJECT_LITERAL.get();
        t.isObjectLiteral = true;
        if (properties != null) {
            for (ObjectLiteralParser.ObjectLiteralProperty p : properties) {
                TypeInfo vt;
                TypeInfo typeInfo = vt = p.valueType != null ? p.valueType : ANY;
                if (p.isCallable()) {
                    TypeInfo returnType;
                    ObjectLiteralParser.CallableInfo ci = p.callableInfo;
                    t.syntheticFields.add(FieldInfo.external(p.keyName, ANY, null, 1));
                    TypeInfo typeInfo2 = returnType = ci.returnsThis() ? t : ci.returnType;
                    if (returnType == null) {
                        returnType = t;
                    }
                    ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
                    for (String paramName : ci.parameterNames) {
                        params.add(FieldInfo.external(paramName, ANY, null, 1));
                    }
                    t.syntheticMethods.add(MethodInfo.external(p.keyName, returnType, t, params, 1, null));
                    continue;
                }
                t.syntheticFields.add(FieldInfo.external(p.keyName, vt, null, 1));
            }
        }
        return t;
    }

    public void addSyntheticField(String name, TypeInfo type) {
        this.syntheticFields.add(FieldInfo.external(name, type, null, 1));
    }

    public void removeSyntheticField(String name) {
        this.syntheticFields.removeIf(f -> f.getName().equals(name));
    }

    public void addSyntheticMethod(String name, TypeInfo returnType, List<FieldInfo> params) {
        this.syntheticMethods.add(MethodInfo.external(name, returnType, this, params != null ? params : Collections.emptyList(), 1, null));
    }

    public void removeSyntheticMethod(String name) {
        this.syntheticMethods.removeIf(m -> m.getName().equals(name));
    }

    public static void injectJSMembers(TypeInfo target, JSTypeInfo jsExtensions) {
        for (JSFieldInfo jsField : jsExtensions.getFields().values()) {
            target.syntheticFields.add(FieldInfo.fromJSField(jsField, target));
        }
        for (JSMethodInfo jsMethod : jsExtensions.getMethods().values()) {
            target.syntheticMethods.add(MethodInfo.fromJSMethod(jsMethod, target));
        }
    }

    public List<MethodInfo> getSyntheticMethods() {
        if (this.isArray() && TypeChecker.isJavaScriptMode()) {
            JSTypeInfo arrayType = JSTypeRegistry.getInstance().getType("Array");
            if (arrayType == null) {
                return this.syntheticMethods;
            }
            LinkedHashMap<MethodSignature, MethodInfo> merged = new LinkedHashMap<MethodSignature, MethodInfo>();
            for (MethodInfo method : this.syntheticMethods) {
                merged.put(method.getSignature(), method);
            }
            for (JSMethodInfo jsMethod : arrayType.getMethods().values()) {
                if (jsMethod.isStatic()) continue;
                MethodInfo method = MethodInfo.fromJSMethod(jsMethod, this);
                merged.putIfAbsent(method.getSignature(), method);
            }
            return new ArrayList<MethodInfo>(merged.values());
        }
        return this.syntheticMethods;
    }

    public List<FieldInfo> getSyntheticFields() {
        return this.syntheticFields;
    }

    public static TypeInfo arrayOf(TypeInfo elementType) {
        if (elementType == null) {
            return TypeInfo.fromClass(Object[].class);
        }
        String simpleName = elementType.getDisplayName() + "[]";
        String fullName = elementType.getDisplayNameFull() + "[]";
        String pkg = elementType.getPackageName();
        Class<?> arrayClass = null;
        if (elementType.getJavaClass() != null) {
            try {
                arrayClass = Array.newInstance(elementType.getJavaClass(), 0).getClass();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        TypeInfo arr = new TypeInfo(simpleName, fullName, pkg, elementType.kind, arrayClass, elementType.isResolved(), null, null, null, null, elementType);
        arr.setPrimitive(elementType.isPrimitive());
        arr.syntheticFields.add(FieldInfo.external("length", TypeInfo.fromPrimitive("int"), null, 17));
        arr.syntheticMethods.add(MethodInfo.external("clone", arr, null, Collections.emptyList(), 1, null));
        arr.typeParameterName = elementType.typeParameterName;
        if (elementType.boundType != null) {
            arr.boundType = TypeInfo.arrayOf(elementType.boundType);
        }
        return arr;
    }

    public boolean isJSType() {
        return this.jsTypeInfo != null;
    }

    public boolean isSyntheticObjectLiteralType() {
        return this.isObjectLiteral;
    }

    public JSTypeInfo getJSTypeInfo() {
        return this.jsTypeInfo;
    }

    public JSDocInfo getJSDocInfo() {
        if (this.jsDocInfo != null) {
            return this.jsDocInfo;
        }
        return this.jsTypeInfo != null ? this.jsTypeInfo.getJsDocInfo() : null;
    }

    public void setJSDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Class<?> getJavaClass() {
        return this.javaClass;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public TypeInfo getEnclosingType() {
        return this.enclosingType;
    }

    public boolean isInnerClass() {
        return this.enclosingType != null;
    }

    public boolean isInterface() {
        return this.kind == Kind.INTERFACE;
    }

    public boolean isEnum() {
        return this.kind == Kind.ENUM;
    }

    public boolean isClass() {
        return this.kind == Kind.CLASS;
    }

    public boolean isPrimitive() {
        return this.isPrimitive || this.javaClass != null && this.javaClass.isPrimitive();
    }

    private TypeInfo setPrimitive(boolean value) {
        this.isPrimitive = value;
        return this;
    }

    public boolean isParameterized() {
        return !this.appliedTypeArgs.isEmpty();
    }

    public TypeInfo getRawType() {
        return this.rawType != null ? this.rawType : this;
    }

    public TypeInfo getElementType() {
        return this.elementType;
    }

    public boolean isArray() {
        return this.elementType != null || this.javaClass != null && this.javaClass.isArray();
    }

    public List<TypeInfo> getAppliedTypeArgs() {
        return this.appliedTypeArgs;
    }

    protected void setRawType(TypeInfo rawType) {
        this.rawType = rawType;
    }

    protected void setAppliedTypeArgs(List<TypeInfo> args) {
        this.appliedTypeArgs = new ArrayList<TypeInfo>(args);
    }

    public TypeInfo parameterize(List<TypeInfo> typeArgs) {
        if (typeArgs == null || typeArgs.isEmpty()) {
            return this;
        }
        TypeInfo raw = this.getRawType();
        return new TypeInfo(raw.simpleName, raw.fullName, raw.packageName, raw.kind, raw.javaClass, raw.resolved, raw.enclosingType, raw.jsTypeInfo, raw, typeArgs, null);
    }

    public TypeInfo parameterize(TypeInfo typeArg) {
        if (typeArg == null) {
            return this;
        }
        ArrayList<TypeInfo> args = new ArrayList<TypeInfo>();
        args.add(typeArg);
        return this.parameterize(args);
    }

    private static String buildTypeArgsString(List<TypeInfo> typeArgs, boolean useFullNames) {
        if (typeArgs == null || typeArgs.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < typeArgs.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            TypeInfo arg = typeArgs.get(i);
            sb.append(useFullNames ? arg.getDisplayNameFull() : arg.getDisplayNameSimple());
        }
        sb.append(">");
        return sb.toString();
    }

    public String getDisplayName() {
        return this.isJSType() ? this.getDisplayNameFull() : this.getDisplayNameSimple();
    }

    public String getDisplayNameSimple() {
        if (this.appliedTypeArgs.isEmpty()) {
            return this.simpleName;
        }
        String baseName = this.rawType != null ? this.rawType.getSimpleName() : this.simpleName;
        return baseName + TypeInfo.buildTypeArgsString(this.appliedTypeArgs, false);
    }

    public String getDisplayNameFull() {
        if (this.appliedTypeArgs.isEmpty()) {
            return this.fullName;
        }
        String baseName = this.rawType != null ? this.rawType.getFullName() : this.fullName;
        return baseName + TypeInfo.buildTypeArgsString(this.appliedTypeArgs, true);
    }

    public boolean isClassReference() {
        return false;
    }

    public TokenType getTokenType() {
        if (!this.resolved) {
            return TokenType.UNDEFINED_VAR;
        }
        if (this.isTypeParameter()) {
            return TokenType.GENERIC_TYPE_PARAM;
        }
        if (this.isPrimitive()) {
            return TokenType.KEYWORD;
        }
        switch (this.kind) {
            case INTERFACE: {
                return TokenType.INTERFACE_DECL;
            }
            case ENUM: {
                return TokenType.ENUM_DECL;
            }
        }
        return TokenType.IMPORTED_CLASS;
    }

    public List<MethodInfo> getAllMethods() {
        if (this.javaClass != null) {
            ArrayList<MethodInfo> result = new ArrayList<MethodInfo>();
            try {
                for (Method m : this.javaClass.getMethods()) {
                    try {
                        result.add(MethodInfo.fromReflection(m, this));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            result.addAll(this.getSyntheticMethods());
            return result;
        }
        if (this.rawType != null && this.rawType != this) {
            List<MethodInfo> rawMethods = this.rawType.getAllMethods();
            if (!rawMethods.isEmpty() && this.isParameterized()) {
                GenericContext ctx = GenericContext.forReceiver(this);
                ArrayList<MethodInfo> substituted = new ArrayList<MethodInfo>(rawMethods.size());
                for (MethodInfo m : rawMethods) {
                    substituted.add(m.substituteTypeParams(ctx));
                }
                return substituted;
            }
            return rawMethods;
        }
        ArrayList<MethodInfo> result = new ArrayList<MethodInfo>();
        if (this.jsTypeInfo != null) {
            for (JSMethodInfo m : this.jsTypeInfo.getMethods().values()) {
                result.add(MethodInfo.fromJSMethod(m, this));
            }
        }
        result.addAll(this.getSyntheticMethods());
        return result;
    }

    public List<FieldInfo> getAllFields() {
        if (this.javaClass != null) {
            ArrayList<FieldInfo> result = new ArrayList<FieldInfo>();
            try {
                for (Field f : this.javaClass.getFields()) {
                    try {
                        result.add(FieldInfo.fromReflection(f, this));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            result.addAll(this.getSyntheticFields());
            return result;
        }
        if (this.rawType != null && this.rawType != this) {
            List<FieldInfo> rawFields = this.rawType.getAllFields();
            if (!rawFields.isEmpty() && this.isParameterized()) {
                GenericContext ctx = GenericContext.forReceiver(this);
                ArrayList<FieldInfo> substituted = new ArrayList<FieldInfo>(rawFields.size());
                for (FieldInfo f : rawFields) {
                    substituted.add(f.substituteTypeParams(ctx));
                }
                return substituted;
            }
            return rawFields;
        }
        ArrayList<FieldInfo> result = new ArrayList<FieldInfo>();
        if (this.jsTypeInfo != null) {
            for (JSFieldInfo f : this.jsTypeInfo.getFields().values()) {
                result.add(FieldInfo.fromJSField(f, this));
            }
        }
        result.addAll(this.getSyntheticFields());
        return result;
    }

    public List<TypeInfo> getAllNestedTypes() {
        Map<String, JSTypeInfo> innerTypes;
        if (this.javaClass != null) {
            ArrayList<TypeInfo> result = new ArrayList<TypeInfo>();
            try {
                for (Class<?> nested : this.javaClass.getDeclaredClasses()) {
                    if (!Modifier.isPublic(nested.getModifiers())) continue;
                    String nestedFullName = nested.getName().replace('$', '.');
                    TypeInfo nestedType = TypeResolver.getInstance().resolveFullName(nestedFullName);
                    if (nestedType == null || !nestedType.isResolved()) continue;
                    result.add(nestedType);
                }
            }
            catch (SecurityException securityException) {
                // empty catch block
            }
            return result;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.getAllNestedTypes();
        }
        if (this.jsTypeInfo != null && !(innerTypes = this.jsTypeInfo.getInnerTypes()).isEmpty()) {
            ArrayList<TypeInfo> result = new ArrayList<TypeInfo>(innerTypes.size());
            for (JSTypeInfo inner : innerTypes.values()) {
                TypeInfo ti = TypeInfo.fromJSTypeInfo(inner);
                if (ti == null) continue;
                result.add(ti);
            }
            return result;
        }
        return Collections.emptyList();
    }

    public boolean hasMethod(String methodName) {
        TypeInfo obj;
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            return true;
        }
        if (this.jsTypeInfo != null) {
            if (this.jsTypeInfo.hasMethod(methodName)) {
                return true;
            }
        } else if (this.javaClass != null) {
            try {
                for (Method m : this.javaClass.getMethods()) {
                    if (!m.getName().equals(methodName)) continue;
                    return true;
                }
            }
            catch (Exception exception) {}
        } else if (this.rawType != null && this.rawType != this) {
            return this.rawType.hasMethod(methodName);
        }
        if ((obj = TypeInfo.object()) != this) {
            return obj.hasMethod(methodName);
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    public boolean hasMethod(String methodName, int paramCount) {
        Method[] overloads2;
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName) || m.getParameterCount() != paramCount) continue;
            return true;
        }
        if (this.jsTypeInfo != null) {
            overloads2 = this.jsTypeInfo.getMethodOverloads(methodName);
            for (JSMethodInfo jSMethodInfo : overloads2) {
                if (jSMethodInfo.getParameterCount() != paramCount) continue;
                return true;
            }
        } else if (this.javaClass != null) {
            try {
                void var5_9;
                overloads2 = this.javaClass.getMethods();
                int n = overloads2.length;
                boolean bl = false;
                while (var5_9 < n) {
                    Method m = overloads2[var5_9];
                    if (m.getName().equals(methodName) && m.getParameterCount() == paramCount) {
                        return true;
                    }
                    ++var5_9;
                }
            }
            catch (Exception overloads2) {}
        } else if (this.rawType != null && this.rawType != this) {
            return this.rawType.hasMethod(methodName, paramCount);
        }
        TypeInfo obj = TypeInfo.object();
        if (obj != this) {
            return obj.hasMethod(methodName);
        }
        return false;
    }

    public boolean hasConstructors() {
        if (this.javaClass != null) {
            try {
                return this.javaClass.getConstructors().length > 0;
            }
            catch (Exception e) {
                return false;
            }
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.hasConstructors();
        }
        return false;
    }

    public List<MethodInfo> getConstructors() {
        ArrayList<MethodInfo> result = new ArrayList<MethodInfo>();
        if (this.javaClass != null) {
            try {
                Constructor<?>[] constructors;
                for (Constructor<?> ctor : constructors = this.javaClass.getConstructors()) {
                    result.add(MethodInfo.fromReflectionConstructor(ctor, this));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return result;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.getConstructors();
        }
        return result;
    }

    public MethodInfo findConstructor(int argCount) {
        if (this.javaClass != null) {
            try {
                Constructor<?>[] constructors;
                for (Constructor<?> ctor : constructors = this.javaClass.getConstructors()) {
                    if (ctor.getParameterCount() != argCount) continue;
                    return MethodInfo.fromReflectionConstructor(ctor, this);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return null;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.findConstructor(argCount);
        }
        return null;
    }

    public MethodInfo findConstructor(TypeInfo[] argTypes) {
        if (this.javaClass != null) {
            try {
                Constructor<?>[] constructors;
                for (Constructor<?> ctor : constructors = this.javaClass.getConstructors()) {
                    if (ctor.getParameterCount() != argTypes.length) continue;
                    Class<?>[] paramTypes = ctor.getParameterTypes();
                    boolean match = true;
                    for (int i = 0; i < argTypes.length; ++i) {
                        TypeInfo paramTypeInfo = TypeInfo.fromClass(paramTypes[i]);
                        if (TypeChecker.isTypeCompatible(paramTypeInfo, argTypes[i])) continue;
                        match = false;
                        break;
                    }
                    if (!match) continue;
                    return MethodInfo.fromReflectionConstructor(ctor, this);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return null;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.findConstructor(argTypes);
        }
        return null;
    }

    public boolean hasField(String fieldName) {
        for (FieldInfo f : this.syntheticFields) {
            if (!f.getName().equals(fieldName)) continue;
            return true;
        }
        if (this.jsTypeInfo != null) {
            return this.jsTypeInfo.hasField(fieldName);
        }
        if (this.javaClass != null) {
            try {
                for (Field f : this.javaClass.getFields()) {
                    if (!f.getName().equals(fieldName)) continue;
                    return true;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return false;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.hasField(fieldName);
        }
        return false;
    }

    public Map<String, EnumConstantInfo> getEnumConstants() {
        if (this.javaClass == null || !this.javaClass.isEnum()) {
            if (this.rawType != null && this.rawType != this) {
                return this.rawType.getEnumConstants();
            }
            return Collections.emptyMap();
        }
        LinkedHashMap<String, EnumConstantInfo> result = new LinkedHashMap<String, EnumConstantInfo>();
        try {
            ?[] constants;
            for (Object constant : constants = this.javaClass.getEnumConstants()) {
                String name = constant.toString();
                result.put(name, EnumConstantInfo.fromReflection(name, this, null));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return result;
    }

    public boolean hasEnumConstant(String constantName) {
        if (this.javaClass != null && this.javaClass.isEnum()) {
            try {
                ?[] constants;
                for (Object constant : constants = this.javaClass.getEnumConstants()) {
                    if (!constant.toString().equals(constantName)) continue;
                    return true;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return false;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.hasEnumConstant(constantName);
        }
        return false;
    }

    public EnumConstantInfo getEnumConstant(String constantName) {
        if (this.javaClass != null && this.javaClass.isEnum()) {
            try {
                ?[] constants;
                for (Object constant : constants = this.javaClass.getEnumConstants()) {
                    if (!constant.toString().equals(constantName)) continue;
                    return EnumConstantInfo.fromReflection(constantName, this, null);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return null;
        }
        if (this.rawType != null && this.rawType != this) {
            return this.rawType.getEnumConstant(constantName);
        }
        return null;
    }

    public MethodInfo getMethodInfo(String methodName) {
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            return m;
        }
        if (this.jsTypeInfo != null) {
            Method[] jsMethod = this.jsTypeInfo.getMethod(methodName);
            if (jsMethod != null) {
                return MethodInfo.fromJSMethod((JSMethodInfo)jsMethod, this);
            }
        } else if (this.javaClass != null) {
            try {
                for (Method m : this.javaClass.getMethods()) {
                    if (!m.getName().equals(methodName)) continue;
                    return MethodInfo.fromReflection(m, this);
                }
            }
            catch (Exception jsMethod) {}
        } else if (this.rawType != null && this.rawType != this) {
            MethodInfo rawMethod = this.rawType.getMethodInfo(methodName);
            if (rawMethod != null && this.isParameterized()) {
                GenericContext ctx = GenericContext.forReceiver(this);
                return rawMethod.substituteTypeParams(ctx);
            }
            return rawMethod;
        }
        TypeInfo obj = TypeInfo.object();
        if (obj != this) {
            return obj.getMethodInfo(methodName);
        }
        return null;
    }

    public List<MethodInfo> getAllMethodOverloads(String methodName) {
        TypeInfo obj;
        ArrayList<MethodInfo> overloads = new ArrayList<MethodInfo>();
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            overloads.add(m);
        }
        if (!overloads.isEmpty()) {
            return overloads;
        }
        if (this.jsTypeInfo != null) {
            for (JSMethodInfo jsMethod : this.jsTypeInfo.getMethodOverloads(methodName)) {
                overloads.add(MethodInfo.fromJSMethod(jsMethod, this));
            }
            if (!overloads.isEmpty()) {
                return overloads;
            }
        } else if (this.javaClass != null) {
            try {
                for (Iterator<Object> iterator : this.javaClass.getMethods()) {
                    if (!((Method)((Object)iterator)).getName().equals(methodName)) continue;
                    overloads.add(MethodInfo.fromReflection(iterator, this));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (!overloads.isEmpty()) {
                return overloads;
            }
        } else if (this.rawType != null && this.rawType != this) {
            List<MethodInfo> rawOverloads = this.rawType.getAllMethodOverloads(methodName);
            if (!rawOverloads.isEmpty() && this.isParameterized()) {
                GenericContext ctx = GenericContext.forReceiver(this);
                for (MethodInfo methodInfo : rawOverloads) {
                    overloads.add(methodInfo.substituteTypeParams(ctx));
                }
                return overloads;
            }
            return rawOverloads;
        }
        if ((obj = TypeInfo.object()) != this) {
            return obj.getAllMethodOverloads(methodName);
        }
        return overloads;
    }

    public MethodInfo getBestMethodOverload(String methodName, TypeInfo expectedReturnType) {
        List<MethodInfo> overloads = this.getAllMethodOverloads(methodName);
        if (overloads.isEmpty()) {
            return null;
        }
        if (expectedReturnType == null) {
            return overloads.get(0);
        }
        for (MethodInfo method : overloads) {
            TypeInfo returnType = method.getReturnType();
            if (returnType == null || !TypeChecker.isTypeCompatible(expectedReturnType, returnType)) continue;
            return method;
        }
        return overloads.get(0);
    }

    public MethodInfo getBestMethodOverload(String methodName, TypeInfo[] argTypes) {
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            return m;
        }
        List<MethodInfo> overloads = this.getAllMethodOverloads(methodName);
        if (overloads.isEmpty()) {
            return null;
        }
        return OverloadSelector.selectBestOverload(overloads, argTypes);
    }

    public FieldInfo getFieldInfo(String fieldName) {
        for (FieldInfo f : this.syntheticFields) {
            if (!f.getName().equals(fieldName)) continue;
            return f;
        }
        if (this.jsTypeInfo != null) {
            Field[] jsField = this.jsTypeInfo.getField(fieldName);
            if (jsField != null) {
                return FieldInfo.fromJSField((JSFieldInfo)jsField, this);
            }
            return null;
        }
        if (this.javaClass != null) {
            try {
                for (Field f : this.javaClass.getFields()) {
                    if (!f.getName().equals(fieldName)) continue;
                    return FieldInfo.fromReflection(f, this);
                }
            }
            catch (Exception jsField) {
                // empty catch block
            }
            return null;
        }
        if (this.rawType != null && this.rawType != this) {
            FieldInfo rawField = this.rawType.getFieldInfo(fieldName);
            if (rawField != null && this.isParameterized()) {
                GenericContext ctx = GenericContext.forReceiver(this);
                return rawField.substituteTypeParams(ctx);
            }
            return rawField;
        }
        return null;
    }

    public void validate() {
    }

    public MethodInfo getSingleAbstractMethod() {
        if (this.samCacheResolved) {
            return this.cachedSAM;
        }
        this.samCacheResolved = true;
        try {
            if (this.javaClass != null) {
                if (!this.javaClass.isInterface()) {
                    this.cachedSAM = null;
                    return null;
                }
                Method[] methods = this.javaClass.getMethods();
                Method singleAbstractMethod = null;
                for (Method method : methods) {
                    String methodName;
                    int modifiers = method.getModifiers();
                    if (Modifier.isStatic(modifiers) || method.isDefault() || this.isObjectMethod(methodName = method.getName(), method.getParameterCount())) continue;
                    if (singleAbstractMethod != null) {
                        this.cachedSAM = null;
                        return null;
                    }
                    singleAbstractMethod = method;
                }
                if (singleAbstractMethod != null) {
                    this.cachedSAM = MethodInfo.fromReflection(singleAbstractMethod, this);
                    return this.cachedSAM;
                }
                this.cachedSAM = null;
                return null;
            }
            if (this instanceof ScriptTypeInfo) {
                ScriptTypeInfo scriptType = (ScriptTypeInfo)this;
                if (scriptType.getKind() != Kind.INTERFACE) {
                    this.cachedSAM = null;
                    return null;
                }
                List<MethodInfo> allMethods = scriptType.getAllMethodsFlat();
                if (allMethods.size() == 1) {
                    this.cachedSAM = allMethods.get(0);
                    return this.cachedSAM;
                }
                if (allMethods.size() > 1) {
                    this.cachedSAM = null;
                    return null;
                }
                this.cachedSAM = null;
                return null;
            }
            if (this.rawType != null && this.rawType != this) {
                MethodInfo rawSAM = this.rawType.getSingleAbstractMethod();
                if (rawSAM != null && this.isParameterized()) {
                    GenericContext ctx = GenericContext.forReceiver(this);
                    this.cachedSAM = rawSAM.substituteTypeParams(ctx);
                    return this.cachedSAM;
                }
                this.cachedSAM = rawSAM;
                return this.cachedSAM;
            }
            this.cachedSAM = null;
            return null;
        }
        catch (Exception e) {
            this.cachedSAM = null;
            return null;
        }
    }

    public boolean isFunctionalInterface() {
        return this.getSingleAbstractMethod() != null;
    }

    private boolean isObjectMethod(String methodName, int paramCount) {
        switch (methodName) {
            case "equals": {
                return paramCount == 1;
            }
            case "hashCode": 
            case "toString": 
            case "getClass": 
            case "notify": 
            case "notifyAll": {
                return paramCount == 0;
            }
            case "wait": {
                return paramCount == 0 || paramCount == 1 || paramCount == 2;
            }
        }
        return false;
    }

    public void addTypeParam(TypeParamInfo param) {
        if (this.jsTypeInfo != null) {
            this.jsTypeInfo.addTypeParam(param);
        } else {
            this.typeParams.add(param);
        }
    }

    public List<TypeParamInfo> getTypeParams() {
        if (this.jsTypeInfo != null) {
            return this.jsTypeInfo.getTypeParams();
        }
        if (this.javaClass != null && this.typeParams.isEmpty()) {
            try {
                TypeVariable<Class<?>>[] vars;
                for (TypeVariable<Class<?>> v : vars = this.javaClass.getTypeParameters()) {
                    String n;
                    if (v == null || (n = v.getName()) == null || n.isEmpty()) continue;
                    this.typeParams.add(new TypeParamInfo(n, null, null));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return this.typeParams;
    }

    public void resolveTypeParameters() {
        if (this.jsTypeInfo != null) {
            this.jsTypeInfo.resolveTypeParameters();
        } else {
            for (TypeParamInfo param : this.typeParams) {
                param.resolveBoundType();
            }
        }
    }

    public TypeParamInfo getTypeParam(String name) {
        if (this.jsTypeInfo != null) {
            return this.jsTypeInfo.getTypeParam(name);
        }
        for (TypeParamInfo param : this.typeParams) {
            if (!param.getName().equals(name)) continue;
            return param;
        }
        return null;
    }

    public String toString() {
        return "TypeInfo{" + this.fullName + ", " + (Object)((Object)this.kind) + ", resolved=" + this.resolved + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeInfo)) {
            return false;
        }
        TypeInfo other = (TypeInfo)o;
        if (!this.fullName.equals(other.fullName)) {
            return false;
        }
        if (this.typeParameterName != null || other.typeParameterName != null) {
            if (!Objects.equals(this.typeParameterName, other.typeParameterName)) {
                return false;
            }
            if (!Objects.equals(this.boundType, other.boundType)) {
                return false;
            }
        }
        if (!this.appliedTypeArgs.equals(other.appliedTypeArgs)) {
            return false;
        }
        return Objects.equals(this.elementType, other.elementType);
    }

    public int hashCode() {
        int result = this.fullName.hashCode();
        if (this.typeParameterName != null) {
            result = 31 * result + this.typeParameterName.hashCode();
        }
        if (this.elementType != null) {
            result = 31 * result + this.elementType.hashCode();
        }
        return result;
    }

    public static enum Kind {
        CLASS,
        INTERFACE,
        ENUM,
        UNKNOWN;

    }
}

