/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum LockMode {
    NO,
    WINDUP,
    ACTIVE,
    WINDUP_AND_ACTIVE;


    public boolean locksWindup() {
        return this == WINDUP || this == WINDUP_AND_ACTIVE;
    }

    public boolean locksActive() {
        return this == ACTIVE || this == WINDUP_AND_ACTIVE;
    }

    public String getDisplayKey() {
        switch (this) {
            case NO: {
                return "gui.no";
            }
            case WINDUP: {
                return "ability.lockMove.windup";
            }
            case ACTIVE: {
                return "ability.lockMove.active";
            }
            case WINDUP_AND_ACTIVE: {
                return "ability.lockMove.both";
            }
        }
        return "gui.no";
    }

    public static String[] getDisplayKeys() {
        return new String[]{"gui.no", "ability.lockMove.windup", "ability.lockMove.active", "ability.lockMove.both"};
    }

    public static LockMode fromOrdinal(int ordinal) {
        LockMode[] values = LockMode.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return WINDUP;
    }
}

