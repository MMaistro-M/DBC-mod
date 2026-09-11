/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

public class ShortcutSpecification {
    private Action action;
    private Target target;
    private Scope scope;

    public ShortcutSpecification(Action a, Target t, Scope s) {
        this.action = a;
        this.target = t;
        this.scope = s;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Target getTarget() {
        return this.target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Scope getScope() {
        return this.scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public static enum Scope {
        EVERYTHING,
        ALL_ITEMS,
        ONE_STACK,
        ONE_ITEM;

    }

    public static enum Target {
        UP,
        DOWN,
        HOTBAR_SLOT,
        UNSPECIFIED;

    }

    public static enum Action {
        MOVE,
        DROP;

    }
}

