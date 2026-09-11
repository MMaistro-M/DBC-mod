/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.client.renderer.lightning;

import kamkeel.npcs.client.renderer.lightning.Vec3d;

public class Quaternion {
    public double x;
    public double y;
    public double z;
    public double s;

    public Quaternion() {
        this.s = 1.0;
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Quaternion(Quaternion quat) {
        this.x = quat.x;
        this.y = quat.y;
        this.z = quat.z;
        this.s = quat.s;
    }

    public Quaternion(double s, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.s = s;
    }

    public static Quaternion aroundAxis(double ax, double ay, double az, double angle) {
        double sinAngle = Math.sin(angle *= 0.5);
        return new Quaternion(Math.cos(angle), ax * sinAngle, ay * sinAngle, az * sinAngle);
    }

    public static Quaternion aroundAxis(Vec3d axis, double angle) {
        return Quaternion.aroundAxis(axis.x, axis.y, axis.z, angle);
    }

    public void multiply(Quaternion quat) {
        double d = this.s * quat.s - this.x * quat.x - this.y * quat.y - this.z * quat.z;
        double d1 = this.s * quat.x + this.x * quat.s - this.y * quat.z + this.z * quat.y;
        double d2 = this.s * quat.y + this.x * quat.z + this.y * quat.s - this.z * quat.x;
        double d3 = this.s * quat.z - this.x * quat.y + this.y * quat.x + this.z * quat.s;
        this.s = d;
        this.x = d1;
        this.y = d2;
        this.z = d3;
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.s * this.s);
    }

    public void normalize() {
        double d = this.mag();
        if (d == 0.0) {
            return;
        }
        d = 1.0 / d;
        this.x *= d;
        this.y *= d;
        this.z *= d;
        this.s *= d;
    }

    public void rotate(Vec3d vec) {
        double d = -this.x * vec.x - this.y * vec.y - this.z * vec.z;
        double d1 = this.s * vec.x + this.y * vec.z - this.z * vec.y;
        double d2 = this.s * vec.y - this.x * vec.z + this.z * vec.x;
        double d3 = this.s * vec.z + this.x * vec.y - this.y * vec.x;
        vec.x = d1 * this.s - d * this.x - d2 * this.z + d3 * this.y;
        vec.y = d2 * this.s - d * this.y + d1 * this.z - d3 * this.x;
        vec.z = d3 * this.s - d * this.z - d1 * this.y + d2 * this.x;
    }

    public String toString() {
        return "Quaternion(" + this.s + ", " + this.x + ", " + this.y + ", " + this.z + ")";
    }
}

