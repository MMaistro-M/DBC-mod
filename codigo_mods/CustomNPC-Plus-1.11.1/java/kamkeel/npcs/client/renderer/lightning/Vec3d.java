/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer.lightning;

import kamkeel.npcs.client.renderer.lightning.Quaternion;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Vec3d {
    public double x;
    public double y;
    public double z;

    public Vec3d() {
    }

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d(Vec3d vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public Vec3d(Vec3 vec) {
        this.x = vec.field_72450_a;
        this.y = vec.field_72448_b;
        this.z = vec.field_72449_c;
    }

    public Vec3d copy() {
        return new Vec3d(this);
    }

    public static Vec3d fromEntity(Entity e) {
        return new Vec3d(e.field_70165_t, e.field_70163_u, e.field_70161_v);
    }

    public static Vec3d fromEntityCenter(Entity e) {
        return new Vec3d(e.field_70165_t, e.field_70163_u - (double)e.field_70129_M + (double)(e.field_70131_O / 2.0f), e.field_70161_v);
    }

    public Vec3d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d set(Vec3d vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public double dotProduct(Vec3d vec) {
        double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
        if (d > 1.0 && d < 1.00001) {
            d = 1.0;
        } else if (d < -1.0 && d > -1.00001) {
            d = -1.0;
        }
        return d;
    }

    public Vec3d crossProduct(Vec3d vec) {
        double d = this.y * vec.z - this.z * vec.y;
        double d1 = this.z * vec.x - this.x * vec.z;
        double d2 = this.x * vec.y - this.y * vec.x;
        this.x = d;
        this.y = d1;
        this.z = d2;
        return this;
    }

    public Vec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d add(Vec3d vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    public Vec3d subtract(Vec3d vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    public Vec3d multiply(double d) {
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double magSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vec3d normalize() {
        double d = this.mag();
        if (d != 0.0) {
            this.multiply(1.0 / d);
        }
        return this;
    }

    public Vec3d perpendicular() {
        if (this.z == 0.0) {
            return this.zCrossProduct();
        }
        return this.xCrossProduct();
    }

    public Vec3d xCrossProduct() {
        double d = this.z;
        double d1 = -this.y;
        this.x = 0.0;
        this.y = d;
        this.z = d1;
        return this;
    }

    public Vec3d zCrossProduct() {
        double d = this.y;
        double d1 = -this.x;
        this.x = d;
        this.y = d1;
        this.z = 0.0;
        return this;
    }

    public Vec3 toVec3() {
        return Vec3.func_72443_a((double)this.x, (double)this.y, (double)this.z);
    }

    public double angle(Vec3d vec) {
        return Math.acos(this.copy().normalize().dotProduct(vec.copy().normalize()));
    }

    public Vec3d negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vec3d rotate(double angle, Vec3d axis) {
        Quaternion.aroundAxis(axis.copy().normalize(), angle).rotate(this);
        return this;
    }

    public void glVertex() {
        GL11.glVertex3d((double)this.x, (double)this.y, (double)this.z);
    }

    public String toString() {
        return "Vec3d(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Vec3d)) {
            return false;
        }
        Vec3d v = (Vec3d)o;
        return this.x == v.x && this.y == v.y && this.z == v.z;
    }
}

