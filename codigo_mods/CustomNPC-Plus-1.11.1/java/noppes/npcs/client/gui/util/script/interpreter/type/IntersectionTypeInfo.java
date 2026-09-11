/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class IntersectionTypeInfo
extends TypeInfo {
    private final TypeInfo primaryBound;
    private final List<TypeInfo> additionalBounds;

    private IntersectionTypeInfo(TypeInfo primaryBound, List<TypeInfo> additionalBounds) {
        super(primaryBound.getSimpleName(), primaryBound.getFullName(), "", primaryBound.getKind(), primaryBound.getJavaClass(), true, null, true);
        this.primaryBound = primaryBound;
        this.additionalBounds = additionalBounds;
    }

    public static TypeInfo of(TypeInfo primary, List<TypeInfo> additional) {
        if (additional == null || additional.isEmpty()) {
            return primary;
        }
        return new IntersectionTypeInfo(primary, new ArrayList<TypeInfo>(additional));
    }

    @Override
    public boolean hasMethod(String methodName) {
        if (this.primaryBound.hasMethod(methodName)) {
            return true;
        }
        for (TypeInfo bound : this.additionalBounds) {
            if (!bound.hasMethod(methodName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasMethod(String methodName, int paramCount) {
        if (this.primaryBound.hasMethod(methodName, paramCount)) {
            return true;
        }
        for (TypeInfo bound : this.additionalBounds) {
            if (!bound.hasMethod(methodName, paramCount)) continue;
            return true;
        }
        return false;
    }

    @Override
    public MethodInfo getMethodInfo(String methodName) {
        MethodInfo m = this.primaryBound.getMethodInfo(methodName);
        if (m != null) {
            return m;
        }
        for (TypeInfo bound : this.additionalBounds) {
            m = bound.getMethodInfo(methodName);
            if (m == null) continue;
            return m;
        }
        return null;
    }

    @Override
    public List<MethodInfo> getAllMethodOverloads(String methodName) {
        LinkedHashSet<MethodInfo> seen = new LinkedHashSet<MethodInfo>();
        seen.addAll(this.primaryBound.getAllMethodOverloads(methodName));
        for (TypeInfo bound : this.additionalBounds) {
            seen.addAll(bound.getAllMethodOverloads(methodName));
        }
        return new ArrayList<MethodInfo>(seen);
    }

    @Override
    public MethodInfo getBestMethodOverload(String methodName, TypeInfo[] argTypes) {
        MethodInfo m = this.primaryBound.getBestMethodOverload(methodName, argTypes);
        if (m != null) {
            return m;
        }
        for (TypeInfo bound : this.additionalBounds) {
            m = bound.getBestMethodOverload(methodName, argTypes);
            if (m == null) continue;
            return m;
        }
        return null;
    }

    @Override
    public MethodInfo getBestMethodOverload(String methodName, TypeInfo expectedReturnType) {
        MethodInfo m = this.primaryBound.getBestMethodOverload(methodName, expectedReturnType);
        if (m != null) {
            return m;
        }
        for (TypeInfo bound : this.additionalBounds) {
            m = bound.getBestMethodOverload(methodName, expectedReturnType);
            if (m == null) continue;
            return m;
        }
        return null;
    }

    @Override
    public boolean hasField(String fieldName) {
        if (this.primaryBound.hasField(fieldName)) {
            return true;
        }
        for (TypeInfo bound : this.additionalBounds) {
            if (!bound.hasField(fieldName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public FieldInfo getFieldInfo(String fieldName) {
        FieldInfo f = this.primaryBound.getFieldInfo(fieldName);
        if (f != null) {
            return f;
        }
        for (TypeInfo bound : this.additionalBounds) {
            f = bound.getFieldInfo(fieldName);
            if (f == null) continue;
            return f;
        }
        return null;
    }

    public TypeInfo getPrimaryBound() {
        return this.primaryBound;
    }

    public List<TypeInfo> getAdditionalBounds() {
        return new ArrayList<TypeInfo>(this.additionalBounds);
    }

    @Override
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder(this.primaryBound.getDisplayName());
        for (TypeInfo bound : this.additionalBounds) {
            sb.append(" & ").append(bound.getDisplayName());
        }
        return sb.toString();
    }

    @Override
    public boolean isResolved() {
        return true;
    }

    @Override
    public String toString() {
        return "IntersectionTypeInfo{" + this.getDisplayName() + "}";
    }
}

