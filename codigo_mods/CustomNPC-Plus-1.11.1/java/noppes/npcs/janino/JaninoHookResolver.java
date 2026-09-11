/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.janino;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class JaninoHookResolver {
    private final Map<String, CachedHandle> handleCache = new HashMap<String, CachedHandle>();
    private Class<?> lastCompiledClass;
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    public void clearResolutionCaches() {
        this.handleCache.clear();
        this.lastCompiledClass = null;
    }

    public MethodHandle resolveHookHandle(String hookName, Object[] args, Object compiledInstance) {
        if (hookName == null || hookName.isEmpty() || compiledInstance == null) {
            return null;
        }
        Class<?> compiledClass = compiledInstance.getClass();
        if (this.lastCompiledClass != compiledClass) {
            this.handleCache.clear();
            this.lastCompiledClass = compiledClass;
        }
        int argCount = args == null ? 0 : args.length;
        CachedHandle cached = this.handleCache.get(hookName);
        if (cached != null && cached.paramCount == argCount) {
            return cached.handle;
        }
        Method method = this.resolveMethod(compiledClass, hookName, args);
        if (method == null) {
            return null;
        }
        try {
            MethodHandle handle = LOOKUP.unreflect(method);
            this.handleCache.put(hookName, new CachedHandle(handle, method.getParameterCount()));
            return handle;
        }
        catch (IllegalAccessException e) {
            return null;
        }
    }

    private Method resolveMethod(Class<?> targetClass, String hookName, Object[] args) {
        Object[] safeArgs = args == null ? new Object[]{} : args;
        Method best = null;
        int bestScore = Integer.MAX_VALUE;
        for (Method method : targetClass.getMethods()) {
            int score;
            Class<?>[] paramTypes;
            if (!method.getName().equals(hookName) || Modifier.isFinal(method.getModifiers()) || method.getDeclaringClass() == Object.class || (paramTypes = method.getParameterTypes()).length != safeArgs.length || (score = JaninoHookResolver.scoreMethodMatch(paramTypes, safeArgs)) == Integer.MAX_VALUE || score >= bestScore) continue;
            bestScore = score;
            best = method;
            if (score != 0) continue;
            return best;
        }
        if (best == null && safeArgs.length > 0) {
            for (Method method : targetClass.getMethods()) {
                if (!method.getName().equals(hookName) || Modifier.isFinal(method.getModifiers()) || method.getDeclaringClass() == Object.class || method.getParameterCount() != 0) continue;
                return method;
            }
        }
        return best;
    }

    private static int scoreMethodMatch(Class<?>[] paramTypes, Object[] args) {
        int score = 0;
        for (int i = 0; i < paramTypes.length; ++i) {
            Class<?> paramType = paramTypes[i];
            Object arg = args[i];
            if (arg == null) {
                if (paramType.isPrimitive()) {
                    return Integer.MAX_VALUE;
                }
                ++score;
                continue;
            }
            Class<?> argType = arg.getClass();
            Class<?> boxedParam = JaninoHookResolver.boxType(paramType);
            if (boxedParam.equals(argType)) continue;
            if (boxedParam.isAssignableFrom(argType)) {
                score += JaninoHookResolver.inheritanceDistance(argType, boxedParam);
                continue;
            }
            return Integer.MAX_VALUE;
        }
        return score;
    }

    private static Class<?> boxType(Class<?> type) {
        if (!type.isPrimitive()) {
            return type;
        }
        if (type == Boolean.TYPE) {
            return Boolean.class;
        }
        if (type == Byte.TYPE) {
            return Byte.class;
        }
        if (type == Short.TYPE) {
            return Short.class;
        }
        if (type == Integer.TYPE) {
            return Integer.class;
        }
        if (type == Long.TYPE) {
            return Long.class;
        }
        if (type == Float.TYPE) {
            return Float.class;
        }
        if (type == Double.TYPE) {
            return Double.class;
        }
        if (type == Character.TYPE) {
            return Character.class;
        }
        return type;
    }

    private static int inheritanceDistance(Class<?> child, Class<?> parent) {
        if (child.equals(parent)) {
            return 0;
        }
        if (!parent.isAssignableFrom(child)) {
            return 1000;
        }
        int distance = 0;
        Class<?> current = child;
        while (current != null && !current.equals(parent)) {
            current = current.getSuperclass();
            ++distance;
        }
        return distance;
    }

    private static final class CachedHandle {
        final MethodHandle handle;
        final int paramCount;

        CachedHandle(MethodHandle handle, int paramCount) {
            this.handle = handle;
            this.paramCount = paramCount;
        }
    }
}

