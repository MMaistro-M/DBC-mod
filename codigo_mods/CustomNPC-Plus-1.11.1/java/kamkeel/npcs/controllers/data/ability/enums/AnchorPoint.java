/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum AnchorPoint {
    FRONT("Front"),
    CENTER("Center"),
    RIGHT_HAND("Right Hand"),
    LEFT_HAND("Left Hand"),
    ABOVE_HEAD("Above Head"),
    CHEST("Chest"),
    EYE("Eye");

    private final String displayName;

    private AnchorPoint(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static AnchorPoint fromOrdinal(int ordinal) {
        AnchorPoint[] vals = AnchorPoint.values();
        if (ordinal < 0 || ordinal >= vals.length) {
            return FRONT;
        }
        return vals[ordinal];
    }

    public static String[] getDisplayNames() {
        AnchorPoint[] values = AnchorPoint.values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; ++i) {
            names[i] = values[i].displayName;
        }
        return names;
    }

    public String toString() {
        switch (this) {
            case FRONT: {
                return "ability.anchor.front";
            }
            case CENTER: {
                return "ability.anchor.center";
            }
            case RIGHT_HAND: {
                return "ability.anchor.rightHand";
            }
            case LEFT_HAND: {
                return "ability.anchor.leftHand";
            }
            case ABOVE_HEAD: {
                return "ability.anchor.aboveHead";
            }
            case CHEST: {
                return "ability.anchor.chest";
            }
            case EYE: {
                return "ability.anchor.eye";
            }
        }
        return this.name();
    }
}

