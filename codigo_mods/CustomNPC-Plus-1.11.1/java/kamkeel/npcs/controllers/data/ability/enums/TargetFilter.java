/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum TargetFilter {
    ALLIES,
    ENEMIES,
    ALL;


    public String toString() {
        switch (this) {
            case ALLIES: {
                return "ability.targetFilter.allies";
            }
            case ENEMIES: {
                return "ability.targetFilter.enemies";
            }
            case ALL: {
                return "ability.targetFilter.all";
            }
        }
        return this.name();
    }

    public static TargetFilter fromString(String name) {
        try {
            return TargetFilter.valueOf(name);
        }
        catch (Exception e) {
            return ALLIES;
        }
    }
}

