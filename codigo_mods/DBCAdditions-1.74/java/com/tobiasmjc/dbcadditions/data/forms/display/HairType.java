/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.data.forms.display;

public enum HairType {
    BASE,
    SS1,
    SS2,
    SS3,
    SS4,
    CUSTOM;

    public static final int SS1_HAIR_ID = 1;
    public static final int SS2_HAIR_ID = 5;
    public static final int SS3_HAIR_ID = 7;
    public static final int SS4_HAIR_ID = 14;

    public static int getIDFromType(HairType type) {
        if (type == SS1) {
            return 1;
        }
        if (type == SS2) {
            return 5;
        }
        if (type == SS3) {
            return 7;
        }
        if (type == SS4) {
            return 14;
        }
        return 0;
    }

    public static String getIDFromType2(HairType type) {
        if (type == SS1) {
            return "B";
        }
        if (type == SS2) {
            return "C";
        }
        if (type == SS3) {
            return "D";
        }
        return "A";
    }
}

