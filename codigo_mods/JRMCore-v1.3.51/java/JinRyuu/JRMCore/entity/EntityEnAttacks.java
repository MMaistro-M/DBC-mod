/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEnAttacks
extends Entity {
    public Entity shootingEntity;

    public EntityEnAttacks(World par1World) {
        super(par1World);
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }

    public long getPower(Entity entity) {
        return 1L;
    }
}

