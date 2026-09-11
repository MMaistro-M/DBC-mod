/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

public interface IHookDefinition {
    public String hookName();

    public String eventClassName();

    public String[] paramNames();

    public String[] requiredImports();

    public boolean isCancelable();

    default public Class<?> getEventClass() {
        String className = this.eventClassName();
        if (className == null || className.isEmpty()) {
            return null;
        }
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    default public String getUsableTypeName() {
        String className = this.eventClassName();
        if (className == null || className.isEmpty()) {
            return null;
        }
        int lastDot = className.lastIndexOf(46);
        if (lastDot < 0) {
            return className.replace('$', '.');
        }
        String simplePart = className.substring(lastDot + 1);
        return simplePart.replace('$', '.');
    }
}

