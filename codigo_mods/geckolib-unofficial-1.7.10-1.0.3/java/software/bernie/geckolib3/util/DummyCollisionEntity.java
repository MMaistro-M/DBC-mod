/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package software.bernie.geckolib3.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DummyCollisionEntity
extends Entity {
    public AxisAlignedBB bb;

    public DummyCollisionEntity(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    public DummyCollisionEntity(World p_i1582_1_, AxisAlignedBB bb) {
        super(p_i1582_1_);
        this.bb = bb;
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
    }

    public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
        return this.bb;
    }
}

