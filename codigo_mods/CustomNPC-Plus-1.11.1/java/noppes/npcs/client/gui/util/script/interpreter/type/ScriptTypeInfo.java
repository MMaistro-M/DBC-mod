/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodSignature;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class ScriptTypeInfo
extends TypeInfo {
    private final String scriptClassName;
    private final int declarationOffset;
    private final int bodyStart;
    private final int bodyEnd;
    private final int modifiers;
    private final Map<String, FieldInfo> fields = new HashMap<String, FieldInfo>();
    private final Map<String, List<MethodInfo>> methods = new HashMap<String, List<MethodInfo>>();
    private final List<MethodInfo> constructors = new ArrayList<MethodInfo>();
    private final List<ScriptTypeInfo> innerClasses = new ArrayList<ScriptTypeInfo>();
    private final Map<String, EnumConstantInfo> enumConstants = new HashMap<String, EnumConstantInfo>();
    private final List<TypeParamInfo> declaredTypeParams = new ArrayList<TypeParamInfo>();
    private static final ThreadLocal<Set<ScriptTypeInfo>> PARAMETERIZING = ThreadLocal.withInitial(HashSet::new);
    private ScriptTypeInfo outerClass;
    private TypeInfo superClass;
    private String superClassName;
    private final List<TypeInfo> implementedInterfaces = new ArrayList<TypeInfo>();
    private final List<String> implementedInterfaceNames = new ArrayList<String>();
    private ErrorType errorType = ErrorType.NONE;
    private String errorMessage;
    private final List<MissingMethodError> missingMethodErrors = new ArrayList<MissingMethodError>();
    private final List<ConstructorMismatchError> constructorMismatchErrors = new ArrayList<ConstructorMismatchError>();

    private ScriptTypeInfo(String simpleName, String fullName, TypeInfo.Kind kind, int declarationOffset, int bodyStart, int bodyEnd, int modifiers) {
        super(simpleName, fullName, "", kind, null, true, null, true);
        this.scriptClassName = simpleName;
        this.declarationOffset = declarationOffset;
        this.bodyStart = bodyStart;
        this.bodyEnd = bodyEnd;
        this.modifiers = modifiers;
    }

    public static ScriptTypeInfo create(String simpleName, TypeInfo.Kind kind, int declarationOffset, int bodyStart, int bodyEnd, int modifiers) {
        return new ScriptTypeInfo(simpleName, simpleName, kind, declarationOffset, bodyStart, bodyEnd, modifiers);
    }

    public static ScriptTypeInfo createInner(String simpleName, TypeInfo.Kind kind, ScriptTypeInfo outer, int declarationOffset, int bodyStart, int bodyEnd, int modifiers) {
        String fullName = outer.getFullName() + "$" + simpleName;
        ScriptTypeInfo inner = new ScriptTypeInfo(simpleName, fullName, kind, declarationOffset, bodyStart, bodyEnd, modifiers);
        inner.outerClass = outer;
        outer.innerClasses.add(inner);
        return inner;
    }

    public String getScriptClassName() {
        return this.scriptClassName;
    }

    public int getDeclarationOffset() {
        return this.declarationOffset;
    }

    public int getBodyStart() {
        return this.bodyStart;
    }

    public int getBodyEnd() {
        return this.bodyEnd;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public ScriptTypeInfo getOuterClass() {
        return this.outerClass;
    }

    public List<ScriptTypeInfo> getInnerClasses() {
        return this.innerClasses;
    }

    public void addDeclaredTypeParam(TypeParamInfo param) {
        this.declaredTypeParams.add(param);
    }

    public List<TypeParamInfo> getDeclaredTypeParams() {
        return this.declaredTypeParams;
    }

    public TypeParamInfo getDeclaredTypeParam(String name) {
        for (TypeParamInfo param : this.declaredTypeParams) {
            if (!param.getName().equals(name)) continue;
            return param;
        }
        return null;
    }

    public boolean hasDeclaredTypeParams() {
        return !this.declaredTypeParams.isEmpty();
    }

    @Override
    public List<TypeParamInfo> getTypeParams() {
        if (!this.declaredTypeParams.isEmpty()) {
            return this.declaredTypeParams;
        }
        return super.getTypeParams();
    }

    @Override
    public TypeParamInfo getTypeParam(String name) {
        TypeParamInfo param = this.getDeclaredTypeParam(name);
        if (param != null) {
            return param;
        }
        return super.getTypeParam(name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeInfo parameterize(List<TypeInfo> typeArgs) {
        if (typeArgs == null || typeArgs.isEmpty()) {
            return this;
        }
        Set<ScriptTypeInfo> active = PARAMETERIZING.get();
        if (!active.add(this)) {
            return super.parameterize(typeArgs);
        }
        try {
            ScriptTypeInfo result = new ScriptTypeInfo(this.getSimpleName(), this.getFullName(), this.getKind(), this.declarationOffset, this.bodyStart, this.bodyEnd, this.modifiers);
            result.setRawType(this);
            result.setAppliedTypeArgs(typeArgs);
            result.superClass = this.superClass;
            result.superClassName = this.superClassName;
            result.implementedInterfaces.addAll(this.implementedInterfaces);
            result.implementedInterfaceNames.addAll(this.implementedInterfaceNames);
            result.declaredTypeParams.addAll(this.declaredTypeParams);
            result.outerClass = this.outerClass;
            GenericContext ctx = GenericContext.fromBindings(this.declaredTypeParams, typeArgs);
            for (Map.Entry<String, List<MethodInfo>> entry : this.methods.entrySet()) {
                ArrayList<MethodInfo> substituted = new ArrayList<MethodInfo>();
                for (MethodInfo m : entry.getValue()) {
                    substituted.add(m.substituteTypeParams(ctx));
                }
                result.methods.put(entry.getKey(), substituted);
            }
            for (MethodInfo methodInfo : this.constructors) {
                result.constructors.add(methodInfo.substituteTypeParams(ctx));
            }
            for (Map.Entry entry : this.fields.entrySet()) {
                result.fields.put((String)entry.getKey(), ((FieldInfo)entry.getValue()).substituteTypeParams(ctx));
            }
            ScriptTypeInfo scriptTypeInfo = result;
            return scriptTypeInfo;
        }
        finally {
            active.remove(this);
        }
    }

    public TypeInfo getSuperClass() {
        return this.superClass;
    }

    public String getSuperClassName() {
        return this.superClassName;
    }

    public void setSuperClass(TypeInfo superClass, String superClassName) {
        this.superClass = superClass;
        this.superClassName = superClassName;
    }

    public boolean hasSuperClass() {
        return this.superClass != null || this.superClassName != null;
    }

    public List<TypeInfo> getImplementedInterfaces() {
        return new ArrayList<TypeInfo>(this.implementedInterfaces);
    }

    public List<String> getImplementedInterfaceNames() {
        return new ArrayList<String>(this.implementedInterfaceNames);
    }

    public void addImplementedInterface(TypeInfo interfaceType, String interfaceName) {
        this.implementedInterfaces.add(interfaceType);
        this.implementedInterfaceNames.add(interfaceName);
    }

    public boolean hasImplementedInterfaces() {
        return !this.implementedInterfaces.isEmpty();
    }

    public boolean implementsInterface(String interfaceName) {
        for (String name : this.implementedInterfaceNames) {
            if (!name.equals(interfaceName)) continue;
            return true;
        }
        for (TypeInfo ti : this.implementedInterfaces) {
            if (!ti.getSimpleName().equals(interfaceName)) continue;
            return true;
        }
        return false;
    }

    public boolean containsPosition(int position) {
        return position >= this.bodyStart && position < this.bodyEnd;
    }

    public void addField(FieldInfo field) {
        this.fields.put(field.getName(), field);
    }

    @Override
    public boolean hasField(String fieldName) {
        if (this.fields.containsKey(fieldName)) {
            return true;
        }
        if (this.enumConstants.containsKey(fieldName)) {
            return true;
        }
        for (FieldInfo f : this.getSyntheticFields()) {
            if (!f.getName().equals(fieldName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public FieldInfo getFieldInfo(String fieldName) {
        EnumConstantInfo enumConst;
        if (this.fields.containsKey(fieldName)) {
            return this.fields.get(fieldName);
        }
        if (this.enumConstants.containsKey(fieldName) && (enumConst = this.enumConstants.get(fieldName)) != null) {
            return enumConst.getFieldInfo();
        }
        for (FieldInfo f : this.getSyntheticFields()) {
            if (!f.getName().equals(fieldName)) continue;
            return f;
        }
        return null;
    }

    public Map<String, FieldInfo> getFields() {
        return new HashMap<String, FieldInfo>(this.fields);
    }

    public void addEnumConstant(EnumConstantInfo constant) {
        if (this.isEnum()) {
            this.enumConstants.put(constant.getFieldInfo().getName(), constant);
        }
    }

    @Override
    public boolean hasEnumConstant(String constantName) {
        return this.enumConstants.containsKey(constantName);
    }

    @Override
    public EnumConstantInfo getEnumConstant(String constantName) {
        return this.enumConstants.get(constantName);
    }

    @Override
    public Map<String, EnumConstantInfo> getEnumConstants() {
        return new HashMap<String, EnumConstantInfo>(this.enumConstants);
    }

    public boolean hasEnumConstants() {
        return this.isEnum() && !this.enumConstants.isEmpty();
    }

    public void addMethod(MethodInfo method) {
        this.methods.computeIfAbsent(method.getName(), k -> new ArrayList()).add(method);
    }

    @Override
    public boolean hasMethod(String methodName) {
        if (this.methods.containsKey(methodName)) {
            return true;
        }
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasMethod(String methodName, int paramCount) {
        List<MethodInfo> overloads = this.methods.get(methodName);
        if (overloads != null) {
            for (MethodInfo m : overloads) {
                if (m.getParameterCount() != paramCount) continue;
                return true;
            }
        }
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName) || m.getParameterCount() != paramCount) continue;
            return true;
        }
        return false;
    }

    @Override
    public MethodInfo getMethodInfo(String methodName) {
        List<MethodInfo> overloads = this.methods.get(methodName);
        if (overloads != null && !overloads.isEmpty()) {
            return overloads.get(0);
        }
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            return m;
        }
        return null;
    }

    @Override
    public List<MethodInfo> getAllMethodOverloads(String methodName) {
        ArrayList<MethodInfo> result = new ArrayList<MethodInfo>(this.methods.getOrDefault(methodName, Collections.emptyList()));
        for (MethodInfo m : this.getSyntheticMethods()) {
            if (!m.getName().equals(methodName)) continue;
            result.add(m);
        }
        return result;
    }

    public MethodInfo getMethodWithParamCount(String methodName, int paramCount) {
        List<MethodInfo> overloads = this.methods.get(methodName);
        if (overloads == null) {
            return null;
        }
        for (MethodInfo m : overloads) {
            if (m.getParameterCount() != paramCount) continue;
            return m;
        }
        return null;
    }

    public Map<String, List<MethodInfo>> getMethods() {
        return new HashMap<String, List<MethodInfo>>(this.methods);
    }

    public List<MethodInfo> getAllMethodsFlat() {
        ArrayList<MethodInfo> allMethods = new ArrayList<MethodInfo>();
        for (List<MethodInfo> overloads : this.methods.values()) {
            allMethods.addAll(overloads);
        }
        return allMethods;
    }

    @Override
    public List<MethodInfo> getAllMethods() {
        List<MethodInfo> result = this.getAllMethodsFlat();
        result.addAll(this.getSyntheticMethods());
        return result;
    }

    @Override
    public List<FieldInfo> getAllFields() {
        ArrayList<FieldInfo> result = new ArrayList<FieldInfo>(this.getFields().values());
        result.addAll(this.getSyntheticFields());
        return result;
    }

    @Override
    public List<TypeInfo> getAllNestedTypes() {
        return new ArrayList<TypeInfo>(this.innerClasses);
    }

    public void addConstructor(MethodInfo constructor) {
        this.constructors.add(constructor);
    }

    @Override
    public List<MethodInfo> getConstructors() {
        return new ArrayList<MethodInfo>(this.constructors);
    }

    @Override
    public boolean hasConstructors() {
        return !this.constructors.isEmpty();
    }

    @Override
    public MethodInfo findConstructor(int argCount) {
        for (MethodInfo constructor : this.constructors) {
            if (constructor.getParameterCount() != argCount) continue;
            return constructor;
        }
        return null;
    }

    @Override
    public MethodInfo findConstructor(TypeInfo[] argTypes) {
        for (MethodInfo constructor : this.constructors) {
            if (constructor.getParameterCount() != argTypes.length) continue;
            boolean match = true;
            List<FieldInfo> params = constructor.getParameters();
            for (int i = 0; i < argTypes.length; ++i) {
                TypeInfo paramType = params.get(i).getTypeInfo();
                if (TypeChecker.isTypeCompatible(paramType, argTypes[i])) continue;
                match = false;
                break;
            }
            if (!match) continue;
            return constructor;
        }
        return null;
    }

    public boolean hasFieldInHierarchy(String fieldName) {
        if (this.hasField(fieldName)) {
            return true;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).hasFieldInHierarchy(fieldName);
            }
            return this.superClass.hasField(fieldName);
        }
        return false;
    }

    public FieldInfo getFieldInfoInHierarchy(String fieldName) {
        FieldInfo field = this.getFieldInfo(fieldName);
        if (field != null) {
            return field;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).getFieldInfoInHierarchy(fieldName);
            }
            return this.superClass.getFieldInfo(fieldName);
        }
        return null;
    }

    public boolean hasMethodInHierarchy(String methodName) {
        if (this.hasMethod(methodName)) {
            return true;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).hasMethodInHierarchy(methodName);
            }
            return this.superClass.hasMethod(methodName);
        }
        return false;
    }

    public boolean hasMethodInHierarchy(String methodName, int paramCount) {
        if (this.hasMethod(methodName, paramCount)) {
            return true;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).hasMethodInHierarchy(methodName, paramCount);
            }
            return this.superClass.hasMethod(methodName, paramCount);
        }
        return false;
    }

    public MethodInfo getMethodInfoInHierarchy(String methodName) {
        MethodInfo method = this.getMethodInfo(methodName);
        if (method != null) {
            return method;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).getMethodInfoInHierarchy(methodName);
            }
            return this.superClass.getMethodInfo(methodName);
        }
        return null;
    }

    public MethodInfo getMethodWithParamCountInHierarchy(String methodName, int paramCount) {
        MethodInfo method = this.getMethodWithParamCount(methodName, paramCount);
        if (method != null) {
            return method;
        }
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                return ((ScriptTypeInfo)this.superClass).getMethodWithParamCountInHierarchy(methodName, paramCount);
            }
            List<MethodInfo> overloads = this.superClass.getAllMethodOverloads(methodName);
            for (MethodInfo m : overloads) {
                if (m.getParameterCount() != paramCount) continue;
                return m;
            }
        }
        return null;
    }

    public List<MethodInfo> getAllMethodOverloadsInHierarchy(String methodName) {
        ArrayList<MethodInfo> result = new ArrayList<MethodInfo>();
        result.addAll(this.getAllMethodOverloads(methodName));
        if (this.superClass != null && this.superClass.isResolved()) {
            if (this.superClass instanceof ScriptTypeInfo) {
                result.addAll(((ScriptTypeInfo)this.superClass).getAllMethodOverloadsInHierarchy(methodName));
            } else {
                result.addAll(this.superClass.getAllMethodOverloads(methodName));
            }
        }
        return result;
    }

    @Override
    public MethodInfo getBestMethodOverload(String methodName, TypeInfo[] argTypes) {
        TypeInfo argType;
        TypeInfo paramType;
        int i;
        List<FieldInfo> params;
        List<MethodInfo> allOverloads = this.getAllMethodOverloadsInHierarchy(methodName);
        if (allOverloads.isEmpty()) {
            return null;
        }
        if (argTypes == null || argTypes.length == 0) {
            for (MethodInfo method : allOverloads) {
                if (method.getParameterCount() != 0) continue;
                return method;
            }
            return allOverloads.get(0);
        }
        for (MethodInfo method : allOverloads) {
            if (method.getParameterCount() != argTypes.length) continue;
            boolean exactMatch = true;
            params = method.getParameters();
            for (i = 0; i < argTypes.length; ++i) {
                paramType = params.get(i).getTypeInfo();
                argType = argTypes[i];
                if (paramType != null && argType != null && paramType.equals(argType)) continue;
                exactMatch = false;
                break;
            }
            if (!exactMatch) continue;
            return method;
        }
        for (MethodInfo method : allOverloads) {
            if (method.getParameterCount() != argTypes.length) continue;
            boolean compatible = true;
            params = method.getParameters();
            for (i = 0; i < argTypes.length; ++i) {
                paramType = params.get(i).getTypeInfo();
                argType = argTypes[i];
                if (paramType == null || argType == null || TypeChecker.isTypeCompatible(paramType, argType)) continue;
                compatible = false;
                break;
            }
            if (!compatible) continue;
            return method;
        }
        for (MethodInfo method : allOverloads) {
            if (method.getParameterCount() != argTypes.length) continue;
            return method;
        }
        return allOverloads.get(0);
    }

    @Override
    public MethodInfo getBestMethodOverload(String methodName, TypeInfo expectedReturnType) {
        List<MethodInfo> allOverloads = this.getAllMethodOverloadsInHierarchy(methodName);
        if (allOverloads.isEmpty()) {
            return null;
        }
        if (expectedReturnType == null) {
            return allOverloads.get(0);
        }
        for (MethodInfo method : allOverloads) {
            TypeInfo returnType = method.getReturnType();
            if (returnType == null || !TypeChecker.isTypeCompatible(expectedReturnType, returnType)) continue;
            return method;
        }
        return allOverloads.get(0);
    }

    public String getDotSeparatedName() {
        if (this.outerClass == null) {
            return this.getSimpleName();
        }
        return this.outerClass.getDotSeparatedName() + "." + this.getSimpleName();
    }

    public ScriptTypeInfo getInnerClass(String name) {
        for (ScriptTypeInfo inner : this.innerClasses) {
            if (!inner.getSimpleName().equals(name)) continue;
            return inner;
        }
        return null;
    }

    @Override
    public boolean isResolved() {
        return true;
    }

    @Override
    public Class<?> getJavaClass() {
        return null;
    }

    @Override
    public String toString() {
        return "ScriptTypeInfo{" + this.scriptClassName + ", " + (Object)((Object)this.getKind()) + ", fields=" + this.fields.size() + ", methods=" + this.methods.size() + "}";
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean hasError() {
        return this.errorType != ErrorType.NONE || !this.missingMethodErrors.isEmpty() || !this.constructorMismatchErrors.isEmpty();
    }

    public List<MissingMethodError> getMissingMethodErrors() {
        return new ArrayList<MissingMethodError>(this.missingMethodErrors);
    }

    public List<ConstructorMismatchError> getConstructorMismatchErrors() {
        return new ArrayList<ConstructorMismatchError>(this.constructorMismatchErrors);
    }

    public void setError(ErrorType type, String message) {
        this.errorType = type;
        this.errorMessage = message;
    }

    public void addMissingMethodError(TypeInfo interfaceType, String signature) {
        this.missingMethodErrors.add(new MissingMethodError(interfaceType, signature));
    }

    public void addConstructorMismatchError(TypeInfo parentType, String parentConstructorSignature) {
        this.constructorMismatchErrors.add(new ConstructorMismatchError(parentType, parentConstructorSignature));
    }

    public void clearErrors() {
        this.errorType = ErrorType.NONE;
        this.errorMessage = null;
        this.missingMethodErrors.clear();
        this.constructorMismatchErrors.clear();
    }

    @Override
    public void validate() {
        if (this.hasSuperClass()) {
            TypeInfo superClass = this.getSuperClass();
            if (superClass == null || !superClass.isResolved()) {
                this.setError(ErrorType.UNRESOLVED_PARENT, "Cannot resolve parent class " + this.getSuperClassName());
            } else {
                this.validateConstructorChain(superClass);
            }
        }
        if (this.getKind() == TypeInfo.Kind.INTERFACE) {
            return;
        }
        if (this.hasImplementedInterfaces()) {
            for (TypeInfo iface : this.getImplementedInterfaces()) {
                if (iface == null || !iface.isResolved()) {
                    this.setError(ErrorType.UNRESOLVED_INTERFACE, "Cannot resolve interface");
                    continue;
                }
                this.validateInterfaceImplementation(iface);
            }
        }
    }

    private void validateInterfaceImplementation(TypeInfo iface) {
        Class<?> javaClass = iface.getJavaClass();
        if (javaClass != null && javaClass.isInterface()) {
            try {
                for (Method javaMethod : javaClass.getMethods()) {
                    if (Modifier.isStatic(javaMethod.getModifiers())) continue;
                    String methodName = javaMethod.getName();
                    int paramCount = javaMethod.getParameterCount();
                    boolean found = false;
                    List<MethodInfo> overloads = this.getAllMethodOverloads(methodName);
                    for (MethodInfo method : overloads) {
                        if (!this.parameterTypesMatch(method, javaMethod)) continue;
                        found = true;
                        break;
                    }
                    if (found || !this.missingMethodErrors.isEmpty()) continue;
                    this.addMissingMethodError(iface, MethodSignature.asString(javaMethod));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return;
        }
        if (iface instanceof ScriptTypeInfo) {
            ScriptTypeInfo ifaceType = (ScriptTypeInfo)iface;
            for (MethodInfo ifaceMethod : ifaceType.getAllMethodsFlat()) {
                MethodSignature ifaceSignature = ifaceMethod.getSignature();
                String methodName = ifaceMethod.getName();
                boolean found = false;
                List<MethodInfo> overloads = this.getAllMethodOverloads(methodName);
                for (MethodInfo method : overloads) {
                    if (!method.getSignature().equals(ifaceSignature)) continue;
                    found = true;
                    break;
                }
                if (found || !this.missingMethodErrors.isEmpty()) continue;
                this.addMissingMethodError(iface, ifaceSignature.toString());
            }
        }
    }

    private void validateConstructorChain(TypeInfo superClass) {
        if (!this.hasConstructors()) {
            if (superClass instanceof ScriptTypeInfo) {
                ScriptTypeInfo parentScript = (ScriptTypeInfo)superClass;
                if (!(parentScript.isInterface() || parentScript.hasConstructors() && parentScript.findConstructor(0) == null)) {
                    this.addConstructorMismatchError(superClass, superClass.getSimpleName());
                }
            } else {
                Class<?> javaClass = superClass.getJavaClass();
                if (javaClass != null && !javaClass.isInterface()) {
                    try {
                        for (Constructor<?> ctor : javaClass.getConstructors()) {
                            if (ctor.getParameterCount() != 0) continue;
                            this.addConstructorMismatchError(superClass, superClass.getSimpleName());
                            break;
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
        }
    }

    private boolean parameterTypesMatch(MethodInfo methodInfo, Method javaMethod) {
        List<FieldInfo> params = methodInfo.getParameters();
        Class<?>[] javaParams = javaMethod.getParameterTypes();
        if (params.size() != javaParams.length) {
            return false;
        }
        for (int i = 0; i < params.size(); ++i) {
            TypeInfo paramType = params.get(i).getTypeInfo();
            if (paramType == null) continue;
            Class<?> javaParamClass = javaParams[i];
            String javaParamName = javaParamClass.getName();
            if (paramType.getFullName().equals(javaParamName) || paramType.getSimpleName().equals(javaParamClass.getSimpleName())) continue;
            return false;
        }
        return true;
    }

    public static class ConstructorMismatchError {
        private final TypeInfo parentType;
        private final String parentConstructorSignature;

        public ConstructorMismatchError(TypeInfo parentType, String parentConstructorSignature) {
            this.parentType = parentType;
            this.parentConstructorSignature = parentConstructorSignature;
        }

        public String getMessage() {
            return "Class extends " + this.parentType.getSimpleName() + " but has no constructor matching " + this.parentConstructorSignature;
        }
    }

    public static class MissingMethodError {
        private final TypeInfo interfaceType;
        private final String signature;

        public MissingMethodError(TypeInfo interfaceType, String signature) {
            this.interfaceType = interfaceType;
            this.signature = signature;
        }

        public String getMessage() {
            return "Class must implement method '" + this.signature + "' from interface " + this.interfaceType.getSimpleName();
        }
    }

    public static enum ErrorType {
        NONE,
        MISSING_INTERFACE_METHOD,
        MISSING_CONSTRUCTOR_MATCH,
        UNRESOLVED_PARENT,
        UNRESOLVED_INTERFACE;

    }
}

