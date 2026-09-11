/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class GenericParameterAdapter {
    private static final List<Rule> RULES;

    public static TypeInfo adaptParameterType(Method method, TypeInfo receiverType, int paramIndex, TypeInfo currentType) {
        if (receiverType == null || !receiverType.isParameterized()) {
            return null;
        }
        if (currentType == null || currentType.getJavaClass() != Object.class) {
            return null;
        }
        List<TypeInfo> appliedArgs = receiverType.getAppliedTypeArgs();
        if (appliedArgs == null || appliedArgs.isEmpty()) {
            return null;
        }
        Class<?> declaringClass = method.getDeclaringClass();
        String methodName = method.getName();
        int paramCount = method.getParameterCount();
        for (Rule rule : RULES) {
            TypeInfo adapted;
            if (rule.paramIndex != paramIndex || rule.paramCount != paramCount || !rule.methodName.equals(methodName) || !rule.contractType.isAssignableFrom(declaringClass) || rule.typeArgIndex >= appliedArgs.size() || (adapted = appliedArgs.get(rule.typeArgIndex)) == null || !adapted.isResolved()) continue;
            return adapted;
        }
        return null;
    }

    private GenericParameterAdapter() {
    }

    static {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule(Map.class, "get", 1, 0, 0));
        rules.add(new Rule(Map.class, "containsKey", 1, 0, 0));
        rules.add(new Rule(Map.class, "containsValue", 1, 0, 1));
        rules.add(new Rule(Map.class, "remove", 1, 0, 0));
        rules.add(new Rule(Map.class, "remove", 2, 0, 0));
        rules.add(new Rule(Map.class, "remove", 2, 1, 1));
        rules.add(new Rule(Map.class, "getOrDefault", 2, 0, 0));
        rules.add(new Rule(ConcurrentMap.class, "remove", 2, 0, 0));
        rules.add(new Rule(ConcurrentMap.class, "remove", 2, 1, 1));
        rules.add(new Rule(ConcurrentMap.class, "getOrDefault", 2, 0, 0));
        rules.add(new Rule(Collection.class, "contains", 1, 0, 0));
        rules.add(new Rule(Collection.class, "remove", 1, 0, 0));
        rules.add(new Rule(List.class, "indexOf", 1, 0, 0));
        rules.add(new Rule(List.class, "lastIndexOf", 1, 0, 0));
        rules.add(new Rule(Deque.class, "contains", 1, 0, 0));
        rules.add(new Rule(Deque.class, "remove", 1, 0, 0));
        rules.add(new Rule(Deque.class, "removeFirstOccurrence", 1, 0, 0));
        rules.add(new Rule(Deque.class, "removeLastOccurrence", 1, 0, 0));
        rules.add(new Rule(BlockingQueue.class, "remove", 1, 0, 0));
        rules.add(new Rule(BlockingQueue.class, "contains", 1, 0, 0));
        rules.add(new Rule(BlockingDeque.class, "remove", 1, 0, 0));
        rules.add(new Rule(BlockingDeque.class, "contains", 1, 0, 0));
        rules.add(new Rule(BlockingDeque.class, "removeFirstOccurrence", 1, 0, 0));
        rules.add(new Rule(BlockingDeque.class, "removeLastOccurrence", 1, 0, 0));
        RULES = Collections.unmodifiableList(rules);
    }

    private static final class Rule {
        final Class<?> contractType;
        final String methodName;
        final int paramCount;
        final int paramIndex;
        final int typeArgIndex;

        Rule(Class<?> contractType, String methodName, int paramCount, int paramIndex, int typeArgIndex) {
            this.contractType = contractType;
            this.methodName = methodName;
            this.paramCount = paramCount;
            this.paramIndex = paramIndex;
            this.typeArgIndex = typeArgIndex;
        }
    }
}

