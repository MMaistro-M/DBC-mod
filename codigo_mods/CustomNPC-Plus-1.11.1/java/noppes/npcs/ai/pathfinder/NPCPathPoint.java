/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.ai.pathfinder;

import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import noppes.npcs.ai.pathfinder.PathNodeType;

public class NPCPathPoint
extends PathPoint {
    public final int field_75839_a;
    public final int field_75837_b;
    public final int field_75838_c;
    public final int field_75840_j;
    public int field_75835_d = -1;
    public float field_75836_e;
    public float field_75833_f;
    public float field_75834_g;
    public NPCPathPoint field_75841_h;
    public boolean field_75842_i;
    public float costMalus;
    public float cost;
    public float distanceFromOrigin;
    public PathNodeType nodeType = PathNodeType.BLOCKED;

    public NPCPathPoint(int xIn, int yIn, int zIn) {
        super(xIn, yIn, zIn);
        this.field_75839_a = xIn;
        this.field_75837_b = yIn;
        this.field_75838_c = zIn;
        this.field_75840_j = NPCPathPoint.makeHash(xIn, yIn, zIn);
    }

    public static int makeHash(int xIn, int yIn, int zIn) {
        return yIn & 0xFF | (xIn & Short.MAX_VALUE) << 8 | (zIn & Short.MAX_VALUE) << 24 | (xIn < 0 ? Integer.MIN_VALUE : 0) | (zIn < 0 ? 32768 : 0);
    }

    public float distanceTo(NPCPathPoint point) {
        float f = point.field_75839_a - this.field_75839_a;
        float f1 = point.field_75837_b - this.field_75837_b;
        float f2 = point.field_75838_c - this.field_75838_c;
        return MathHelper.func_76129_c((float)(f * f + f1 * f1 + f2 * f2));
    }

    public float distanceToSquared(NPCPathPoint point) {
        float f = point.field_75839_a - this.field_75839_a;
        float f1 = point.field_75837_b - this.field_75837_b;
        float f2 = point.field_75838_c - this.field_75838_c;
        return f * f + f1 * f1 + f2 * f2;
    }

    public float distanceManhattan(NPCPathPoint point) {
        float f = Math.abs(point.field_75839_a - this.field_75839_a);
        float f1 = Math.abs(point.field_75837_b - this.field_75837_b);
        float f2 = Math.abs(point.field_75838_c - this.field_75838_c);
        return f + f1 + f2;
    }

    public boolean equals(Object point) {
        if (!(point instanceof NPCPathPoint)) {
            return false;
        }
        NPCPathPoint pathpoint = (NPCPathPoint)((Object)point);
        return this.field_75840_j == pathpoint.field_75840_j && this.field_75839_a == pathpoint.field_75839_a && this.field_75837_b == pathpoint.field_75837_b && this.field_75838_c == pathpoint.field_75838_c;
    }

    public int hashCode() {
        return this.field_75840_j;
    }

    public boolean func_75831_a() {
        return this.field_75835_d >= 0;
    }

    public String toString() {
        return this.field_75839_a + ", " + this.field_75837_b + ", " + this.field_75838_c;
    }
}

