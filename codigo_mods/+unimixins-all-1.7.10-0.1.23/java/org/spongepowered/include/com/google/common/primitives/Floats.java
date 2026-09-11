/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.CheckForNull
 *  javax.annotation.Nullable
 */
package org.spongepowered.include.com.google.common.primitives;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import org.spongepowered.include.com.google.common.primitives.Doubles;

public final class Floats {
    @Nullable
    @CheckForNull
    public static Float tryParse(String string) {
        if (Doubles.FLOATING_POINT_PATTERN.matcher(string).matches()) {
            try {
                return Float.valueOf(Float.parseFloat(string));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return null;
    }
}

