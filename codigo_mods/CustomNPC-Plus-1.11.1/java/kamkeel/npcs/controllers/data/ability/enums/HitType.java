/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum HitType {
    SINGLE,
    PIERCE,
    MULTI;


    public static HitType fromOrdinal(int ordinal) {
        HitType[] values = HitType.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return SINGLE;
    }

    public String toString() {
        switch (this) {
            case SINGLE: {
                return "ability.hitType.single";
            }
            case PIERCE: {
                return "ability.hitType.pierce";
            }
            case MULTI: {
                return "ability.hitType.multi";
            }
        }
        return this.name();
    }
}

