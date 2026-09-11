/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbsbroly;

import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDBSBroly2
extends EntityDBCEvil {
    public String tex;
    public final int AttPow = 20000;
    public final int HePo = 140000;

    public EntityDBSBroly2(World par1World) {
        super(par1World);
        this.field_70728_aV = 200;
        this.tex = "dbsbroly2";
        this.func_70105_a(0.6f, 2.0f);
        this.setData1(1);
        this.setData2(6);
        this.setHardDifficulty();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(140000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(20000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/" + this.tex + ".png";
    }

    public long BattlePowerOld() {
        int BP = 1654251520;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }

    @Override
    public void func_70636_d() {
        if (this.doBlst()) {
            int r = (int)(Math.random() * 2.0);
            if (r == 0) {
                this.setData1(1);
                this.setData2(6);
            } else {
                this.setData1(6);
                this.setData2(6);
            }
        }
        this.becomeAngryAtAllPlayer();
        super.func_70636_d();
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            this.becomeAngryAt(var3);
        }
        super.func_70645_a(par1DamageSource);
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
    }
}

