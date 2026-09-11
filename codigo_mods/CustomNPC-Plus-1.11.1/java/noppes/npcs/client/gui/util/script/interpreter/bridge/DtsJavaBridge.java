/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.bridge;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public final class DtsJavaBridge {
    private static final Map<Method, JSMethodInfo> METHOD_CACHE = new ConcurrentHashMap<Method, JSMethodInfo>();
    private static final Map<Field, JSFieldInfo> FIELD_CACHE = new ConcurrentHashMap<Field, JSFieldInfo>();
    private static final Map<Method, TypeInfo> RETURN_OVERRIDE_CACHE = new ConcurrentHashMap<Method, TypeInfo>();
    private static final Set<Method> RETURN_OVERRIDE_MISSES = ConcurrentHashMap.newKeySet();

    private DtsJavaBridge() {
    }

    public static void clearCache() {
        METHOD_CACHE.clear();
        FIELD_CACHE.clear();
        RETURN_OVERRIDE_CACHE.clear();
        RETURN_OVERRIDE_MISSES.clear();
    }

    public static TypeInfo resolveReturnTypeOverride(Method method, TypeInfo containingType, JSMethodInfo jsMethod) {
        if (method == null || jsMethod == null) {
            return null;
        }
        TypeInfo cached = RETURN_OVERRIDE_CACHE.get(method);
        if (cached != null) {
            return cached;
        }
        if (RETURN_OVERRIDE_MISSES.contains(method)) {
            return null;
        }
        String jsReturnType = jsMethod.getReturnType();
        if (jsReturnType == null || jsReturnType.isEmpty()) {
            RETURN_OVERRIDE_MISSES.add(method);
            return null;
        }
        String normalizedJsReturn = jsReturnType;
        if (normalizedJsReturn.contains("|")) {
            normalizedJsReturn = normalizedJsReturn.split("\\|")[0].trim();
        }
        normalizedJsReturn = normalizedJsReturn.replace("?", "").trim();
        Class<?> reflectedReturn = method.getReturnType();
        if (reflectedReturn != null) {
            if (normalizedJsReturn.equals(reflectedReturn.getName()) || normalizedJsReturn.equals(reflectedReturn.getSimpleName()) || normalizedJsReturn.equals("Java." + reflectedReturn.getName())) {
                RETURN_OVERRIDE_MISSES.add(method);
                return null;
            }
            String lower = normalizedJsReturn.toLowerCase();
            if ("void".equals(lower) && (reflectedReturn == Void.TYPE || reflectedReturn == Void.class) || "boolean".equals(lower) && (reflectedReturn == Boolean.TYPE || reflectedReturn == Boolean.class) || "string".equals(lower) && reflectedReturn == String.class || "number".equals(lower) && (reflectedReturn == Double.TYPE || reflectedReturn == Double.class)) {
                RETURN_OVERRIDE_MISSES.add(method);
                return null;
            }
        }
        TypeResolver resolver = TypeResolver.getInstance();
        TypeInfo resolved = resolver.resolveJSType(normalizedJsReturn);
        TypeInfo javaResolved = null;
        if (resolved != null) {
            String javaFqn;
            if (resolved.getJavaClass() != null) {
                javaResolved = resolved;
            } else if (resolved.isJSType() && resolved.getJSTypeInfo() != null && (javaFqn = resolved.getJSTypeInfo().getJavaFqn()) != null && !javaFqn.isEmpty()) {
                javaResolved = resolver.resolveFullName(javaFqn);
            }
        }
        if (javaResolved == null || javaResolved.getJavaClass() == null) {
            RETURN_OVERRIDE_MISSES.add(method);
            return null;
        }
        RETURN_OVERRIDE_CACHE.put(method, javaResolved);
        return javaResolved;
    }

    public static JSMethodInfo findMatchingMethod(Method method, TypeInfo containingType) {
        JSMethodInfo cached = METHOD_CACHE.get(method);
        if (cached != null) {
            return cached;
        }
        JSTypeInfo jsType = DtsJavaBridge.findJSTypeInfo(method, containingType);
        if (jsType == null) {
            return null;
        }
        List<JSMethodInfo> overloads = jsType.getMethodOverloads(method.getName());
        if (overloads.isEmpty()) {
            return null;
        }
        Class<?>[] paramTypes = method.getParameterTypes();
        JSMethodInfo best = null;
        int bestScore = -1;
        for (JSMethodInfo candidate : overloads) {
            int score;
            if (candidate.getParameterCount() != paramTypes.length || (score = DtsJavaBridge.scoreOverload(candidate, paramTypes, containingType, method.isVarArgs())) <= bestScore) continue;
            bestScore = score;
            best = candidate;
        }
        if (bestScore >= 0 && best != null) {
            METHOD_CACHE.put(method, best);
            return best;
        }
        return null;
    }

    public static JSFieldInfo findMatchingField(Field field, TypeInfo containingType) {
        JSFieldInfo cached = FIELD_CACHE.get(field);
        if (cached != null) {
            return cached;
        }
        JSTypeInfo jsType = DtsJavaBridge.findJSTypeInfo(field, containingType);
        if (jsType == null) {
            return null;
        }
        JSFieldInfo match = jsType.getField(field.getName());
        if (match != null) {
            FIELD_CACHE.put(field, match);
        }
        return match;
    }

    private static int scoreOverload(JSMethodInfo method, Class<?>[] paramTypes, TypeInfo containingType, boolean isVarArgs) {
        int score = 0;
        List<JSMethodInfo.JSParameterInfo> jsParams = method.getParameters();
        for (int i = 0; i < paramTypes.length; ++i) {
            int paramScore = DtsJavaBridge.scoreParam(paramTypes[i], jsParams.get(i), containingType, isVarArgs, i == paramTypes.length - 1);
            if (paramScore < 0) {
                return -1;
            }
            score += paramScore;
        }
        return score;
    }

    private static int scoreParam(Class<?> javaParam, JSMethodInfo.JSParameterInfo jsParam, TypeInfo containingType, boolean isVarArgs, boolean isLastParam) {
        String paramFqn;
        String javaFqn;
        String jsTypeName;
        if (javaParam == null || jsParam == null) {
            return -1;
        }
        TypeInfo resolved = jsParam.getResolvedType(containingType);
        if (resolved != null && resolved.getJavaClass() != null) {
            Class<?> jsClass = resolved.getJavaClass();
            if (javaParam.equals(jsClass)) {
                return 4;
            }
            if (jsClass.isAssignableFrom(javaParam)) {
                return 3;
            }
            if (javaParam.isAssignableFrom(jsClass)) {
                return 2;
            }
        }
        if ((jsTypeName = jsParam.getType()) == null || jsTypeName.isEmpty()) {
            return -1;
        }
        if (isVarArgs && isLastParam && javaParam.isArray() && DtsJavaBridge.matchesArrayElement(javaParam.getComponentType(), jsTypeName, containingType)) {
            return 2;
        }
        if (DtsJavaBridge.matchesPrimitive(jsTypeName, javaParam)) {
            return 3;
        }
        if (javaParam.isArray() && jsTypeName.endsWith("[]")) {
            String elementType = jsTypeName.substring(0, jsTypeName.length() - 2);
            return DtsJavaBridge.matchesArrayElement(javaParam.getComponentType(), elementType, containingType) ? 2 : -1;
        }
        JSTypeInfo jsType = DtsJavaBridge.resolveJSTypeInfo(jsTypeName);
        if (jsType != null && jsType.getJavaFqn() != null && (javaFqn = DtsJavaBridge.normalizeJavaFqn(jsType.getJavaFqn())).equals(paramFqn = DtsJavaBridge.normalizeJavaFqn(javaParam.getName()))) {
            return 3;
        }
        if (jsTypeName.equals(javaParam.getSimpleName())) {
            return 1;
        }
        if (jsTypeName.equals(javaParam.getName())) {
            return 1;
        }
        if ("any".equals(jsTypeName)) {
            return 1;
        }
        return -1;
    }

    private static boolean matchesArrayElement(Class<?> elementClass, String jsElementType, TypeInfo containingType) {
        if (elementClass == null) {
            return false;
        }
        if (DtsJavaBridge.matchesPrimitive(jsElementType, elementClass)) {
            return true;
        }
        JSTypeInfo jsType = DtsJavaBridge.resolveJSTypeInfo(jsElementType);
        if (jsType != null && jsType.getJavaFqn() != null) {
            String javaFqn = DtsJavaBridge.normalizeJavaFqn(jsType.getJavaFqn());
            String elementFqn = DtsJavaBridge.normalizeJavaFqn(elementClass.getName());
            return javaFqn.equals(elementFqn);
        }
        return jsElementType.equals(elementClass.getSimpleName()) || jsElementType.equals(elementClass.getName());
    }

    private static boolean matchesPrimitive(String jsTypeName, Class<?> javaParam) {
        switch (jsTypeName.toLowerCase()) {
            case "string": 
            case "char": {
                return javaParam == String.class || javaParam == Character.TYPE || javaParam == Character.class;
            }
            case "boolean": 
            case "bool": {
                return javaParam == Boolean.TYPE || javaParam == Boolean.class;
            }
            case "number": 
            case "byte": 
            case "short": 
            case "int": 
            case "integer": 
            case "long": 
            case "float": 
            case "double": {
                return DtsJavaBridge.isNumberType(javaParam);
            }
            case "void": {
                return javaParam == Void.TYPE || javaParam == Void.class;
            }
        }
        return false;
    }

    private static boolean isNumberType(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type == Byte.TYPE || type == Short.TYPE || type == Integer.TYPE || type == Long.TYPE || type == Float.TYPE || type == Double.TYPE || Number.class.isAssignableFrom(type);
    }

    private static JSTypeInfo findJSTypeInfo(Method method, TypeInfo containingType) {
        if (containingType != null && containingType.isJSType()) {
            return containingType.getJSTypeInfo();
        }
        Class<?> javaClass = containingType != null ? containingType.getJavaClass() : method.getDeclaringClass();
        return DtsJavaBridge.resolveJSTypeInfo(javaClass);
    }

    private static JSTypeInfo findJSTypeInfo(Field field, TypeInfo containingType) {
        if (containingType != null && containingType.isJSType()) {
            return containingType.getJSTypeInfo();
        }
        Class<?> javaClass = containingType != null ? containingType.getJavaClass() : field.getDeclaringClass();
        return DtsJavaBridge.resolveJSTypeInfo(javaClass);
    }

    private static JSTypeInfo resolveJSTypeInfo(Class<?> javaClass) {
        if (javaClass == null) {
            return null;
        }
        JSTypeInfo direct = DtsJavaBridge.resolveJSTypeInfo(javaClass.getName());
        if (direct != null) {
            return direct;
        }
        for (Class<?> iface : javaClass.getInterfaces()) {
            JSTypeInfo ifaceType = DtsJavaBridge.resolveJSTypeInfo(iface.getName());
            if (ifaceType == null) continue;
            return ifaceType;
        }
        Class<?> superClass = javaClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            return DtsJavaBridge.resolveJSTypeInfo(superClass);
        }
        return null;
    }

    private static JSTypeInfo resolveJSTypeInfo(String javaFqnOrType) {
        JSTypeInfo normalizedType;
        JSTypeRegistry registry;
        JSTypeInfo direct;
        if (javaFqnOrType == null || javaFqnOrType.isEmpty()) {
            return null;
        }
        if (javaFqnOrType.startsWith("Java.")) {
            javaFqnOrType = javaFqnOrType.substring(5);
        }
        if ((direct = (registry = TypeResolver.getInstance().getJSTypeRegistry()).getTypeByJavaFqn(javaFqnOrType)) != null) {
            return direct;
        }
        String normalized = DtsJavaBridge.normalizeJavaFqn(javaFqnOrType);
        if (!normalized.equals(javaFqnOrType) && (normalizedType = registry.getTypeByJavaFqn(normalized)) != null) {
            return normalizedType;
        }
        return registry.getType(javaFqnOrType);
    }

    private static String normalizeJavaFqn(String javaFqn) {
        if (javaFqn == null) {
            return null;
        }
        return javaFqn.replace('$', '.');
    }
}

