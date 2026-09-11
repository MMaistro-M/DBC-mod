/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.util;

import net.minecraft.util.Vec3;

public class MathUtil {
    public static float getYaw(Vec3 vector) {
        double x = vector.field_72450_a;
        double z = vector.field_72449_c;
        double yawRad = Math.atan2(z, x);
        return 90.0f - (float)Math.toDegrees(yawRad);
    }

    public static float getPitch(Vec3 vector) {
        double x = vector.field_72450_a;
        double y = vector.field_72448_b;
        double z = vector.field_72449_c;
        double horizontalMag = Math.sqrt(x * x + z * z);
        double pitchRad = Math.atan2(y, horizontalMag);
        return (float)(-Math.toDegrees(pitchRad));
    }
}

