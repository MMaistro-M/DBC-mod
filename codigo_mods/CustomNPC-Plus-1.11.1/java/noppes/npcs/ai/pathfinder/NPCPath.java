/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai.pathfinder;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.Vec3;
import noppes.npcs.ai.pathfinder.NPCPathPoint;

public class NPCPath
extends PathEntity {
    public final NPCPathPoint[] field_75884_a;
    public int field_75882_b;
    public int field_75883_c;

    public NPCPath(NPCPathPoint[] pointsIn) {
        super((PathPoint[])pointsIn);
        this.field_75884_a = pointsIn;
        this.field_75883_c = pointsIn.length;
    }

    public void func_75875_a() {
        ++this.field_75882_b;
    }

    public boolean func_75879_b() {
        return this.field_75882_b >= this.field_75883_c;
    }

    public NPCPathPoint getFinalPathPoint() {
        return this.field_75883_c > 0 ? this.field_75884_a[this.field_75883_c - 1] : null;
    }

    public NPCPathPoint getPathPointFromIndex(int p_75877_1_) {
        return this.field_75884_a[p_75877_1_];
    }

    public int func_75874_d() {
        return this.field_75883_c;
    }

    public void func_75871_b(int p_75871_1_) {
        this.field_75883_c = p_75871_1_;
    }

    public int func_75873_e() {
        return this.field_75882_b;
    }

    public void func_75872_c(int p_75872_1_) {
        this.field_75882_b = p_75872_1_;
    }

    public Vec3 func_75881_a(Entity entity, int index) {
        double d0 = (double)this.field_75884_a[index].field_75839_a + (double)((int)(entity.field_70130_N + 1.0f)) * 0.5;
        double d1 = (double)this.field_75884_a[index].field_75837_b + 0.05;
        double d2 = (double)this.field_75884_a[index].field_75838_c + (double)((int)(entity.field_70130_N + 1.0f)) * 0.5;
        return Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
    }

    public Vec3 func_75878_a(Entity p_75878_1_) {
        return this.func_75881_a(p_75878_1_, this.field_75882_b);
    }

    public Vec3 getCurrentPos() {
        NPCPathPoint pathpoint = this.field_75884_a[this.field_75882_b];
        return Vec3.func_72443_a((double)pathpoint.field_75839_a, (double)pathpoint.field_75837_b, (double)pathpoint.field_75838_c);
    }

    public boolean func_75876_a(PathEntity pathEntity) {
        NPCPath NPCPath2 = (NPCPath)pathEntity;
        if (NPCPath2 == null) {
            return false;
        }
        if (NPCPath2.field_75884_a.length != this.field_75884_a.length) {
            return false;
        }
        for (int i = 0; i < this.field_75884_a.length; ++i) {
            if (this.field_75884_a[i].field_75839_a == NPCPath2.field_75884_a[i].field_75839_a && this.field_75884_a[i].field_75837_b == NPCPath2.field_75884_a[i].field_75837_b && this.field_75884_a[i].field_75838_c == NPCPath2.field_75884_a[i].field_75838_c) continue;
            return false;
        }
        return true;
    }

    public boolean func_75880_b(Vec3 vec3) {
        NPCPathPoint pathpoint = this.getFinalPathPoint();
        return pathpoint == null ? false : pathpoint.field_75839_a == (int)vec3.field_72450_a && pathpoint.field_75838_c == (int)vec3.field_72449_c;
    }
}

