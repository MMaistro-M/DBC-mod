/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.wardrobe;

import java.awt.Color;
import java.util.Arrays;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;

public class ExtraColours {
    public static final Color COLOUR_SKIN_DEFAULT = Color.decode("#F9DFD2");
    public static final Color COLOUR_HAIR_DEFAULT = Color.decode("#804020");
    public static final Color COLOUR_EYE_DEFAULT = Color.decode("#808080");
    public static final Color COLOUR_MISC_DEFAULT = Color.decode("#808080");
    public static final ExtraColours EMPTY_COLOUR = new ExtraColours();
    public final int[] extraColoursArray = new int[ExtraColourType.values().length];

    public ExtraColours(ExtraColours extraColours) {
        for (int i = 0; i < ExtraColourType.values().length; ++i) {
            this.setColour(ExtraColourType.values()[i], extraColours.getColour(ExtraColourType.values()[i]));
        }
    }

    public ExtraColours() {
        this.setColour(ExtraColourType.SKIN, COLOUR_SKIN_DEFAULT.getRGB());
        this.setColour(ExtraColourType.HAIR, COLOUR_HAIR_DEFAULT.getRGB());
        this.setColour(ExtraColourType.EYE, COLOUR_EYE_DEFAULT.getRGB());
        this.setColour(ExtraColourType.MISC, COLOUR_MISC_DEFAULT.getRGB());
    }

    public int getColour(ExtraColourType type) {
        return this.extraColoursArray[type.ordinal()];
    }

    public byte[] getColourBytes(ExtraColourType type) {
        return PaintingHelper.intToBytes(this.extraColoursArray[type.ordinal()]);
    }

    public void setColour(ExtraColourType type, int trgb) {
        this.extraColoursArray[type.ordinal()] = trgb;
    }

    public void setColourBytes(ExtraColourType type, byte[] rgbt) {
        this.extraColoursArray[type.ordinal()] = PaintingHelper.bytesToInt(rgbt);
    }

    public int hashCode() {
        int prime = 193;
        return 193 * Arrays.hashCode(this.extraColoursArray);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ExtraColours other = (ExtraColours)obj;
        return Arrays.equals(this.extraColoursArray, other.extraColoursArray);
    }

    static {
        for (int i = 0; i < ExtraColourType.values().length; ++i) {
            EMPTY_COLOUR.setColour(ExtraColourType.values()[i], 0);
        }
    }

    public static enum ExtraColourType {
        SKIN,
        HAIR,
        EYE,
        MISC;

    }
}

