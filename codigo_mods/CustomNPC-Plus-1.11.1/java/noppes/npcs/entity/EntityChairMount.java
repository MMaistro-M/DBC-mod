/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChairMount
extends Entity {
    public EntityChairMount(World world) {
        super(world);
        this.func_70105_a(0.0f, 0.0f);
    }

    public double func_70042_X() {
        return 0.5;
    }

    protected void func_70088_a() {
    }

    public void func_70030_z() {
        super.func_70030_z();
        if (this.field_70170_p != null && !this.field_70170_p.field_72995_K && this.field_70153_n == null) {
            this.field_70128_L = true;
        }
    }

    public boolean func_85032_ar() {
        return true;
    }

    public boolean func_82150_aj() {
        return true;
    }

    public void func_70091_d(double p_70091_1_, double p_70091_3_, double p_70091_5_) {
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_70069_a(float p_70069_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
        this.func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
        this.func_70101_b(p_70056_7_, p_70056_8_);
    }
}

