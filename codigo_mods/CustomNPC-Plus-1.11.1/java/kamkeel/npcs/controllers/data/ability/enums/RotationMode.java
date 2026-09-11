/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum RotationMode {
    FREE,
    LOCKED,
    TRACK;


    public String getDisplayKey() {
        switch (this) {
            case FREE: {
                return "ability.rotation.free";
            }
            case LOCKED: {
                return "ability.rotation.locked";
            }
            case TRACK: {
                return "ability.rotation.track";
            }
        }
        return "ability.rotation.free";
    }

    public static String[] getDisplayKeys() {
        return new String[]{"ability.rotation.free", "ability.rotation.locked", "ability.rotation.track"};
    }

    public static RotationMode fromOrdinal(int ordinal) {
        RotationMode[] values = RotationMode.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return FREE;
    }
}

