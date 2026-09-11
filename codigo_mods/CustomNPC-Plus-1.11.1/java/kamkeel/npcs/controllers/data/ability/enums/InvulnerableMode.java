/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;

public enum InvulnerableMode {
    NONE,
    WINDUP,
    ACTIVE,
    BOTH;


    public boolean invulnerableDuringWindup() {
        return this == WINDUP || this == BOTH;
    }

    public boolean invulnerableDuringActive() {
        return this == ACTIVE || this == BOTH;
    }

    public boolean isInvulnerableInPhase(AbilityPhase phase) {
        if (phase == null) {
            return false;
        }
        switch (phase) {
            case WINDUP: 
            case BURST_DELAY: {
                return this.invulnerableDuringWindup();
            }
            case ACTIVE: {
                return this.invulnerableDuringActive();
            }
        }
        return false;
    }

    public String getDisplayKey() {
        switch (this) {
            case WINDUP: {
                return "ability.invulnerable.windup";
            }
            case ACTIVE: {
                return "ability.invulnerable.active";
            }
            case BOTH: {
                return "ability.invulnerable.both";
            }
        }
        return "ability.invulnerable.none";
    }

    public static String[] getDisplayKeys() {
        return new String[]{"ability.invulnerable.none", "ability.invulnerable.windup", "ability.invulnerable.active", "ability.invulnerable.both"};
    }

    public static InvulnerableMode fromOrdinal(int ordinal) {
        InvulnerableMode[] values = InvulnerableMode.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return NONE;
    }
}

