/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.EntityEnAttacks;
import net.minecraft.world.World;

public class EntityEnergyAttJ4
extends EntityEnAttacks {
    private float size;
    private byte type;
    public final int number_of_lightVerts = 10;
    public long[] lightVert = new long[10];
    private int lightLivingTime;

    public float getSize() {
        return this.size;
    }

    public byte getType() {
        return this.type;
    }

    public int getLightLivingTime() {
        return this.lightLivingTime;
    }

    public EntityEnergyAttJ4(World w, byte type, double x, double y, double z) {
        super(w);
        this.type = type;
        this.size = 1.0f;
        this.func_70105_a(this.size, this.size);
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa == 1) {
                this.func_70105_a(this.size, this.size);
            }
            if (this.field_70173_aa > 20) {
                this.func_70106_y();
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }
}

