/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.painting;

import riskyken.armourersWorkshop.utils.BitwiseUtils;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public enum PaintType {
    NORMAL(255),
    DYE_1(1),
    DYE_2(2),
    DYE_3(3),
    DYE_4(4),
    DYE_5(5),
    DYE_6(6),
    DYE_7(7),
    DYE_8(8),
    NONE(0),
    SKIN(253),
    HAIR(254),
    EYES(251),
    MISC(252);

    private final int key;

    private PaintType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public static PaintType getPaintTypeFormSKey(byte key) {
        int uKey = key & 0xFF;
        return PaintType.getPaintTypeFromUKey(uKey);
    }

    public static PaintType getPaintTypeFromColour(int colour) {
        int uKey = colour >>> 24;
        return PaintType.getPaintTypeFromUKey(uKey);
    }

    public static PaintType getPaintTypeFromUKey(int uKey) {
        for (int i = 0; i < PaintType.values().length; ++i) {
            if (PaintType.values()[i].key != uKey) continue;
            return PaintType.values()[i];
        }
        return NORMAL;
    }

    public static int setPaintTypeOnColour(PaintType paintType, int colour) {
        return BitwiseUtils.setUByteToInt(colour, 0, paintType.key);
    }

    public String getLocalizedName() {
        String unlocalizedText = "paintType." + "armourersWorkshop".toLowerCase() + ":";
        unlocalizedText = unlocalizedText + this.name().toLowerCase() + ".name";
        return TranslateUtils.translate(unlocalizedText);
    }
}

