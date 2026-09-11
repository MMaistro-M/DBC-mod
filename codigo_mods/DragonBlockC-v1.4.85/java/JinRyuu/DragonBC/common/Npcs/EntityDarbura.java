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
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDarbura
extends EntityDBCEvil {
    public int randomSoundDelay = 0;
    public String tex;
    public final int AttPow = 3600;
    public final int HePo = 36000;
    private int target;

    public EntityDarbura(World par1World) {
        super(par1World);
        this.field_70728_aV = 200;
        this.tex = "darbura";
        this.setMediumDifficulty();
        this.addAAiTeleport();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(36000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3600.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/" + this.tex + ".png";
    }

    public long BattlePowerOld() {
        int BP = 150196224;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }

    public void target(int i) {
        this.target = i;
    }

    @Override
    public void func_70636_d() {
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
        this.randomSoundDelay = this.field_70146_Z.nextInt(40);
    }
}

