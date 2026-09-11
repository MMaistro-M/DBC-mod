/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  net.minecraft.util.MathHelper
 */
package net.minecraft.util;

import com.google.common.base.Objects;
import net.minecraft.util.MathHelper;

public class Vec3i
implements Comparable<Vec3i> {
    private final int x;
    private final int y;
    private final int z;
    private final double xd;
    private final double yd;
    private final double zd;

    public Vec3i(int xIn, int yIn, int zIn) {
        this((double)xIn, (double)yIn, (double)zIn);
    }

    public Vec3i(double xIn, double yIn, double zIn) {
        this.x = MathHelper.func_76128_c((double)xIn);
        this.y = MathHelper.func_76128_c((double)yIn);
        this.z = MathHelper.func_76128_c((double)zIn);
        this.xd = xIn;
        this.yd = yIn;
        this.zd = zIn;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof Vec3i)) {
            return false;
        }
        Vec3i vec3I = (Vec3i)p_equals_1_;
        return this.getX() != vec3I.getX() ? false : (this.getY() != vec3I.getY() ? false : this.getZ() == vec3I.getZ());
    }

    public int hashCode() {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }

    @Override
    public int compareTo(Vec3i p_compareTo_1_) {
        return this.getY() == p_compareTo_1_.getY() ? (this.getZ() == p_compareTo_1_.getZ() ? this.getX() - p_compareTo_1_.getX() : this.getZ() - p_compareTo_1_.getZ()) : this.getY() - p_compareTo_1_.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public double getXD() {
        return this.xd;
    }

    public double getYD() {
        return this.yd;
    }

    public double getZD() {
        return this.zd;
    }

    public Vec3i crossProduct(Vec3i vec) {
        return new Vec3i(this.getYD() * vec.getZD() - this.getZD() * vec.getYD(), this.getZD() * vec.getXD() - this.getXD() * vec.getZD(), this.getXD() * vec.getYD() - this.getYD() * vec.getXD());
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("x", this.getX()).add("y", this.getY()).add("z", this.getZ()).toString();
    }
}

