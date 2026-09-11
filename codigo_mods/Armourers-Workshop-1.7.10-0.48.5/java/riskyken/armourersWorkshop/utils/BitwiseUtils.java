/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.utils;

public final class BitwiseUtils {
    public static int getUByteFromInt(int source, int index) {
        return source >>> (3 - index) * 8 & 0xFF;
    }

    public static int setUByteToInt(int target, int index, int value) {
        int[] bytes = new int[]{target >>> 24 & 0xFF, target >>> 16 & 0xFF, target >>> 8 & 0xFF, target & 0xFF};
        bytes[index] = value;
        return (bytes[0] << 24) + (bytes[1] << 16) + (bytes[2] << 8) + bytes[3];
    }
}

