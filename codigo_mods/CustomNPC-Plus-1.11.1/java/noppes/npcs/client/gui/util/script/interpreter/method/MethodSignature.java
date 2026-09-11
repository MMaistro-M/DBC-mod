/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.method;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class MethodSignature {
    private final String methodName;
    private final List<TypeInfo> parameterTypes;

    public MethodSignature(String methodName, List<TypeInfo> parameterTypes) {
        this.methodName = methodName;
        this.parameterTypes = new ArrayList<TypeInfo>(parameterTypes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodSignature)) {
            return false;
        }
        MethodSignature other = (MethodSignature)obj;
        if (!this.methodName.equals(other.methodName)) {
            return false;
        }
        if (this.parameterTypes.size() != other.parameterTypes.size()) {
            return false;
        }
        for (int i = 0; i < this.parameterTypes.size(); ++i) {
            TypeInfo thisType = this.parameterTypes.get(i);
            TypeInfo otherType = other.parameterTypes.get(i);
            if (thisType == null && otherType == null) continue;
            if (thisType == null || otherType == null) {
                return false;
            }
            if (thisType.getFullName().equals(otherType.getFullName())) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.methodName.hashCode();
        for (TypeInfo paramType : this.parameterTypes) {
            result = 31 * result + (paramType != null ? paramType.getFullName().hashCode() : 0);
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.methodName);
        sb.append("(");
        for (int i = 0; i < this.parameterTypes.size(); ++i) {
            TypeInfo type;
            if (i > 0) {
                sb.append(", ");
            }
            sb.append((type = this.parameterTypes.get(i)) != null ? type.getFullName() : "?");
        }
        sb.append(")");
        return sb.toString();
    }

    public static String asString(Method javaMethod) {
        StringBuilder sig = new StringBuilder(javaMethod.getName());
        sig.append("(");
        Class<?>[] paramTypes = javaMethod.getParameterTypes();
        for (int i = 0; i < paramTypes.length; ++i) {
            if (i > 0) {
                sig.append(", ");
            }
            sig.append(paramTypes[i].getSimpleName());
        }
        sig.append(")");
        return sig.toString();
    }
}

