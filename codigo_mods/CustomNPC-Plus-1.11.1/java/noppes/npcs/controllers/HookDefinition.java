/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.controllers;

import cpw.mods.fml.common.eventhandler.Cancelable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import noppes.npcs.api.handler.IHookDefinition;
import noppes.npcs.janino.annotations.ParamName;

public class HookDefinition
implements IHookDefinition {
    private final String hookName;
    private final String eventClassName;
    private final String[] paramNames;
    private final String[] requiredImports;
    private final boolean cancelable;
    private transient Class<?> cachedEventClass;
    private transient boolean classResolutionAttempted;

    private HookDefinition(Builder builder) {
        String[] stringArray;
        this.hookName = builder.hookName;
        this.eventClassName = builder.eventClassName;
        if (builder.paramNames != null) {
            stringArray = builder.paramNames;
        } else {
            String[] stringArray2 = new String[1];
            stringArray = stringArray2;
            stringArray2[0] = "event";
        }
        this.paramNames = stringArray;
        this.requiredImports = builder.requiredImports != null ? builder.requiredImports : new String[]{};
        this.cancelable = builder.cancelable;
    }

    @Override
    public String hookName() {
        return this.hookName;
    }

    @Override
    public String eventClassName() {
        return this.eventClassName;
    }

    @Override
    public String[] paramNames() {
        return this.paramNames;
    }

    @Override
    public String[] requiredImports() {
        return this.requiredImports;
    }

    @Override
    public boolean isCancelable() {
        return this.cancelable;
    }

    @Override
    public Class<?> getEventClass() {
        if (this.classResolutionAttempted) {
            return this.cachedEventClass;
        }
        this.classResolutionAttempted = true;
        if (this.eventClassName == null || this.eventClassName.isEmpty()) {
            return null;
        }
        try {
            this.cachedEventClass = Class.forName(this.eventClassName);
        }
        catch (ClassNotFoundException e) {
            this.cachedEventClass = null;
        }
        return this.cachedEventClass;
    }

    public static HookDefinition of(String hookName, Class<?> eventClass) {
        return HookDefinition.builder(hookName).eventClass(eventClass).build();
    }

    public static HookDefinition simple(String hookName) {
        return HookDefinition.builder(hookName).build();
    }

    public static Builder builder(String hookName) {
        return new Builder(hookName);
    }

    public static HookDefinition fromMethod(String hookName, Method method) {
        Parameter[] params;
        Builder builder = HookDefinition.builder(hookName);
        if (method.getParameterCount() > 0) {
            String[] importNames;
            Class<?> eventType = method.getParameterTypes()[0];
            builder.eventClass(eventType);
            if (eventType.isAnnotationPresent(Cancelable.class)) {
                builder.cancelable(true);
            }
            if ((importNames = HookDefinition.getImportsForClass(eventType)).length > 0) {
                builder.requiredImports(importNames);
            }
        }
        if ((params = method.getParameters()).length > 0) {
            String[] names = new String[params.length];
            for (int i = 0; i < params.length; ++i) {
                ParamName annotation = params[i].getAnnotation(ParamName.class);
                names[i] = annotation != null ? annotation.value() : Character.toLowerCase(params[i].getType().getSimpleName().charAt(0)) + params[i].getType().getSimpleName().substring(1);
            }
            builder.paramNames(names);
        }
        return builder.build();
    }

    public static HookDefinition fromMethod(Method method) {
        return HookDefinition.fromMethod(method.getName(), method);
    }

    private static String[] getImportsForClass(Class<?> clazz) {
        if (clazz == null || clazz.isPrimitive()) {
            return new String[0];
        }
        String fullName = clazz.getName();
        if (fullName.startsWith("java.lang.")) {
            return new String[0];
        }
        Class<?> enclosing = clazz;
        while (enclosing.getEnclosingClass() != null) {
            enclosing = enclosing.getEnclosingClass();
        }
        String enclosingName = enclosing.getName();
        String nestedName = fullName.replace('$', '.');
        if (!nestedName.equals(enclosingName)) {
            return new String[]{enclosingName, nestedName};
        }
        return new String[]{enclosingName};
    }

    public String toString() {
        return "HookDefinition{hookName='" + this.hookName + "', eventClass='" + this.eventClassName + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HookDefinition)) {
            return false;
        }
        return this.hookName.equals(((HookDefinition)obj).hookName);
    }

    public int hashCode() {
        return this.hookName.hashCode();
    }

    public static class Builder {
        private final String hookName;
        private String eventClassName;
        private String[] paramNames;
        private String[] requiredImports;
        private boolean cancelable;

        private Builder(String hookName) {
            if (hookName == null || hookName.isEmpty()) {
                throw new IllegalArgumentException("hookName cannot be null or empty");
            }
            this.hookName = hookName;
        }

        public Builder eventClass(Class<?> clazz) {
            if (clazz != null) {
                this.eventClassName = clazz.getName();
                String[] importNames = HookDefinition.getImportsForClass(clazz);
                if (importNames.length > 0) {
                    this.requiredImports = importNames;
                }
                if (clazz.isAnnotationPresent(Cancelable.class)) {
                    this.cancelable = true;
                }
            }
            return this;
        }

        public Builder eventClass(String className) {
            this.eventClassName = className;
            return this;
        }

        public Builder paramNames(String ... names) {
            this.paramNames = names;
            return this;
        }

        public Builder requiredImports(String ... imports) {
            this.requiredImports = imports;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public HookDefinition build() {
            return new HookDefinition(this);
        }
    }
}

