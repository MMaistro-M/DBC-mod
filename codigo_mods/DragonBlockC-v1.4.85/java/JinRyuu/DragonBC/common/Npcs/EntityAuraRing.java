/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityAuraRing
extends Entity {
    public int randomSoundDelay = 0;
    public int tex = 1;
    private String mot = "";
    int Age;
    int MaxAge = 20;
    int color = 0xFFFFFF;
    private float state = 0.0f;
    private float state2 = 0.0f;
    int crel = 0;
    float alpha = 0.1f;

    public EntityAuraRing(World par1World) {
        super(par1World);
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    public EntityAuraRing(World par1World, String dbcCharger, int c, float s, float s2, int cr) {
        super(par1World);
        this.mot = dbcCharger;
        this.color = c;
        this.state = s;
        this.state2 = s2;
        this.crel = cr;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public int getAge() {
        return this.Age;
    }

    public int getCol() {
        return this.color;
    }

    public float getState() {
        return this.state;
    }

    public float getState2() {
        return this.state2;
    }

    public float getCRel() {
        return this.crel;
    }

    public void setCol(int c) {
        this.color = c;
    }

    public float getAlp() {
        return this.alpha;
    }

    public void setAlp(float a) {
        this.alpha = a;
    }

    public float update_alpha() {
        float one_age = (float)this.MaxAge / 100.0f;
        float one_alpha = this.getAlp() / 100.0f;
        float alpha_curr = (100.0f - (float)this.Age / one_age) / 100.0f * (this.getAlp() * 3.0f);
        return alpha_curr;
    }

    public void func_70071_h_() {
        if (this.mot.length() > 1) {
            EntityPlayer other = this.field_70170_p.func_72924_a(this.mot);
            if (other != null) {
                this.func_70012_b(other.field_70165_t, other.field_70163_u + (double)(other instanceof EntityPlayerSP ? -1.6f : 0.0f), other.field_70161_v, 0.0f, 0.0f);
            } else {
                this.func_70106_y();
            }
        }
        if (this.Age++ >= this.MaxAge) {
            this.func_70106_y();
        }
    }

    public boolean getCanSpawnHere() {
        return !this.field_70170_p.func_72855_b(this.field_70121_D);
    }

    public void onLivingUpdate() {
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }
}

