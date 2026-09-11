/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCKiAttacks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAuraSup
extends Entity {
    public int randomSoundDelay = 0;
    public int tex = 2;
    private Entity mot;
    int Age;
    int MaxAge = 20;

    public EntityAuraSup(World par1World) {
        super(par1World);
    }

    public EntityAuraSup(World par1World, Entity other) {
        super(par1World);
        this.mot = other;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public void func_70071_h_() {
        if (this.mot != null) {
            this.field_70159_w = this.mot.field_70159_w;
            this.field_70181_x = this.mot.field_70181_x;
            this.field_70179_y = this.mot.field_70179_y;
        }
        int x = 0;
        int y = 0;
        int z = 0;
        if (this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v - 1).func_149688_o() == Material.field_151579_a) {
            x = (int)this.field_70165_t;
            y = (int)this.field_70163_u;
            z = (int)this.field_70161_v;
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.Age++ >= this.MaxAge) {
            this.func_70106_y();
        }
        this.field_70181_x += 0.0;
        if (this.field_70163_u == this.field_70167_r) {
            this.field_70159_w *= 1.0;
            this.field_70179_y *= 1.0;
        }
        if (!this.field_70122_E) {
            this.field_70159_w *= DBCKiAttacks.motX;
            this.field_70179_y *= DBCKiAttacks.motZ;
            this.field_70181_x *= DBCKiAttacks.motY;
        }
        this.func_70091_d(this.field_70159_w * 2.0, this.field_70181_x * 2.0, this.field_70179_y * 2.0);
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        String textura = "";
        if (this.tex == 1) {
            textura = "jinryuudragonbc:aurab.png";
        }
        if (this.tex == 2) {
            textura = "jinryuudragonbc:auras.png";
        }
        if (this.tex == 3) {
            textura = "jinryuudragonbc:aurap.png";
        }
        if (this.tex == 4) {
            textura = "jinryuudragonbc:aurar.png";
        }
        return textura;
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

